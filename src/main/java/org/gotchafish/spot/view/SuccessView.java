package org.gotchafish.spot.view;

import org.gotchafish.spot.dto.SpotDTO;
import org.gotchafish.user.dto.UserDTO;

import java.util.List;

import static org.gotchafish.common.ConsoleColor.*;

public class SuccessView {
    // 낚시터 조회 성공
    public static void spotSuccessView(UserDTO user, List<SpotDTO> spots) {
        System.out.println("  보유 골드 : " + YELLOW + user.getGold() + "G" + RESET);
        System.out.println();

        System.out.println("  " + BRIGHT_CYAN + "[ 낚시터 목록 ]" + RESET);
        System.out.println();

        for (SpotDTO spot : spots) {
            System.out.println("  " + GREEN + "[" + spot.getSpotId() + "] " + RESET + BOLD  + spot.getSpotName() + RESET);

            if (spot.isUnlocked()) {
                System.out.println("  "  + "✦" + RESET + " 상태 : " + BOLD + BRIGHT_GREEN + "\uD83D\uDD13 해제" + RESET);
            } else {
                System.out.println("  "  + "✦" + RESET + " 가격 : " + BOLD + YELLOW + spot.getUnlockPrice() + "G" + RESET);
                System.out.println("  "  + "✦" + RESET + " 상태 : " + BOLD + BRIGHT_RED + "\uD83D\uDD12 잠금" + RESET);
            }
            System.out.println();
        }
    }

    // 낚시터 잠금 해제 성공
    public static void unlockSuccessView(String spotName, int unlockPrice) {
        System.out.println();
        System.out.println(BRIGHT_GREEN + "✨ " + RESET + BRIGHT_PURPLE + spotName + "낚시터 잠금을 해제했습니다!" + RESET);
        System.out.println();
        System.out.println(YELLOW + "-" + unlockPrice + "G" + RESET);
    }

    public static void selectSpotView(List<SpotDTO> spots) {
        System.out.println();
        System.out.println("  " + BRIGHT_CYAN + "[ 낚시터 선택 ]" + RESET);
        System.out.println();

        for (SpotDTO spot : spots) {
            String status = spot.isUnlocked() ? (BRIGHT_GREEN + "🔓 해제") : (BRIGHT_RED + "🔒 잠금");
            System.out.println("  " + GREEN + "[" + spot.getSpotId() + "] " + RESET + BOLD  + spot.getSpotName() + " [" + status + "] " + RESET);
        }
        System.out.println();
    }
}
