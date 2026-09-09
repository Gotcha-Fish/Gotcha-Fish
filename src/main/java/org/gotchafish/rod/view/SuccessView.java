package org.gotchafish.rod.view;

import org.gotchafish.rod.dto.RodDTO;
import org.gotchafish.user.dto.UserDTO;

import java.util.List;

public class SuccessView {
    // 낚시대 상점 조회 성공
    public static void rodShopInfoSuccess(UserDTO user, List<RodDTO> rodList) {
        System.out.println("보유 골드 : " + user.getGold() + "G");
        System.out.println();

        System.out.println("[ 낚시대 목록 ]");
        System.out.println();

        for (RodDTO rod : rodList) {
            System.out.println(rod.getRodId() + ". " + rod.getRodName());
            System.out.println("   포획 확률 : " + rod.getCatchProbability() + "%");
            System.out.println("   가격 : " + rod.getPrice() + "G");
            System.out.println("   보유 : " + rod.getQuantity() + "개");
            System.out.println();
        }
    }

    // 낚시대 구입 성공
    public static void rodBuySuccess(RodDTO rod, int quantity) {
        System.out.println();
        System.out.println(rod.getRodName() + "를 구입했습니다!");
        System.out.println();
        System.out.println("-" + rod.getPrice() * quantity + "G");
    }

    // 내 낚시대 조회 성공
    public static void MyRodsInfoSuccess(List<RodDTO> rodList) {
        System.out.println();
        System.out.println("[ 낚시대 선택 ]");
        System.out.println();

        for (RodDTO rod : rodList) {
            System.out.println(rod.getRodId() + ". " + rod.getRodName() + " X " + rod.getQuantity());
            System.out.println("   포획 확률 : " + rod.getCatchProbability() + "%");
            System.out.println();
        }
    }

    // 낚시대 사용 성공
    public static void rodUseSuccess(RodDTO rod) {
        System.out.println();
        System.out.println("🎣 " + rod.getRodName() + "를 사용합니다.");
    }
}