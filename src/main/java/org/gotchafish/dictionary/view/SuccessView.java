package org.gotchafish.dictionary.view;

import org.gotchafish.dictionary.dto.DictionaryEntryDTO;

import java.util.List;

public class SuccessView {

    // 도감 완성률 출력
    public static void dictionaryRateSuccess(int collectedCount, int total) {
        double rate = (collectedCount * 100.0) / total;

        System.out.println();
        System.out.println("도감 완성률 : " + collectedCount + " / " + total + " (" + rate + "%)");
    }

    // 낚시터 하나의 물고기 목록만 출력
    public static void dictionaryCategorySuccess(String spotName, List<DictionaryEntryDTO> entries) {
        System.out.println();
        System.out.println("[ " + getSpotEmoji(spotName) + " " + spotName + " ]");
        System.out.println();
        System.out.println("물고기         희귀도      상태");
        System.out.println("----------------------------");

        for (DictionaryEntryDTO entry : entries) {
            String fishName = entry.getFishName();
            String rarity = entry.getRarity().toString();
            String status = entry.isCollected() ? "✅  수집" : "❓ 미발견";

            System.out.print(fishName);
            printSpaces(14 - getDisplayWidth(fishName));

            System.out.print(rarity);
            printSpaces(12 - getDisplayWidth(rarity));

            System.out.println(status);
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
