package org.gotchafish.rod.view;

public class FailView {
    // 낚시대 상점 조회 실패
    public static void rodShopInfoFail(String message) {
        System.out.println();
        System.out.println("상점 조회에 실패했습니다.");
        System.out.println("실패 사유 : " + message);
    }

    // 낚시대 구입 실패
    public static void rodBuyFail(String message) {
        System.out.println();
        System.out.println("낚시대 구입에 실패했습니다.");
        System.out.println("실패 사유 : " + message);
    }
}