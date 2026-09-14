package org.gotchafish.fish.view;

import org.gotchafish.fish.dto.FishDTO;

import java.util.List;

import static org.gotchafish.common.ConsoleColor.*;

public class SuccessView {
    // 보유 물고기 조회 성공
    public static void myFishInfoSuccess(List<FishDTO> fishList) {
        System.out.println("  " + BRIGHT_CYAN + "[ 보유 물고기 ]" + RESET);
        System.out.println();

        for (FishDTO fish : fishList) {
            System.out.println("  " + GREEN + "[" + fish.getFishId() + "] " + RESET + BOLD + fish.getFishName() + " x " + fish.getQuantity() + "개" + RESET);
            System.out.println("  "  + "✦" + RESET + " 희귀도 : " + BOLD + BRIGHT_BLUE + fish.getRarity() + RESET);
            System.out.println("  " +  "✦" + RESET + " 판매가 : " + BOLD + YELLOW + fish.getPrice() + "G" + RESET);
            System.out.println();
        }
    }

    // 물고기 판매 성공
    public static void fishSellSuccess(FishDTO fish, int quantity) {
        System.out.println();
        System.out.println(BRIGHT_GREEN + "✨ " + RESET + BRIGHT_PURPLE + fish.getFishName() + "를 판매했습니다!" + RESET);
        System.out.println();
        System.out.println(YELLOW + "+" + fish.getPrice() * quantity + "G" + RESET);
    }
}
