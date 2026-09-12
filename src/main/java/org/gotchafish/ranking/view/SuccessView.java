package org.gotchafish.ranking.view;

import org.gotchafish.ranking.dto.RankingDTO;

import java.util.List;

import static org.gotchafish.common.ConsoleColor.*;

public class SuccessView {
    // 사용자의 낚은 물고기 수 순위 조회 성공
    public static void myFishingRankingSuccess(RankingDTO ranking) {
        System.out.println();
        System.out.println("  " + BLUE + "✦" + RESET + " 내 낚시 순위 : " + BOLD + BRIGHT_PURPLE + ranking.getRank() + RESET +
                "위 (" + BOLD + BRIGHT_GREEN + ranking.getValue() + "마리" + RESET + ")");
        System.out.println();
        System.out.println(BRIGHT_CYAN + "  [ 어획량 상위 랭킹 ]" + RESET);
    }

    // 낚은 물고기 수 전체 순위 조회 성공
    public static void fishingRankingSuccess(List<RankingDTO> rankings) {
        System.out.println();
        System.out.println("  " + BOLD + "순위    닉네임           낚은 물고기" + RESET);
        System.out.println(BRIGHT_CYAN + "  " + "═".repeat(36) + RESET);

        for (RankingDTO ranking : rankings) {
            String rank = String.valueOf(ranking.getRank());
            String nickname = ranking.getNickname();
            String fishCount = ranking.getValue() + "마리";

            System.out.print("  " + rank);
            printSpaces(8 - getDisplayWidth(rank));

            System.out.print(nickname);
            printSpaces(14 - getDisplayWidth(nickname));

            printSpaces(14 - getDisplayWidth(fishCount));
            System.out.println(BRIGHT_GREEN + fishCount + RESET);
        }
    }

    // 사용자의 보유 골드 순위 조회 성공
    public static void myGoldRankingSuccess(RankingDTO ranking) {
        System.out.println();
        System.out.println("  " + BRIGHT_YELLOW + "✦" + RESET + " 내 골드 순위 : " + BOLD + BRIGHT_PURPLE + ranking.getRank() + RESET +
                "위 (" + BOLD + YELLOW + ranking.getValue()  + "G" + RESET + ")");
        System.out.println();
        System.out.println(BRIGHT_CYAN + "  [ 보유 골드 상위 랭킹 ]" + RESET);
    }

    // 보유 골드 전체 순위 조회 성공
    public static void goldRankingSuccess(List<RankingDTO> rankings) {
        System.out.println();
        System.out.println("  " + BOLD + "순위    닉네임             보유 골드" + RESET);
        System.out.println(BRIGHT_CYAN + "  " + "═".repeat(36) + RESET);

        for (RankingDTO ranking : rankings) {
            String rank = String.valueOf(ranking.getRank());
            String nickname = ranking.getNickname();
            String gold = ranking.getValue() + "G";

            System.out.print("  " + rank);
            printSpaces(8 - getDisplayWidth(rank));

            System.out.print(nickname);
            printSpaces(14 - getDisplayWidth(nickname));

            printSpaces(14 - getDisplayWidth(gold));
            System.out.println(YELLOW + gold + RESET);
        }
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