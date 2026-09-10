package org.gotchafish.raid.view;

import org.gotchafish.raid.controller.RaidController;

import java.util.Scanner;

public class RaidView {
    private final Scanner scanner = new Scanner(System.in);

    private final RaidController raidController = RaidController.getInstance();

    public void showRaidMenu(Long userId) {
        System.out.println();
        System.out.println("================================");
        System.out.println("             대결");
        System.out.println("================================");
        System.out.println();

        System.out.println("1. 방 만들기");
        System.out.println("2. 방 참가하기");
        System.out.println("0. 뒤로가기");
        System.out.println();

        System.out.print("선택 : ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                createRoom(userId);
                break;
            case 2:
                joinRoom(userId);
                break;
            case 0:
                return;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    public void createRoom(Long userId) {
        System.out.println();
        System.out.println("================================");
        System.out.println("         대결 방 생성");
        System.out.println("================================");
        System.out.println();

        raidController.createRoom(userId);
    }

    public void joinRoom(Long userId) {
        System.out.println();
        System.out.println("================================");
        System.out.println("          대결 방 목록");
        System.out.println("================================");
        System.out.println();

        raidController.joinRoom(userId);
    }

    public static void main(String[] args) {
        new RaidView().showRaidMenu(1L);
    }
}