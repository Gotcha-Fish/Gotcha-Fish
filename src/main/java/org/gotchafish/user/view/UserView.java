package org.gotchafish.user.view;

import org.gotchafish.user.dto.Session;
import org.gotchafish.user.dto.UserDTO;
import org.gotchafish.user.controller.UserController;

import java.util.Scanner;

public class UserView {
    private final Scanner sc = new Scanner(System.in);

    private final UserController controller = UserController.getInstance();

    public void signUp() {
        System.out.println();
        System.out.println("================================");
        System.out.println("             회원가입");
        System.out.println("================================");
        System.out.println();

        System.out.print("아이디 : ");
        String loginId = sc.nextLine();

        System.out.print("비밀번호 : ");
        String password = sc.nextLine();

        System.out.print("닉네임 : ");
        String nickname = sc.nextLine();

        UserDTO user = new UserDTO(loginId, password, nickname);

        controller.signUp(user);
    }

    public void loginInput() {
        System.out.println();
        System.out.println("================================");
        System.out.println("             로그인");
        System.out.println("================================");
        System.out.println();

        System.out.print("아이디 : ");
        String loginId = sc.nextLine();

        System.out.print("비밀번호 : ");
        String password = sc.nextLine();

        controller.login(loginId, password);
    }

    public void profile() {
        System.out.println();
        System.out.println("================================");
        System.out.println("             내 정보");
        System.out.println("================================");
        System.out.println();

        if(!controller.profile(Session.getUserId())) return;

        System.out.println();
        System.out.println("1. 닉네임 수정");
        System.out.println("2. 비밀번호 수정");
        System.out.println("0. 뒤로가기");
        System.out.println();

        System.out.print("선택 : ");
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
        System.out.print("새 닉네임 : ");
        String nickname = sc.nextLine();

        System.out.println();
        System.out.print("비밀번호 : ");
        String password = sc.nextLine();

        controller.changeNickname(Session.getUserId(), nickname ,password);
    }

    private void changePasswordInput() {
        System.out.println();
        System.out.print("기존 비밀번호 : ");
        String oldPassword = sc.nextLine();

        System.out.println();
        System.out.print("새 비밀번호 : ");
        String newPassword = sc.nextLine();

        controller.changePassword(Session.getUserId(), oldPassword, newPassword);
    }
}