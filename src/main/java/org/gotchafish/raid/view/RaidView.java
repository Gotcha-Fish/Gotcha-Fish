package org.gotchafish.raid.view;

import org.gotchafish.raid.controller.RaidController;
import org.gotchafish.user.dto.Session;

import java.util.Scanner;

import static org.gotchafish.common.ConsoleColor.*;

public class RaidView {
    private final Scanner scanner = new Scanner(System.in);

    private final RaidController raidController = RaidController.getInstance();

    public void showRaidMenu() {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "═".repeat(40) + RESET);
        System.out.println(BOLD + BRIGHT_YELLOW + "             ⚔️ 1:1 대결" + RESET);
        System.out.println(BRIGHT_CYAN +  "═".repeat(40) + RESET);
        System.out.println();

        System.out.println(GREEN + "  [1] " + RESET + "방 만들기");
        System.out.println(GREEN + "  [2] " + RESET + "방 참가하기");
        System.out.println(YELLOW + "  [0] " + RESET + "뒤로가기");
        System.out.println();

        System.out.print(BRIGHT_CYAN + "선택 > " + RESET);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                createRoom(Session.getUserId());
                break;
            case 2:
                findRooms(Session.getUserId());
                break;
            case 0:
                return;
            default:
                System.out.println(RED + "❌ 잘못된 선택입니다." + RESET);
        }
    }

    public void createRoom(Long userId) {
        System.out.println();
        System.out.println("1:1 대결방 생성을 시작합니다.");
        System.out.println();

        System.out.print(GREEN + "대결방 이름 : " + RESET);
        scanner.nextLine();
        String roomName = scanner.nextLine();
        System.out.println();

        raidController.createRoom(userId, roomName);
    }

    public void findRooms(Long userId) {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "  [ 참여 가능한 대결방 목록 ]" + RESET);
        System.out.println();

        if (raidController.findRooms(userId)) {
            joinRoom(userId);
        }
    }

    public void joinRoom(Long userId) {
        System.out.println();
        System.out.print(GREEN + "입장할 대결방 번호 : " + RESET);

        Long roomId = scanner.nextLong();
        System.out.println();

        raidController.joinRoom(userId, roomId);
    }

    public static void main(String[] args) {
        new RaidView().findRooms(2L);
    }
}