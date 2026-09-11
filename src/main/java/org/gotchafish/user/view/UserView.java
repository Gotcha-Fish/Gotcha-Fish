package org.gotchafish.user.view;

import org.gotchafish.user.dto.Session;
import org.gotchafish.user.dto.UserDTO;
import org.gotchafish.user.controller.UserController;

import java.util.Scanner;

import static org.gotchafish.common.ConsoleColor.*;

public class UserView {
    private final Scanner sc = new Scanner(System.in);

    private final UserController controller = UserController.getInstance();

    public void signUp() {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "════════════════════════════════════" + RESET);
        System.out.println(BOLD + BRIGHT_YELLOW + "             🐣 회원가입" + RESET);
        System.out.println(BRIGHT_CYAN + "════════════════════════════════════" + RESET);
        System.out.println();

        System.out.print(GREEN + "아이디 : " + RESET);
        String loginId = sc.nextLine();

        System.out.println();
        System.out.print(GREEN + "비밀번호 : " + RESET);
        String password = sc.nextLine();

        System.out.println();
        System.out.print(GREEN + "닉네임 : " + RESET);
        String nickname = sc.nextLine();

        UserDTO user = new UserDTO(loginId, password, nickname);

        controller.signUp(user);
    }

    public void loginInput() {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "════════════════════════════════════" + RESET);
        System.out.println(BOLD + BRIGHT_YELLOW + "            👋 로그인" + RESET);
        System.out.println(BRIGHT_CYAN + "════════════════════════════════════" + RESET);
        System.out.println();

        System.out.print(GREEN + "아이디 : " + RESET);
        String loginId = sc.nextLine();

        System.out.println();
        System.out.print(GREEN + "비밀번호 : " + RESET);
        String password = sc.nextLine();

        controller.login(loginId, password);
    }

    public void profile() {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "════════════════════════════════════" + RESET);
        System.out.println(BOLD + BRIGHT_YELLOW + "            ✨ 내 정보" + RESET);
        System.out.println(BRIGHT_CYAN + "════════════════════════════════════" + RESET);
        System.out.println();

        if (!controller.profile(Session.getUserId())) return;

        System.out.println();
        System.out.println(GREEN + "  1. " + RESET + "닉네임 수정");
        System.out.println(GREEN + "  2. " + RESET + "비밀번호 수정");
        System.out.println(YELLOW + "  0. " + RESET + "뒤로가기");
        System.out.println();

        System.out.print(BRIGHT_CYAN + "선택 > " + RESET);
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                changeNicknameInput();
                break;
            case 2:
                changePasswordInput();
                break;
            case 0:
            default:
        }
    }

    private void changeNicknameInput() {
        System.out.println();
        System.out.print(GREEN + "새 닉네임 : " + RESET);
        String nickname = sc.nextLine();

        System.out.println();
        System.out.print(GREEN + "비밀번호 : " + RESET);
        String password = sc.nextLine();

        controller.changeNickname(Session.getUserId(), nickname, password);
    }

    private void changePasswordInput() {
        System.out.println();
        System.out.print(GREEN + "기존 비밀번호 : " + RESET);
        String oldPassword = sc.nextLine();

        System.out.println();
        System.out.print(GREEN + "새 비밀번호 : " + RESET);
        String newPassword = sc.nextLine();

        controller.changePassword(Session.getUserId(), oldPassword, newPassword);
    }
}