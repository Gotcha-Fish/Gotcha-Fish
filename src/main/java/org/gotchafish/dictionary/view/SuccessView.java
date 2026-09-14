package org.gotchafish.dictionary.view;

import org.gotchafish.dictionary.dto.DictionaryEntryDTO;

import java.util.List;

import static org.gotchafish.common.ConsoleColor.*;

public class SuccessView {

    // 도감 완성률 출력
    public static void dictionaryRateSuccess(int collectedCount, int total) {
        double rate = (collectedCount * 100.0) / total;

        System.out.println();
        System.out.println("  " + BRIGHT_CYAN + "[ 도감 완성률 ]" + RESET);
        System.out.println("  " + "✦" + RESET + " " + BOLD + YELLOW + collectedCount + " / " + total + RESET
                + " (" + BOLD + BRIGHT_BLUE + String.format("%.1f", rate) + "%" + RESET + ")");
    }

    // 낚시터 하나의 물고기 목록만 출력
    public static void dictionaryCategorySuccess(String spotName, List<DictionaryEntryDTO> entries) {
        System.out.println();
        System.out.println("  " + BRIGHT_CYAN + "[ " + getSpotEmoji(spotName) + " " + spotName + " ]" + RESET);
        System.out.println();
        System.out.println(BOLD + "물고기         희귀도      상태" + RESET);
        System.out.println(BRIGHT_CYAN + "-".repeat(30) + RESET);

        for (DictionaryEntryDTO entry : entries) {
            String fishName = entry.getFishName();
            String rarity = entry.getRarity().toString();
            boolean collected = entry.isCollected();

            System.out.print(BOLD + fishName + RESET);
            printSpaces(14 - getDisplayWidth(fishName));

            System.out.print(BRIGHT_BLUE + rarity + RESET);
            printSpaces(12 - getDisplayWidth(rarity));

            if (collected) {
                System.out.println(GREEN + "✅  수집" + RESET);
            } else {
                System.out.println(RED + "❓ 미발견" + RESET);
            }
        }

        System.out.println(BRIGHT_CYAN + "-".repeat(30) + RESET);
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

    private static String getSpotEmoji(String spotName) {
        return switch (spotName) {
            case "강" -> "🏞";
            case "바다" -> "🌊";
            case "심해" -> "🌌";
            case "용암 지대" -> "🌋";
            case "신비의 호수" -> "🔮";
            default -> "🐟";
        };
    }
}
