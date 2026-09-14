package org.gotchafish.rod.view;

import static org.gotchafish.common.ConsoleColor.*;

public class FailView {
    // 낚시대 상점 조회 실패
    public static void rodShopInfoFail(String message) {
        System.out.println();
        System.out.println(RED + "❌ 상점 조회에 실패했습니다." + RESET);
        System.out.println(YELLOW + "실패 사유 : " + RESET + message);
    }

    // 낚시대 구입 실패
    public static void rodBuyFail(String message) {
        System.out.println();
        System.out.println(RED + "❌ 낚시대 구입에 실패했습니다." + RESET);
        System.out.println(YELLOW + "실패 사유 : " + RESET + message);
    }

    // 내 낚시대 조회 실패
    public static void myRodsInfoFail(String message) {
        System.out.println();
        System.out.println(RED + "❌ 낚시대 목록 조회에 실패했습니다." + RESET);
        System.out.println(YELLOW + "실패 사유 : " + RESET + message);
    }

    // 낚시대 사용 실패
    public static void rodUseFail(String message) {
        System.out.println();
        System.out.println(RED + "❌ 낚시대 사용에 실패했습니다." + RESET);
        System.out.println(YELLOW + "실패 사유 : " + RESET + message);
    }
}