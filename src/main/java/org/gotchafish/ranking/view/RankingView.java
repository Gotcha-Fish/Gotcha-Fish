package org.gotchafish.ranking.view;

import org.gotchafish.ranking.controller.RankingController;
import org.gotchafish.user.dto.Session;

import java.util.Scanner;

public class RankingView {
    private final Scanner sc = new Scanner(System.in);

    private final RankingController rankingController = RankingController.getInstance();

    public void showRankingMenu() {
        while (true) {
            System.out.println();
            System.out.println("================================");
            System.out.println("              랭킹");
            System.out.println("================================");
            System.out.println();

            System.out.println("1. 낚은 물고기 수 랭킹");
            System.out.println("2. 사용자 보유 골드 랭킹");
            System.out.println("0. 뒤로가기");
            System.out.println();

            System.out.print("선택 : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    this.fishingRankingMenu();
                    break;
                case 2:
                    this.goldRankingMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    public void fishingRankingMenu() {
        rankingController.showMyFishingRanking(Session.getUserId());

        int page = 1;

        do {
            if(! rankingController.showFishingRanking(page++)) return;

            System.out.println();
            System.out.println("1. 다음 페이지");
            System.out.println("0. 나가기");
            System.out.println();

            System.out.print("선택 : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }

        } while (true);
    }

    public void goldRankingMenu() {
        rankingController.showMyGoldRanking(Session.getUserId());

        int page = 1;

        do {
            if(! rankingController.showGoldRanking(page++)) return;

            System.out.println();
            System.out.println("1. 다음 페이지");
            System.out.println("0. 나가기");
            System.out.println();

            System.out.print("선택 : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }

        } while (true);
    }
}