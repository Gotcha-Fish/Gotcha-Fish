package org.gotchafish.fish.view;


import static org.gotchafish.common.ConsoleColor.*;

public class FailView {
    public static void myFishInfoFail(String message) {
        System.out.println();
        System.out.println(RED + "❌ 물고기 목록 조회에 실패했습니다." + RESET);
        System.out.println(YELLOW + "실패 사유 : " + RESET + message);
    }

    public static void fishSellFail(String message) {
        System.out.println();
        System.out.println(RED + "❌ 물고기 판매에 실패했습니다." + RESET);
        System.out.println(YELLOW + "실패 사유 : " + RESET + message);
    }

}
