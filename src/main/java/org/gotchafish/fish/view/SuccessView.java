package org.gotchafish.fish.view;

import org.gotchafish.fish.dto.FishDTO;

import java.util.List;

public class SuccessView {
    // 보유 물고기 조회 성공
    public static void myFishInfoSuccess(List<FishDTO> fishList) {
        System.out.println();
        System.out.println("[ 보 유  물 고 기  목 록 ]");
        System.out.println();

        for (FishDTO fish : fishList) {
            System.out.println(fish.getFishId() + ". " + fish.getFishName() + " X " + fish.getQuantity());
            System.out.println("   희귀도 : " + fish.getRarity());
            System.out.println("   가격 : " + fish.getPrice() + "G");
            System.out.println();
        }
    }

    public static void fishSellSuccess(FishDTO fish, int quantity) {
        System.out.println();
        System.out.println(fish.getFishName() + " " + quantity + " 마리를 판매했습니다!");
        System.out.println();
        System.out.println("+" + fish.getPrice() * quantity + "G");
    }
}
