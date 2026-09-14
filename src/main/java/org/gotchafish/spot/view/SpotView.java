package org.gotchafish.spot.view;

import org.gotchafish.spot.controller.SpotController;
import org.gotchafish.user.dto.Session;

import static org.gotchafish.common.ConsoleColor.*;

import java.util.Scanner;

public class SpotView {
    private final Scanner sc = new Scanner(System.in);

    private final SpotController spotController = new SpotController();

    public void unlockSpot() {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "═".repeat(40) + RESET);
        System.out.println(BOLD + BRIGHT_YELLOW + "             🌊 낚시터 상점" + RESET);
        System.out.println(BRIGHT_CYAN +  "═".repeat(40) + RESET);
        System.out.println();

        spotController.spotList(Session.getUserId());

        System.out.println(YELLOW + "  [0] " + RESET + "뒤로가기");
        System.out.println();

        System.out.print(GREEN + "선택 : " + RESET);
        int choice = sc.nextInt();

        if (choice == 0) {
            sc.nextLine();
            return;
        }

        spotController.unlockSpot(Session.getUserId(), (long) choice);
    }

    public Long selectSpot(Long userId) {
        spotController.selectSpot(userId);

        System.out.print(BRIGHT_CYAN + "선택 > " + RESET);
        int choice = sc.nextInt();
        sc.nextLine();

        return (long) choice;
    }

}
