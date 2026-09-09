package org.gotchafish.fish.view;

import org.gotchafish.fish.controller.FishController;
import org.gotchafish.user.dto.Session;

import java.util.Scanner;

public class FishView {
    private final Scanner sc = new Scanner(System.in);

    private final FishController fishController = FishController.getInstance();

    public void sellFish() {
        System.out.println();
        System.out.println("================================");
        System.out.println("          물고기 판매");
        System.out.println("================================");
        System.out.println();

        if(!fishController.getMyFishInfo(Session.getUserId())) return;

        System.out.println("0. 뒤로가기");
        System.out.println();

        System.out.print("판매할 물고기 : ");
        int choice = sc.nextInt();

        if (choice == 0) {
            sc.nextLine();
            return;
        }

        System.out.println();
        System.out.print("판매할 수량 : ");
        int quantity = sc.nextInt();

        fishController.sellFish(Session.getUserId(), (long) choice, quantity);
    }
}
