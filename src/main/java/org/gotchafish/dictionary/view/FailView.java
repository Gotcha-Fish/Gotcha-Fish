package org.gotchafish.dictionary.view;

import static org.gotchafish.common.ConsoleColor.*;

public class FailView {
    public static void dictionaryInfoFail(String message) {
        System.out.println();
        System.out.println(RED + "❌ 도감 조회에 실패했습니다." + RESET);
        System.out.println(YELLOW + "실패 사유 : " + RESET + message);
    }

    public static void noMorePage() {
        System.out.println();
        System.out.println(YELLOW + "더 이상 낚시터가 없습니다." + RESET);
    }
}
