package org.gotchafish;

import org.gotchafish.dictionary.view.DictionaryView;
import org.gotchafish.fish.view.FishView;
import org.gotchafish.raid.view.RaidView;
import org.gotchafish.ranking.view.RankingView;
import org.gotchafish.rod.view.RodView;
import org.gotchafish.spot.view.SpotView;
import org.gotchafish.user.dto.Session;
import org.gotchafish.user.view.UserView;

import java.util.Scanner;

import static org.gotchafish.common.ConsoleColor.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UserView userView = new UserView();
        RodView rodView = new RodView();
        FishView fishView = new FishView();
        SpotView spotView = new SpotView();
        RankingView rankingView = new RankingView();
        DictionaryView dictionaryView = new DictionaryView();
        RaidView raidView = new RaidView();

        while (true) {
            do {
                printMainMenu();

                System.out.print(BRIGHT_CYAN + "선택 > " + RESET);
                String choice = sc.nextLine();

                switch (choice) {
                    case "1":
                        userView.loginInput();
                        break;
                    case "2":
                        userView.signUp();
                        break;
                    case "3":
                        System.out.println();
                        System.out.println(BRIGHT_YELLOW + "🎣 낚시를 마칩니다. 다음에 또 만나요!" + RESET);
                        sc.close();
                        return;
                    default:
                        System.out.println();
                        System.out.println(RED + "❌ 잘못된 입력입니다. 다시 선택해주세요!" + RESET);
                }
            } while (Session.getUserId() == null);

            // 게임 메뉴
            while (Session.getUserId() != null) {
                printGameMenu();

                System.out.print(BRIGHT_CYAN + "선택 > " + RESET);
                String choice = sc.nextLine();

                switch (choice) {
                    case "1":
                        // 낚시하기
                        break;
                    case "2":
                        fishView.sellFish();
                        break;
                    case "3":
                        rodView.buyRod();
                        break;
                    case "4":
                        spotView.unlockSpot();
                        break;
                    case "5":
                        dictionaryView.showDictionary();
                        break;
                    case "6":
                        rankingView.showRankingMenu();
                        break;
                    case "7":
                        userView.profile();
                        break;
                    case "8":
                        raidView.showRaidMenu();
                        break;
                    case "9":
                        Session.setUserId(null);
                        System.out.println();
                        System.out.println(RED + "👋 로그아웃 되었습니다." + RESET);
                        break;
                    default:
                        System.out.println();
                        System.out.println(BRIGHT_PURPLE + "❌ 잘못된 입력입니다." + RESET);
                }
            }
        }
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "════════════════════════════════════" + RESET);
        System.out.println(BOLD + BRIGHT_YELLOW + "         🐟 잡았다 요놈! 🐟" + RESET);
        System.out.println(BRIGHT_CYAN + "════════════════════════════════════" + RESET);
        System.out.println();
        System.out.println(GREEN + "  1. " + RESET + "로그인");
        System.out.println(GREEN + "  2. " + RESET + "회원가입");
        System.out.println(GREEN + "  3. " + RESET + "종료");
        System.out.println();
    }

    private static void printGameMenu() {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "════════════════════════════════════" + RESET);
        System.out.println(BOLD + BRIGHT_YELLOW + "             🎣 낚시터" + RESET);
        System.out.println(BRIGHT_CYAN + "════════════════════════════════════" + RESET);
        System.out.println();
        System.out.println(GREEN + "  1. " + RESET + "낚시하기");
        System.out.println(GREEN + "  2. " + RESET + "물고기 판매");
        System.out.println(GREEN + "  3. " + RESET + "낚시대 구입");
        System.out.println(GREEN + "  4. " + RESET + "낚시터 상점");
        System.out.println(GREEN + "  5. " + RESET + "물고기 도감");
        System.out.println(GREEN + "  6. " + RESET + "랭킹");
        System.out.println(GREEN + "  7. " + RESET + "내 정보");
        System.out.println(BRIGHT_RED + "  8. " + RESET + "대결");
        System.out.println(YELLOW + "  9. " + RESET + "로그아웃");
        System.out.println();
    }
}