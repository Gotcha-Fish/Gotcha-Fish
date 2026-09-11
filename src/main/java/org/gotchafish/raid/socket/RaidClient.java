package org.gotchafish.raid.socket;

import org.gotchafish.common.PropertyLoader;
import org.gotchafish.raid.dto.RaidRoomDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RaidClient {
    private static final String HOST = PropertyLoader.get("HOST");
    private static final int PORT = Integer.parseInt(PropertyLoader.get("PORT"));

    private final Long userId;

    public RaidClient(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() { return userId; }

    public void createRoom(String roomName) throws IOException {
        try (
                // 서버에 연결하는 Socket 생성
                Socket socket = new Socket(HOST, PORT);

                // 클라이언트 기준: 서버가 보낸 데이터를 읽음
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // 클라이언트 기준: 서버에게 데이터를 보냄
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // 서버에 현재 로그인한 사용자의 ID 전달
            writer.println(userId);

            // 방 생성 요청
            writer.println("CREATE_ROOM");

            // 생성할 방 이름 전달
            writer.println(roomName);

            // 서버가 보내는 모든 메시지 수신
            startMessageListener(reader, writer);
        }
    }

    public List<RaidRoomDTO> findWaitingRooms() throws IOException {
        List<RaidRoomDTO> rooms = new ArrayList<>();

        try (
                // 서버에 연결하는 Socket 생성
                Socket socket = new Socket(HOST, PORT);

                // 클라이언트 기준: 서버가 보낸 데이터를 읽음
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // 클라이언트 기준: 서버에게 데이터를 보냄
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // 서버에 현재 로그인한 사용자의 ID 전달
            writer.println(userId);

            // 방 목록 조회 요청
            writer.println("GET_ROOMS");

            String message;

            while ((message = reader.readLine()) != null) {
                String[] roomInfo = message.split(",");

                Long roomId = Long.parseLong(roomInfo[0]);
                String roomName = roomInfo[1];
                String hostName = roomInfo[2];

                rooms.add(new RaidRoomDTO(roomId, roomName, hostName));
            }
        }

        // 참여 가능한 방이 없는 경우 예외 던지기
        if (rooms.isEmpty()) {
            throw new RuntimeException("현재 참여 가능한 방이 없습니다.");
        }

        return rooms;
    }

    public void joinRoom(Long roomId) throws IOException {
        try (
                // 서버에 연결하는 Socket 생성
                Socket socket = new Socket(HOST, PORT);

                // 클라이언트 기준: 서버가 보낸 데이터를 읽음
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // 클라이언트 기준: 서버에게 데이터를 보냄
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // 서버에 현재 로그인한 사용자의 ID 전달
            writer.println(userId);

            // 방 참가 요청
            writer.println("JOIN_ROOM");

            // 입장할 방 번호 전달
            writer.println(roomId);

            // 서버가 보내는 모든 메시지 수신
            startMessageListener(reader, writer);
        }
    }

    /**
     * 서버 메시지를 현재 스레드에서 수신한다.
     */
    private void startMessageListener(BufferedReader reader, PrintWriter writer
    ) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String message;

        while ((message = reader.readLine()) != null) {
            if (message.startsWith("ERROR|")) {
                throw new RuntimeException(message.substring(6));
            }

            System.out.println(message);

            if (message.equals("선택할 물고기 번호를 입력하세요 : ")) {
                int choice = scanner.nextInt();
                writer.println(choice);
            }
        }
    }
}