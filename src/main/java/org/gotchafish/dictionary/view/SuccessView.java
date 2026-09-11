package org.gotchafish.dictionary.view;

import org.gotchafish.dictionary.dto.DictionaryEntryDTO;

import java.util.List;

public class SuccessView {
    public static void dictionaryInfoSuccess(List<DictionaryEntryDTO> entries) {
        int total = entries.size();
        int collectedCount = 0;

        for (DictionaryEntryDTO entry : entries) {
            if (entry.isCollected()) {
                collectedCount++;
            }
        }

        double rate = (collectedCount * 100.0) / total;

        System.out.println();
        System.out.println("도감 완성률 : " + collectedCount + " / " + total + " (" + rate + "%)");
        System.out.println();
        System.out.println("[ 물고기 목록 ]");
        System.out.println();

        for (DictionaryEntryDTO entry : entries) {
            System.out.println(entry.getFishName());
            System.out.println("   희귀도 : " + entry.getRarity());
            System.out.println("   발견 장소 : " + entry.getSpotName());

            if (entry.isCollected()) System.out.println("   상태 : ✅ 수집");
            else System.out.println("   상태 : ❓  미발견");
            System.out.println();
        }
    }
}
