package org.gotchafish.spot.view;

import org.gotchafish.spot.dto.SpotDTO;

import java.util.List;

public class SuccessView {
    // 낚시터 조회 성공
    public static void spotSuccessView(List<SpotDTO> spots) {
        System.out.println("================================");
        System.out.println("          낚시터 상점");
        System.out.println("================================");
        System.out.println();
        System.out.println("[ 낚시터 목록 ]");
        System.out.println();

        for (SpotDTO spot : spots) {
            System.out.println(spot.getSpotId() + ". " + spot.getSpotName());
            System.out.println("   가격 : " + spot.getUnlockPrice() + "G");
            System.out.println();
        }
    }

    // 낚시터 잠금 해제 성공
    public static void unlockSuccessView(String spotName, int unlockPrice) {
        System.out.println();
        System.out.println(spotName + " 낚시터 잠금을 해제했습니다!");
        System.out.println();
        System.out.println("-" + unlockPrice + "G");
    }
}
