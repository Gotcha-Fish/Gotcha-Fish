package org.gotchafish.rod.view;

import org.gotchafish.rod.dto.RodDTO;
import org.gotchafish.user.dto.UserDTO;

import java.util.List;

import static org.gotchafish.common.ConsoleColor.*;

public class SuccessView {
    // 낚시대 상점 조회 성공
    public static void rodShopInfoSuccess(UserDTO user, List<RodDTO> rodList) {
        System.out.println("  보유 골드 : " + YELLOW + user.getGold() + "G" + RESET);
        System.out.println();

        System.out.println(BRIGHT_CYAN + "  [ 낚시대 목록 ]" + RESET);
        System.out.println();

        for (RodDTO rod : rodList) {
            System.out.println("  " + GREEN + rod.getRodId() + ". " + RESET + BOLD  + rod.getRodName() + RESET);
            System.out.println("  "  + "✦" + RESET + " 포획 확률 : " + BOLD + BRIGHT_BLUE + rod.getCatchProbability() + "%" + RESET);
            System.out.println("  "  + "✦" + RESET + " 가격 : " + BOLD + YELLOW + rod.getPrice() + "G" + RESET);
            System.out.println("  "  + "✦" + RESET + " 보유량 : " + BOLD + rod.getQuantity() + "개" + RESET);
            System.out.println();
        }
    }

    // 낚시대 구입 성공
    public static void rodBuySuccess(RodDTO rod, int quantity) {
        System.out.println();
        System.out.println(BRIGHT_GREEN + "✨ " + RESET + BRIGHT_PURPLE + rod.getRodName() + "를 구입했습니다!" + RESET);
        System.out.println();
        System.out.println(YELLOW + "-" + rod.getPrice() * quantity + "G" + RESET);
    }

    // 내 낚시대 조회 성공
    public static void MyRodsInfoSuccess(List<RodDTO> rodList) {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "  [ 낚시대 선택 ]" + RESET);
        System.out.println();

        for (RodDTO rod : rodList) {
            System.out.println("  " + GREEN + rod.getRodId() + ". " + RESET + BOLD  + rod.getRodName() + RESET);
            System.out.println("  "  + "✦" + RESET + " 포획 확률 : " + BOLD + BRIGHT_BLUE + rod.getCatchProbability() + "%" + RESET);
            System.out.println("  "  + "✦" + RESET + " 가격 : " + BOLD + YELLOW + rod.getPrice() + "G" + RESET);
            System.out.println("  "  + "✦" + RESET + " 보유량 : " + BOLD + rod.getQuantity() + "개" + RESET);
            System.out.println();
        }
    }

    // 낚시대 사용 성공
    public static void rodUseSuccess(RodDTO rod) {
        System.out.println();
        System.out.println(BRIGHT_GREEN + "🎣 " + RESET + rod.getRodName() + "를 사용합니다." + RESET);
    }
}