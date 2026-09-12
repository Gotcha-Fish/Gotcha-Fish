package org.gotchafish.user.view;

import static org.gotchafish.common.ConsoleColor.*;

public class FailView {
    // 회원가입 실패
    public static void signUpFail(String message) {
        System.out.println();
        System.out.println(RED + "❌ 회원가입에 실패했습니다." + RESET);
        System.out.println(YELLOW + "실패 사유 : " + RESET + message);
    }

    // 로그인 실패
    public static void loginFail(String message) {
        System.out.println();
        System.out.println(RED + "❌ 로그인에 실패했습니다." + RESET);
        System.out.println(YELLOW + "실패 사유 : " + RESET + message);
    }

    // 내 정보 조회 실패
    public static void profileFail(String message) {
        System.out.println();
        System.out.println(RED + "❌ 내 정보 조회에 실패했습니다." + RESET);
        System.out.println(YELLOW + "실패 사유 : " + RESET + message);
    }

    // 닉네임 변경 실패
    public static void changeNicknameFail(String message) {
        System.out.println();
        System.out.println(RED + "❌ 닉네임 변경에 실패했습니다." + RESET);
        System.out.println(YELLOW + "실패 사유 : " + RESET + message);
    }

    // 비밀번호 변경 실패
    public static void changePasswordFail(String message) {
        System.out.println();
        System.out.println(RED + "❌ 비밀번호 변경에 실패했습니다." + RESET);
        System.out.println(YELLOW + "실패 사유 : " + RESET + message);
    }
}