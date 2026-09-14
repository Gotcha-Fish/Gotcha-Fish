package org.gotchafish.ranking.view;

import org.gotchafish.ranking.controller.RankingController;
import org.gotchafish.user.dto.Session;

import java.util.Scanner;

import static org.gotchafish.common.ConsoleColor.*;
import static org.gotchafish.common.ConsoleColor.BRIGHT_YELLOW;

public class RankingView {
    private final Scanner sc = new Scanner(System.in);

    private final RankingController rankingController = RankingController.getInstance();

    public void showRankingMenu() {
        while (true) {
            System.out.println();
            System.out.println(BRIGHT_CYAN + "═".repeat(40) + RESET);
            System.out.println(BOLD + BRIGHT_YELLOW + "                🏆 랭킹" + RESET);
            System.out.println(BRIGHT_CYAN + "═".repeat(40) + RESET);
            System.out.println();

            System.out.println(GREEN + "  [1]" + RESET +  " 어획량 상위 랭킹");
            System.out.println(GREEN + "  [2]" + RESET + " 보유 골드 상위 랭킹");
            System.out.println(YELLOW + "  [0]" + RESET + " 뒤로가기");
            System.out.println();

            System.out.print(BRIGHT_CYAN + "선택 > " + RESET);
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
                    System.out.println(RED + "❌ 잘못된 선택입니다." + RESET);
            }
        }
    }

    public void fishingRankingMenu() {
        rankingController.showMyFishingRanking(Session.getUserId());

        int page = 1;

        do {
            if(! rankingController.showFishingRanking(page++)) return;

            System.out.println();
            System.out.println(GREEN + "  [1]" + RESET +  " 다음 페이지");
            System.out.println(YELLOW + "  [0]" + RESET + " 나가기");
            System.out.println();

            System.out.print(BRIGHT_CYAN + "선택 > " + RESET);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    break;
                case 0:
                    return;
                default:
                    System.out.println(RED + "❌ 잘못된 선택입니다." + RESET);
            }
        } while (true);
    }

    public void goldRankingMenu() {
        rankingController.showMyGoldRanking(Session.getUserId());

        int page = 1;

        do {
            if(! rankingController.showGoldRanking(page++)) return;

            System.out.println();
            System.out.println(GREEN + "  [1]" + RESET +  " 다음 페이지");
            System.out.println(YELLOW + "  [0]" + RESET + " 나가기");
            System.out.println();

            System.out.print(BRIGHT_CYAN + "선택 > " + RESET);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    break;
                case 0:
                    return;
                default:
                    System.out.println(RED + "❌ 잘못된 선택입니다." + RESET);
            }
        } while (true);
    }
}