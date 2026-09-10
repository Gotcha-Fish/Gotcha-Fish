package org.gotchafish.raid.controller;

import org.gotchafish.raid.dto.RaidRoomDTO;
import org.gotchafish.raid.socket.RaidClient;
import org.gotchafish.raid.view.RaidView;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class RaidController {
    private static final RaidController instance = new RaidController();

    public static RaidController getInstance() { return instance; }

    private final Scanner scanner = new Scanner(System.in);

    public void createRoom(Long userId) {
        try {
            RaidClient client = new RaidClient(userId);

            // 대결방 생성 요청
            client.createRoom();
        } catch (IOException e) {
            System.out.println("서버 통신 중 오류가 발생했습니다.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void joinRoom(Long UserId) {
        try {
            RaidClient client = new RaidClient(UserId);

            // 참여 가능한 대결방 리스트 조회
            List<RaidRoomDTO> rooms = client.findWaitingRooms();

            for (RaidRoomDTO room : rooms) {
                System.out.println(room.getRoomId() + "번 방" + " | 호스트 : " + room.getHostUserId());
            }
            System.out.println("================================");

            // 입장할 방 번호 입력
            System.out.println();
            System.out.print("입장할 방 번호 : ");
            Long roomId = scanner.nextLong();
            System.out.println();

            // 선택한 방 참가 요청
            client.joinRoom(roomId);
        } catch (IOException e) {
            System.out.println("서버 통신 중 오류가 발생했습니다.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new RaidView().showRaidMenu(2L);
    }
}