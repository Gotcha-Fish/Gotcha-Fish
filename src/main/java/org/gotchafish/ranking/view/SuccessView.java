package org.gotchafish.ranking.view;

import org.gotchafish.ranking.dto.RankingDTO;

import java.util.List;

public class SuccessView {
    // 사용자의 낚은 물고기 수 순위 조회 성공
    public static void myFishingRankingSuccess(RankingDTO ranking) {
        System.out.println();
        System.out.println("[ 낚은 물고기 수 ]");
        System.out.println();

        System.out.println("현재 나의 순위 : " + ranking.getRank() + "위 (" + ranking.getValue() + "마리)");
    }

    // 낚은 물고기 수 전체 순위 조회 성공
    public static void fishingRankingSuccess(List<RankingDTO> rankings) {
        System.out.println();
        System.out.println("순위    닉네임       낚은 물고기");
        System.out.println("--------------------------------");

        for (RankingDTO ranking : rankings) {
            String rank = String.valueOf(ranking.getRank());
            String nickname = ranking.getNickname();
            String fishCount = ranking.getValue() + "마리";

            System.out.print(rank);
            printSpaces(8 - getDisplayWidth(rank));

            System.out.print(nickname);
            printSpaces(14 - getDisplayWidth(nickname));

            printSpaces(10 - getDisplayWidth(fishCount));
            System.out.println(fishCount);
        }

        System.out.println("--------------------------------");
    }

    // 사용자의 보유 골드 순위 조회 성공
    public static void myGoldRankingSuccess(RankingDTO ranking) {
        System.out.println();
        System.out.println("[ 보유 골드 ]");
        System.out.println();

        System.out.println("현재 나의 순위 : " + ranking.getRank() + "위 (" + ranking.getValue() + "G)");
    }

    // 보유 골드 전체 순위 조회 성공
    public static void goldRankingSuccess(List<RankingDTO> rankings) {
        System.out.println();
        System.out.println("순위    닉네임         보유 골드");
        System.out.println("--------------------------------");

        for (RankingDTO ranking : rankings) {
            String rank = String.valueOf(ranking.getRank());
            String nickname = ranking.getNickname();
            String gold = ranking.getValue() + "G";

            System.out.print(rank);
            printSpaces(8 - getDisplayWidth(rank));

            System.out.print(nickname);
            printSpaces(14 - getDisplayWidth(nickname));

            printSpaces(10 - getDisplayWidth(gold));
            System.out.println(gold);
        }

        System.out.println("--------------------------------");
    }

    private static int getDisplayWidth(String text) {
        int width = 0;

        for (char c : text.toCharArray()) {
            if (c >= 0xAC00 && c <= 0xD7A3) width += 2;
            else width += 1;
        }

        return width;
    }

    private static void printSpaces(int count) {
        System.out.print(" ".repeat(Math.max(0, count)));
    }
}