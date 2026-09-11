package org.gotchafish.fishing.view;

import org.gotchafish.fish.dto.FishDTO;
import org.gotchafish.rod.dto.RodDTO;

public class SuccessView {

    public static void fishAppeared(FishDTO fish) {
        System.out.println();
        System.out.println("🌊 물고기를 발견했습니다!");
        System.out.println();
        System.out.println("       🐟[ " + fish.getFishName() + " ]");
        System.out.println();
        System.out.println("희귀도 : " + fish.getRarity());
    }

    public static void rodGet(RodDTO rod) {
        System.out.println();
        System.out.println("🎣 " + rod.getRodName() + "를 사용합니다.");
    }

    public static void fishingSuccess(FishDTO fish, int totalFishing) {
        System.out.println();
        System.out.println("🎉 낚시에 성공했습니다!");
        System.out.println();
        System.out.println("================================");
        System.out.println("          낚시 결과");
        System.out.println("================================");
        System.out.println();
        System.out.println("물고기 : " + fish.getFishName() + " 🐟");
        System.out.println("희귀도 : " + fish.getRarity());
        System.out.println("판매 가격 : " + fish.getPrice() + "G");
        System.out.println();
        System.out.println("총 낚시 횟수 : " + totalFishing + "회");
    }

    public static void fishingCaughtFail(int totalFishing) {
        System.out.println();
        System.out.println("\uD83D\uDCA6앗! 물고기가 도망갔습니다.");
        System.out.println();
        System.out.println("낚시에 실패했습니다.");
        System.out.println();
        System.out.println("총 낚시 횟수 : " + totalFishing + "회");
    }


}
