package org.gotchafish;

import org.gotchafish.dictionary.view.DictionaryView;
import org.gotchafish.fish.view.FishView;
import org.gotchafish.ranking.view.RankingView;
import org.gotchafish.rod.view.RodView;
import org.gotchafish.spot.view.SpotView;
import org.gotchafish.user.dto.Session;
import org.gotchafish.user.view.UserView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UserView userView = new UserView();
        RodView rodView = new RodView();
        FishView fishView = new FishView();
        SpotView spotView = new SpotView();
        RankingView rankingView = new RankingView();
        DictionaryView dictionaryView = new DictionaryView();

        // 프로그램 전체 반복
        while (true) {
            // 로그인 전 메뉴
            do {
                System.out.println();
                System.out.println("================================");
                System.out.println("         잡았다 요놈!");
                System.out.println("================================");
                System.out.println();
                System.out.println("1. 로그인");
                System.out.println("2. 회원가입");
                System.out.println("3. 종료");
                System.out.println();
                System.out.print("선택 : ");

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
                        System.out.println("게임을 종료합니다.");
                        sc.close();
                        return;
                    default:
                        System.out.println();
                        System.out.println("잘못된 입력입니다.");
                }
            } while (Session.getUserId() == null);

            // 게임 메뉴
            while (Session.getUserId() != null) {
                System.out.println();
                System.out.println("================================");
                System.out.println("1. 낚시하기");
                System.out.println("2. 물고기 판매");
                System.out.println("3. 낚시대 구입");
                System.out.println("4. 낚시터 상점");
                System.out.println("5. 물고기 도감");
                System.out.println("6. 랭킹");
                System.out.println("7. 내 정보");
                System.out.println("8. 대결");
                System.out.println("9. 로그아웃");
                System.out.println("================================");
                System.out.println();
                System.out.print("선택 : ");

                String choice = sc.nextLine();

                switch (choice) {
                    case "1":
                        // 낚시하기
                        break;
                    case "2":
                        // 물고기 판매
                        fishView.sellFish();
                        break;
                    case "3":
                        // 낚시대 구입
                        rodView.buyRod();
                        break;
                    case "4":
                        // 낚시터 상점
                        spotView.unlockSpot();
                        break;
                    case "5":
                        // 물고기 도감
                        dictionaryView.showDictionary();
                        break;
                    case "6":
                        // 랭킹
                        rankingView.showRankingMenu();
                        break;
                    case "7":
                        // 내 정보
                        userView.profile();
                        break;
                    case "8":
                        // 대결
                        break;
                    case "9":
                        Session.setUserId(null);
                        System.out.println();
                        System.out.println("로그아웃 되었습니다.");
                        break;
                    default:
                        System.out.println();
                        System.out.println("잘못된 입력입니다.");
                }
            }
        }
    }
}