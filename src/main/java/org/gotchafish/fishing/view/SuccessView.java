package org.gotchafish.fishing.view;

import org.gotchafish.fish.dto.FishDTO;
import org.gotchafish.rod.dto.RodDTO;

import static org.gotchafish.common.ConsoleColor.*;

public class SuccessView {

    public static void fishAppeared(FishDTO fish) {
        System.out.println();
        System.out.println(BRIGHT_GREEN + "🌊 물고기를 발견했습니다!" + RESET);
        System.out.println();
        System.out.println("  " + BOLD + "🐟[ " + fish.getFishName() + " ]" + RESET);
        System.out.println();
        System.out.println("  " + "✦" + RESET + " 희귀도 : " + BOLD + BRIGHT_BLUE + fish.getRarity() + RESET);
    }

    public static void rodGet(RodDTO rod) {
        System.out.println();
        System.out.println(BRIGHT_GREEN + "🎣 " + RESET + rod.getRodName() + "를 사용합니다." + RESET);
    }

    public static void fishingSuccess(FishDTO fish, int totalFishing) {
        System.out.println();
        System.out.println(BRIGHT_GREEN + "🎉 낚시에 성공했습니다!" + RESET);
        System.out.println();
        System.out.println(BRIGHT_CYAN + "═".repeat(40) + RESET);
        System.out.println(BOLD + BRIGHT_YELLOW + "             낚시 결과" + RESET);
        System.out.println(BRIGHT_CYAN + "═".repeat(40) + RESET);
        System.out.println();
        System.out.println("  " + "✦" + RESET + " 물고기 : " + BOLD + fish.getFishName() + " 🐟" + RESET);
        System.out.println("  " + "✦" + RESET + " 희귀도 : " + BOLD + BRIGHT_BLUE + fish.getRarity() + RESET);
        System.out.println("  " + "✦" + RESET + " 판매 가격 : " + BOLD + YELLOW + fish.getPrice() + "G" + RESET);
        System.out.println();
        System.out.println(GREEN + "총 낚시 횟수 : " + RESET + totalFishing + "회");
    }

    public static void fishingCaughtFail(int totalFishing) {
        System.out.println();
        System.out.println(RED + "💦앗! 물고기가 도망갔습니다." + RESET);
        System.out.println();
        System.out.println(RED + "낚시에 실패했습니다." + RESET);
        System.out.println();
        System.out.println(GREEN + "총 낚시 횟수 : " + RESET + totalFishing + "회");
    }
}
