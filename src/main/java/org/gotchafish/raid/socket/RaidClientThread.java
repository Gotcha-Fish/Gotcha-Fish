package org.gotchafish.raid.socket;

import org.gotchafish.fish.dto.FishDTO;
import org.gotchafish.fish.service.FishService;
import org.gotchafish.fish.service.FishServiceImpl;
import org.gotchafish.raid.room.RaidRoom;
import org.gotchafish.raid.room.RaidRoomManager;
import org.gotchafish.user.service.UserService;
import org.gotchafish.user.service.UserServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public class RaidClientThread extends Thread {
    private final Socket socket;
    private final RaidRoomManager roomManager;

    private final FishService fishService = FishServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

    private Long userId;
    private String nickName;
    private boolean guestJoined = false;

    public RaidClientThread(Socket socket, RaidRoomManager roomManager) {
        this.socket = socket;
        this.roomManager = roomManager;
    }

    /**
     * Gust 입장까지 Host 대기 상태 유지
     */
    public synchronized void waitForGuest() {
        try {
            while (!guestJoined) {
                wait();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Gust 입장시 대기중인 Host 깨움
     */
    public synchronized void notifyGuestJoined() {
        guestJoined = true;
        notify();
    }

    @Override
    public void run() {
        try (
                // 서버 기준: 클라이언트가 보낸 데이터를 읽음
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // 서버 기준: 클라이언트에게 데이터를 보냄
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
        ) {
            try {
                // 클라이언트 userId 받기
                userId = Long.parseLong(reader.readLine());

                // 클라이언트 닉네임 조회
                nickName = userService.getUser(userId).getNickname();

                // "CREATE_ROOM", "GET_ROOMS", "JOIN_ROOM"
                String message = reader.readLine();

                switch (message) {
                    // 대결방 생성
                    case "CREATE_ROOM" -> {
                        // 클라이언트가 보낸 대결방 이름 받기
                        String roomName = reader.readLine();

                        RaidRoom room = roomManager.createRoom(roomName, this, nickName);

                        writer.println("방을 생성했습니다!");
                        writer.println();
                        writer.println("방 번호 : " + room.getRoomId());
                        writer.println();
                        writer.println("상대방을 기다리는 중...");
                        writer.println();

                        // 게스트 입장 대기
                        waitForGuest();

                        writer.println("🎉 상대방이 입장했습니다.");
                        writer.println();

                        writer.println("상대방 : " + room.getGuestNickname());
                        writer.println();

                        startBattle(reader, writer, room);
                    }
                    // 대기중인 대결방 목록 조회
                    case "GET_ROOMS" -> {
                        List<RaidRoom> rooms = roomManager.findWaitingRooms();

                        for (RaidRoom room : rooms) {
                            writer.println(room.getRoomId() + "," + room.getRoomName() + "," + room.getHostNickname());
                        }
                    }
                    // 대결방 입장
                    case "JOIN_ROOM" -> {
                        Long roomId = Long.parseLong(reader.readLine());

                        RaidRoom room = roomManager.joinRoom(roomId, this, nickName);

                        writer.println("🎉 대결방에 입장했습니다!");
                        writer.println();

                        // 호스트 깨우기
                        RaidClientThread hostThread = room.getHostThread();
                        hostThread.notifyGuestJoined();

                        writer.println("상대방 : " + room.getHostNickname());
                        writer.println();

                        startBattle(reader, writer, room);
                    }
                }
            } catch (IOException e) {
                writer.println("ERROR|" + "통신 중 오류가 발생했습니다.");
            } catch (SQLException e) {
                writer.println("ERROR|" + "DB 조회 중 오류가 발생했습니다.");
            } catch (RuntimeException e) {
                writer.println("ERROR|" + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 대결 시작을 안내하고 보유 물고기 목록을 출력한 뒤, 선택 번호를 입력받는다.
     * @param reader 클라이언트 입력을 읽을 BufferedReader
     * @param writer 클라이언트 출력을 담당할 PrintWriter
     */
    private void startBattle(BufferedReader reader, PrintWriter writer, RaidRoom room) throws IOException, SQLException {
        writer.println("대결을 시작합니다.");
        writer.println();

        writer.println("================================");
        writer.println("          대결 시작");
        writer.println("================================");

        // 사용자 보유 물고기 목록 조회
        List<FishDTO> myFish = fishService.getMyFish(userId);

        writer.println();
        writer.println("보유 물고기 중 하나를 선택하세요.");

        writer.println();
        writer.println("-----------------------------------");
        writer.println();

        for (int i = 0; i < myFish.size(); i++) {
            FishDTO fish = myFish.get(i);

            writer.println((i + 1) + ". " + fish.getFishName() + " X " + fish.getQuantity());
            writer.println("   희귀도 : " + fish.getRarity());
            writer.println();
        }

        writer.println("-----------------------------------");
        writer.println();

        writer.println("선택할 물고기 번호를 입력하세요 : ");
        int choice = Integer.parseInt(reader.readLine());
        FishDTO selectedFish = myFish.get(choice - 1);

        writer.println();
        writer.println(selectedFish.getFishName() + "를 선택했습니다!");

        writer.println();
        writer.println("상대방의 선택을 기다리는 중...");

        // 상대방 선택 기다리기
        room.selectFish(this, selectedFish);
        room.waitForFishSelection();

        // Host 쪽에서 승부 판정
        if (this == room.getHostThread()) {
            room.determineBattleResult();
        } else {
            // Guest 대기
            room.waitForBattleResult();
        }

        // 두 플레이어 모두 결과 출력
        printBattleResult(room, writer);

        // 대결방 삭제
        if (this == room.getHostThread()) {
            roomManager.removeRoom(room.getRoomId());
        }
    }

    /**
     * 대결 결과를 양쪽 플레이어에게 출력한다.
     * @param room 대결이 진행 중인 방
     */
    private void printBattleResult(RaidRoom room, PrintWriter writer) {
        FishDTO hostFish = room.getHostFish();
        FishDTO guestFish = room.getGuestFish();

        RaidClientThread winner = room.getWinnerThread();

        String winnerNickname = winner.nickName;
        FishDTO winnerFish = winner == room.getHostThread() ? hostFish : guestFish;

        boolean isDraw = hostFish.getRarity().getProbability()
                == guestFish.getRarity().getProbability();

        String drawMessage = "";

        if (isDraw) {
            drawMessage = """
                    
                    희귀도가 같습니다!
                    
                    ⚔️ 승부를 랜덤 결정합니다...
                    
                    --------------------------------
                    """;
        }

        String resultMessage = """
                
                ================================
                          대결 결과
                ================================
                
                %s
                🐟 %s
                희귀도 : %s
                
                      VS
                
                %s
                🐟 %s
                희귀도 : %s
                
                --------------------------------
                %s
                🏆 %s 승리!
                
                🎁 %s 획득
                
                
                두 물고기가 모두 %s의 보관함으로 이동합니다.
                """.formatted(
                room.getHostNickname(),
                hostFish.getFishName(),
                hostFish.getRarity(),

                room.getGuestNickname(),
                guestFish.getFishName(),
                guestFish.getRarity(),

                drawMessage,
                winnerNickname,
                winnerFish.getFishName(),
                winnerNickname
        );

        writer.println(resultMessage);
    }
}