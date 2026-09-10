package org.gotchafish.raid.socket;

import org.gotchafish.raid.room.RaidRoomManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RaidServer {
    // 서버 포트 번호 -> 이후 변경 가능
    private static final int PORT = 9999;

    // 서버에서 생성된 모든 대결방을 관리
    private final RaidRoomManager roomManager = new RaidRoomManager();

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("대결 서버가 시작되었습니다.");
            System.out.println("포트 : " + PORT);
            System.out.println();

            while (true) {
                // 클라이언트 접속을 기다리고, 접속하면 통신용 Socket 생성
                Socket socket = serverSocket.accept();
                System.out.println("클라이언트가 접속했습니다. - " + socket.getRemoteSocketAddress());

                // 접속한 클라이언트마다 전담 Thread 생성
                RaidClientThread clientThread = new RaidClientThread(socket, roomManager);
                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println("대결 서버 실행 중 오류가 발생했습니다.");
        }
    }

    public static void main(String[] args) {
        new RaidServer().start();
    }
}