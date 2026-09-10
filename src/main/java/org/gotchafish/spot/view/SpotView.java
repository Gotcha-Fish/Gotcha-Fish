package org.gotchafish.spot.view;

import org.gotchafish.spot.controller.SpotController;
import org.gotchafish.user.dto.Session;

import java.util.Scanner;

public class SpotView {
    private final Scanner sc = new Scanner(System.in);

    private final SpotController spotController = new SpotController();

    public void unlockSpot() {
        System.out.println();
        System.out.println("================================");
        System.out.println("          낚시터 상점");
        System.out.println("================================");
        System.out.println();

        spotController.spotList(Session.getUserId());

        System.out.println("0. 뒤로가기");
        System.out.println();

        System.out.print("잠금 해제할 낚시터 : ");
        int choice = sc.nextInt();

        if (choice == 0) {
            sc.nextLine();
            return;
        }

        spotController.unlockSpot(Session.getUserId(), (long) choice);
    }

    public Long selectSpot(Long userId) {
        spotController.selectSpot(userId);

        System.out.print("낚시터 선택 : ");
        int choice = sc.nextInt();
        sc.nextLine();

        return (long) choice;
    }

}
