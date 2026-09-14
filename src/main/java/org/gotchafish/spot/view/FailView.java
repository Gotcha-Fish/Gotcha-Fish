package org.gotchafish.spot.view;

import static org.gotchafish.common.ConsoleColor.*;

public class FailView {

    public static void spotListFail(String message) {
        System.out.println();
        System.out.println(RED + "❌ 낚시터 조회에 실패했습니다." + RESET);
        System.out.println(YELLOW + "실패 사유 : " + RESET + message);
    }

    public static void unlockSpotFail(String message) {
        System.out.println();
        System.out.println(RED + "❌ 낚시터 잠금 해제에 실패했습니다." + RESET);
        System.out.println(YELLOW + "실패 사유 : " + RESET + message);
    }

    public static void selectSpotFail(String message) {
        System.out.println();
        System.out.println(RED + "❌ 낚시터 선택에 실패했습니다." + RESET);
        System.out.println(YELLOW + "실패 사유 : " + RESET + message);
    }
}
