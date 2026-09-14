package org.gotchafish.fish.view;

import org.gotchafish.fish.controller.FishController;
import org.gotchafish.user.dto.Session;

import java.util.Scanner;

import static org.gotchafish.common.ConsoleColor.*;

public class FishView {
    private final Scanner sc = new Scanner(System.in);

    private final FishController fishController = FishController.getInstance();

    public void sellFish() {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "═".repeat(40) + RESET);
        System.out.println(BOLD + BRIGHT_YELLOW + "             🍤 물고기 판매" + RESET);
        System.out.println(BRIGHT_CYAN + "═".repeat(40) + RESET);
        System.out.println();

        if(!fishController.getMyFishInfo(Session.getUserId())) return;

        System.out.println(YELLOW + "  [0] " + RESET + "뒤로가기");
        System.out.println();

        System.out.print(GREEN + "판매할 물고기 : " + RESET);
        int choice = sc.nextInt();

        if (choice == 0) {
            sc.nextLine();
            return;
        }

        System.out.println();
        System.out.print(BRIGHT_CYAN + "수량 > " + RESET);
        int quantity = sc.nextInt();

        fishController.sellFish(Session.getUserId(), (long) choice, quantity);
    }
}
