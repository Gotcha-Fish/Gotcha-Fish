package org.gotchafish.spot.view;

import org.gotchafish.spot.dto.SpotDTO;
import org.gotchafish.user.dto.UserDTO;

import java.util.List;

public class SuccessView {
    // 낚시터 조회 성공
    public static void spotSuccessView(UserDTO user, List<SpotDTO> spots) {
        System.out.println("보유 골드 : " + user.getGold() + "G");
        System.out.println();
        System.out.println("[ 낚시터 목록 ]");
        System.out.println();

        for (SpotDTO spot : spots) {
            System.out.println(spot.getSpotId() + ". " + spot.getSpotName());

            if (spot.isUnlocked()) {
                System.out.println("   상태 :  \uD83D\uDD13 해제");
            } else {
                System.out.println("   가격 : " + spot.getUnlockPrice() + "G");
                System.out.println("   상태 :  \uD83D\uDD12 잠금");
            }
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

    public static void selectSpotView(List<SpotDTO> spots) {
        System.out.println();
        System.out.println("[ 낚시터 선택 ]");
        System.out.println();

        for (SpotDTO spot : spots) {
            String status = spot.isUnlocked() ? "🔓" : "🔒";
            System.out.println(spot.getSpotId() + ". " + spot.getSpotName() + " (상태 : " + status + ")");
        }
        System.out.println();
    }
}
