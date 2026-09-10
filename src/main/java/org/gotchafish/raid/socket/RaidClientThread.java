package org.gotchafish.raid.socket;

import org.gotchafish.fish.dto.FishDTO;
import org.gotchafish.fish.service.FishService;
import org.gotchafish.fish.service.FishServiceImpl;
import org.gotchafish.raid.room.RaidRoom;
import org.gotchafish.raid.room.RaidRoomManager;

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

    private Long userId;
    private PrintWriter writer;
    private boolean guestJoined = false;

    public RaidClientThread(Socket socket, RaidRoomManager roomManager) {
        this.socket = socket;
        this.roomManager = roomManager;
    }

    public Long getUserId() {
        return userId;
    }

    // 클라이언트에게 메시지 전송
    public void sendMessage(String message) {
        writer.println(message);
    }

    // Guest가 입장할 때까지 대기
    public synchronized void waitForGuest() {
        try {
            while (!guestJoined) {
                wait();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Guest가 입장하면 Host 깨움
    public synchronized void notifyGuestJoined() {
        guestJoined = true;
        notify();
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true)
        ) {
            writer = output;

            // 소켓이 닫히기 전에 예외를 잡아서 오류 응답을 보낸다.
            try {
                userId = Long.parseLong(reader.readLine());

                String message = reader.readLine();

                switch (message) {
                    case "CREATE_ROOM" -> {
                        RaidRoom room = roomManager.createRoom(this);

                        writer.println("방을 생성했습니다!");
                        writer.println();
                        writer.println("방 번호 : " + room.getRoomId());
                        writer.println();
                        writer.println("상대방을 기다리는 중...");
                        writer.println();

                        waitForGuest();

                        RaidClientThread guestThread = room.getGuestThread();

                        writer.println("🎉 상대방이 입장했습니다.");
                        writer.println();

                        startBattle(reader, guestThread);
                    }

                    case "GET_ROOMS" -> {
                        List<RaidRoom> rooms = roomManager.findWaitingRooms();

                        for (RaidRoom room : rooms) {
                            writer.println(room.getRoomId() + "," + room.getHostUserId());
                        }
                    }

                    case "JOIN_ROOM" -> {
                        Long roomId = Long.parseLong(reader.readLine());

                        RaidRoom room = roomManager.joinRoom(roomId, this);

                        writer.println("🎉 대결방에 입장했습니다!");
                        writer.println();

                        RaidClientThread hostThread = room.getHostThread();
                        hostThread.notifyGuestJoined();

                        startBattle(reader, hostThread);
                    }
                }
            } catch (SQLException e) {
                writer.println("ERROR|" + "DB 조회 중 오류가 발생했습니다.");
            } catch (RuntimeException e) {
                writer.println("ERROR|" + e.getMessage());
            } catch (IOException e) {
                writer.println("ERROR|" + "통신 중 오류가 발생했습니다.");
            }
        } catch (IOException e) {
            writer.println("ERROR|" + "연결 준비 또는 정리 중 오류가 발생했습니다.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                writer.println("ERROR|" + "소켓을 닫는 중 오류가 발생했습니다.");
            }
        }
    }

    /**
     * 대결 시작을 안내하고 보유 물고기 목록을 출력한 뒤, 선택 번호를 입력받는다.
     * @param reader 클라이언트 입력을 읽을 BufferedReader
     * @param opponentThread 상대방(Host 입장에선 Guest, Guest 입장에선 Host)의 Thread
     */
    private void startBattle(BufferedReader reader, RaidClientThread opponentThread) throws IOException, SQLException {
        writer.println("상대방 ID : " + opponentThread.getUserId());
        writer.println();

        writer.println("대결을 시작합니다.");
        writer.println();

        writer.println("================================");
        writer.println("          대결 시작");
        writer.println("================================");

        List<FishDTO> myFish = fishService.getMyFish(userId);

        writer.println();
        writer.println("보유 물고기 중 하나를 선택하세요.");

        writer.println();
        writer.println("--------------------------------");

        for (FishDTO fish : myFish) {
            writer.println((fish.getFishId()) + ". " + fish.getFishName()
                    + " x " + fish.getQuantity()
                    + "  희귀도 : " + fish.getRarity());
        }

        writer.println("--------------------------------");
        writer.println();

        writer.println("선택할 물고기 번호를 입력하세요 : ");
        int choice = Integer.parseInt(reader.readLine());
    }
}