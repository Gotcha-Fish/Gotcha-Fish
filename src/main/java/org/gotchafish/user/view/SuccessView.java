package org.gotchafish.user.view;

import org.gotchafish.user.dto.UserDTO;

import static org.gotchafish.common.ConsoleColor.*;

public class SuccessView {
    /// 회원가입 성공
    public static void signUpSuccess(UserDTO user) {
        System.out.println();
        System.out.println(BRIGHT_GREEN + "✨ " + RESET + BRIGHT_PURPLE + user.getNickname() + "님 회원가입이 완료되었습니다!" + RESET);
        System.out.println();
        System.out.println(BOLD + BRIGHT_CYAN + "🎁 가입 보상" + RESET);
        System.out.println("골드 : " + YELLOW + 100 + "G" + RESET);
        System.out.println("낚시대 : " + YELLOW + "초급 낚시대" + " × 10" + RESET);
    }

    // 로그인 성공
    public static void loginSuccess(UserDTO user) {
        System.out.println();
        System.out.println(BRIGHT_GREEN + "✨ " + RESET + BRIGHT_PURPLE + "로그인 성공!" + RESET);
        System.out.println("환영합니다. " + BOLD + user.getNickname() + RESET + "님!");
    }

    /// 출석 보상 획득
    public static void attendanceReward(UserDTO user) {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "[ 오늘의 출석 보상 ]" + RESET);
        System.out.println(BRIGHT_GREEN + "🎁 " + RESET + "출석 보상 " + YELLOW + 10 + "G" + RESET + "를 획득했습니다!");
        System.out.println(BRIGHT_GREEN + "🎁 " + RESET + "출석 보상 " + YELLOW + "초급 낚시대 1개" + RESET + "를 획득했습니다!");
        System.out.println();
    }

    // 프로필 조회 성공
    public static void profileSuccess(UserDTO user) {
        System.out.println(BOLD + BRIGHT_PURPLE + "👤 닉네임" + RESET);
        System.out.println("   " + BOLD + user.getNickname() + RESET);

        System.out.println();
        System.out.println(BOLD + BLUE + "🐟 총 잡은 물고기" + RESET);
        System.out.println("   " + BOLD + user.getTotalFishing() + "마리" + RESET);

        System.out.println();
        System.out.println( BOLD + YELLOW + "💰 보유 골드" + RESET);
        System.out.println("   " + BOLD  + user.getGold() + "G" + RESET);
    }

    // 닉네임 변경 성공
    public static void changeNicknameSuccess() {
        System.out.println();
        System.out.println(BRIGHT_GREEN + "✨ " + RESET + BRIGHT_PURPLE + "닉네임이 변경되었습니다." + RESET);
    }

    // 비밀번호 변경 성공
    public static void changePasswordSuccess() {
        System.out.println();
        System.out.println(BRIGHT_GREEN + "✨ " + RESET + BRIGHT_PURPLE + "비밀번호가 변경되었습니다." + RESET);
    }
}