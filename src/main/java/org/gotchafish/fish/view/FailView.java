package org.gotchafish.fish.view;

public class FailView {
    public static void myFishInfoFail(String message) {
        System.out.println();
        System.out.println("물고기 목록 조회에 실패했습니다.");
        System.out.println("실패 사유 : " + message);
    }

    public static void fishSellFail(String message) {
        System.out.println();
        System.out.println("물고기 판매에 실패했습니다.");
        System.out.println("실패 사유 : " + message);
    }

}
