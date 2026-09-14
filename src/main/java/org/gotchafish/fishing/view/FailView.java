package org.gotchafish.fishing.view;

import static org.gotchafish.common.ConsoleColor.*;

public class FailView {
    public static void fishingFail(String message) {
        System.out.println();
        System.out.println(RED + "❌ 낚시에 실패했습니다." + RESET);
        System.out.println(YELLOW + "실패 사유 : " + RESET + message);
    }
}
