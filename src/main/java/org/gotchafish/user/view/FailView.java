package org.gotchafish.user.view;

public class FailView {
    // 회원가입 실패
    public static void signUpFail(String message) {
        System.out.println();
        System.out.println("회원가입 실패했습니다.");
        System.out.println("실패 사유 : " + message);
    }

    // 로그인 실패
    public static void loginFail(String message) {
        System.out.println();
        System.out.println("로그인 실패했습니다.");
        System.out.println("실패 사유 : " + message);
    }

    // 내 정보 조회 실패
    public static void profileFail(String message) {
        System.out.println();
        System.out.println("내 정보 불러오기에 실패했습니다.");
        System.out.println("실패 사유 : " + message);
    }

    // 닉네임 변경 실패
    public static void changeNicknameFail(String message) {
        System.out.println();
        System.out.println("닉네임 변경에 실패했습니다.");
        System.out.println("실패 사유 : " + message);
    }

    // 비밀번호 변경 실패
    public static void changePasswordFail(String message) {
        System.out.println();
        System.out.println("비밀번호가 변경되었습니다.");
        System.out.println("실패 사유 : " + message);
    }
}