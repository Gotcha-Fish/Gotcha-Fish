package org.gotchafish.ranking.view;

import static org.gotchafish.common.ConsoleColor.*;
import static org.gotchafish.common.ConsoleColor.RESET;

public class FailView {
    // 사용자의 순위 조회 실패
    public static void myRankingFail() {
        System.out.println();
        System.out.println(RED + "❌ 회원 랭킹 조회에 실패했습니다." + RESET);
    }
}
