package org.gotchafish.raid.room;

import org.gotchafish.fish.dto.FishDTO;
import org.gotchafish.fish.service.FishServiceImpl;
import org.gotchafish.raid.dto.RaidResultDTO;
import org.gotchafish.raid.service.RaidServiceImpl;
import org.gotchafish.raid.socket.RaidClientThread;

import java.sql.SQLException;

public class RaidRoom {
    private final Long roomId;
    private final String roomName;

    private final RaidClientThread hostThread;
    private RaidClientThread guestThread;
    private RaidClientThread winnerThread;

    private final String hostNickname;
    private String guestNickname;

    private FishDTO hostFish;
    private FishDTO guestFish;

    public RaidRoom(Long roomId, String roomName, RaidClientThread hostThread, String hostNickname) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.hostThread = hostThread;
        this.hostNickname = hostNickname;
    }

    public void join(RaidClientThread guestThread, String guestNickname) {
        this.guestThread = guestThread;
        this.guestNickname = guestNickname;
    }

    public Long getRoomId() {
        return roomId;
    }

    public String getRoomName() { return roomName; }

    public RaidClientThread getHostThread() {
        return hostThread;
    }

    public RaidClientThread getGuestThread() { return guestThread; }

    public RaidClientThread getWinnerThread() { return winnerThread; }

    public String getHostNickname() { return hostNickname; }

    public String getGuestNickname() { return guestNickname; }

    public FishDTO getHostFish() { return hostFish; }

    public FishDTO getGuestFish() { return guestFish; }

    // 게스트 입장 여부
    public boolean isFull() { return guestThread != null; }

    // 대결 판정 여부
    public boolean isDone() { return winnerThread != null; }

    /**
     * 두 플레이어가 모두 물고기를 선택할 때까지 대기한다.
     */
    public synchronized void waitForFishSelection() {
        try {
            while (hostFish == null || guestFish == null) {
                wait();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 플레이어가 선택한 물고기를 저장하고, 선택 완료를 알린다.
     * @param player 물고기를 선택한 플레이어의 Thread
     * @param fish 플레이어가 선택한 물고기
     */
    public synchronized void selectFish(RaidClientThread player, FishDTO fish) {
        if (player == hostThread) {
            hostFish = fish;
        } else {
            guestFish = fish;
        }

        notifyAll();
    }

    /**
     * 승부가 판정날 때까지 대기한다.
     */
    public synchronized void waitForBattleResult() {
        try {
            while (!isDone()) {
                wait();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 두 물고기의 희귀도를 비교하여 승자를 결정한다.
     */
    public synchronized void determineBattleResult() throws SQLException {
        FishDTO hostFish = this.hostFish;
        FishDTO guestFish = this.guestFish;

        RaidClientThread host = this.getHostThread();
        RaidClientThread guest = this.getGuestThread();

        int hostRank = hostFish.getRarity().getProbability();
        int guestRank = guestFish.getRarity().getProbability();

        if (hostRank < guestRank) {
            winnerThread = host;
        } else if (hostRank > guestRank) {
            winnerThread = guest;
        } else {
            winnerThread = Math.random() < 0.5 ? host : guest;
        }

        // 대결 결과 DB 반영
        this.processBattleResult();

        notifyAll();
    }

    /**
     * 대결 결과를 DB에 반영한다. (물고기 수량 변경, 대결 결과 저장)
     * 승자는 패자의 물고기를 얻고, 패자는 본인 물고기를 잃는다.
     */
    private void processBattleResult() throws SQLException {
        RaidClientThread winner = this.getWinnerThread();

        RaidClientThread loser;
        FishDTO loserFish;

        if (winner == this.getHostThread()) {
            loser = this.getGuestThread();
            loserFish = this.getGuestFish();
        } else {
            loser = this.getHostThread();
            loserFish = this.getHostFish();
        }

        // 승자가 상대방의 물고기를 획득
        FishServiceImpl.getInstance().catchFish(winner.getUserId(), loserFish.getFishId());

        // 패자가 자신의 물고기를 잃음
        FishServiceImpl.getInstance().loseFish(loser.getUserId(), loserFish.getFishId());

        RaidResultDTO raidResultDTO = new RaidResultDTO(
                this.getHostThread().getUserId(),
                this.getGuestThread().getUserId(),
                this.getHostFish().getFishId(),
                this.getGuestFish().getFishId(),
                this.getWinnerThread().getUserId()
        );
        // 레이드 결과를 DB에 저장
        RaidServiceImpl.getInstance().insertRaidResult(raidResultDTO);
    }
}