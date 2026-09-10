package org.gotchafish.user.view;

import org.gotchafish.user.dto.UserDTO;

public class SuccessView {
    // 회원가입 성공
    public static void signUpSuccess(UserDTO user) {
        System.out.println();
        System.out.println(user.getNickname() + "님 회원가입이 완료되었습니다!");
        System.out.println();
        System.out.println("🎁 가입 보상");
        System.out.println("골드 : " + 100 + "G");
        System.out.println("낚시대 : " + "초급 낚시대" + " × 10");
    }

    // 로그인 성공
    public static void loginSuccess(UserDTO user) {
        System.out.println();
        System.out.println("로그인 성공!");
        System.out.println("환영합니다. " + user.getNickname() + "님!");
    }

    // 출석 보상 획득
    public static void attendanceReward(UserDTO user) {
        System.out.println();
        System.out.println("[ 오늘의 출석 보상 ]");
        System.out.println("🎁 출석 보상 " + 10 + "G를 획득했습니다!");
        System.out.println();
        System.out.println("현재 골드 : " + user.getGold() + "G");
    }

    // 프로필 조회 성공
    public static void profileSuccess(UserDTO user) {
        System.out.println("👤 닉네임");
        System.out.println("   " + user.getNickname());

        System.out.println();
        System.out.println("🐟 총 잡은 물고기");
        System.out.println("   " + user.getTotalFishing() + "마리");

        System.out.println();
        System.out.println("💰 보유 골드");
        System.out.println("   " + user.getGold() + "G");
    }

    // 닉네임 변경 성공
    public static void changeNicknameSuccess() {
        System.out.println();
        System.out.println("닉네임이 변경되었습니다.");
    }

    // 비밀번호 변경 성공
    public static void changePasswordSuccess() {
        System.out.println();
        System.out.println("비밀번호가 변경되었습니다.");
    }
}