package org.gotchafish.rod.view;

import org.gotchafish.rod.controller.RodController;
import org.gotchafish.user.dto.Session;

import java.util.Scanner;

import static org.gotchafish.common.ConsoleColor.*;
import static org.gotchafish.common.ConsoleColor.BRIGHT_CYAN;
import static org.gotchafish.common.ConsoleColor.BRIGHT_YELLOW;
import static org.gotchafish.common.ConsoleColor.RESET;

public class RodView {
    private final Scanner sc = new Scanner(System.in);

    private final RodController rodController = RodController.getInstance();

    public void buyRod() {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "═".repeat(40) + RESET);
        System.out.println(BOLD + BRIGHT_YELLOW + "             🎣 낚시대 상점" + RESET);
        System.out.println(BRIGHT_CYAN +  "═".repeat(40) + RESET);
        System.out.println();

        if(!rodController.getRodShopInfo(Session.getUserId())) return;

        System.out.println(YELLOW + "  [0] " + RESET + "뒤로가기");
        System.out.println();

        System.out.print(GREEN + "구입할 낚시대 : " + RESET);
        int choice = sc.nextInt();

        if (choice == 0) {
            sc.nextLine();
            return;
        }

        System.out.println();
        System.out.print(GREEN + "구입할 수량 : " + RESET);
        int quantity = sc.nextInt();

        rodController.buyRod(Session.getUserId(), (long) choice, quantity);
    }

    public void useRod() {
        if(!rodController.getMyRodSInfo(Session.getUserId())) return;

        System.out.print(BRIGHT_CYAN + "선택 > " + RESET);
        int choice = sc.nextInt();

        rodController.useRod(Session.getUserId(), (long) choice);
    }
}
