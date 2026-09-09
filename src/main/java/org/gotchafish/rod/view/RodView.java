package org.gotchafish.rod.view;

import org.gotchafish.rod.controller.RodController;
import org.gotchafish.user.dto.Session;

import java.util.Scanner;

public class RodView {
    private final Scanner sc = new Scanner(System.in);

    private final RodController rodController = RodController.getInstance();

    public void buyRod() {
        System.out.println();
        System.out.println("================================");
        System.out.println("          낚시대 구입");
        System.out.println("================================");
        System.out.println();

        if(!rodController.getRodShopInfo(Session.getUserId())) return;

        System.out.println("0. 뒤로가기");
        System.out.println();

        System.out.print("구입할 낚시대 : ");
        int choice = sc.nextInt();

        if (choice == 0) {
            sc.nextLine();
            return;
        }

        System.out.println();
        System.out.print("구입할 수량 : ");
        int quantity = sc.nextInt();

        rodController.buyRod(Session.getUserId(), (long) choice, quantity);
    }

    public void useRod() {
        if(!rodController.getMyRodSInfo(Session.getUserId())) return;

        System.out.print("선택 : ");
        int choice = sc.nextInt();

        rodController.useRod(Session.getUserId(), (long) choice);
    }
}
