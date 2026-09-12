package org.gotchafish.user.controller;

import org.gotchafish.user.dto.UserDTO;
import org.gotchafish.user.service.UserService;
import org.gotchafish.user.service.UserServiceImpl;
import org.gotchafish.user.view.FailView;
import org.gotchafish.user.view.SuccessView;

import java.sql.SQLException;

public class UserController {
    private final UserService userService = UserServiceImpl.getInstance();

    private static final UserController instance = new UserController();

    public static UserController getInstance() {
        return instance;
    }

    public void signUp(UserDTO user) {
        try {
            // 회원가입 서비스 실행
            UserDTO userDTO = userService.signUp(user);

            // 회원가입 성공
            SuccessView.signUpSuccess(userDTO);
        } catch (RuntimeException e) {
            // 아이디/닉네임 중복,저장 실패 등의 예외
            FailView.signUpFail(e.getMessage());
        } catch (SQLException e) {
            // DB 오류
            FailView.signUpFail("회원가입 중 오류가 발생했습니다.");
        }
    }

    public void login(String loginId, String password) {
        try {
            UserDTO userDTO = userService.login(loginId, password);

            SuccessView.loginSuccess(userDTO);
            if (userDTO.isAttendanceRewarded()) {
                SuccessView.attendanceReward(userDTO);
            }
        } catch (RuntimeException e) {
            // 존재하지 않는 아이디, 비밀번호 오류 등의 예외
            FailView.loginFail(e.getMessage());
        } catch (SQLException e) {
            // DB 오류
            FailView.loginFail("로그인 중 오류가 발생했습니다.");
        }
    }

    public boolean profile(Long userId) {
        try {
            UserDTO user = userService.getUser(userId);
            SuccessView.profileSuccess(user);
            return true;
        } catch (RuntimeException e) {
            // 정보 조회 실패
            FailView.profileFail(e.getMessage());
            return false;
        } catch (SQLException e) {
            // DB 오류
            FailView.profileFail("정보 조회 중 오류가 발생했습니다.");
            return false;
        }
    }

    public void changeNickname(Long userId, String nickname, String password) {
        try {
            if(userService.updateNickname(userId, nickname, password)) {
                SuccessView.changeNicknameSuccess();
            }
        } catch (RuntimeException e) {
            // 비밀번호 불일치, 닉네임 중복, 닉네임 변경 실패
            FailView.changeNicknameFail(e.getMessage());
        } catch (SQLException e) {
            // DB 오류
            FailView.changeNicknameFail("닉네임 변경 중 오류가 발생했습니다.");
        }
    }

    public void changePassword(Long userId, String oldPassword, String newPassword) {
        try {
            if(userService.updatePassword(userId, oldPassword, newPassword)) {
                SuccessView.changePasswordSuccess();
            }
        } catch (RuntimeException e) {
            // 비밀번호 불일치, 비밀번호 변경 실패
            FailView.changePasswordFail(e.getMessage());
        } catch (SQLException e) {
            // DB 오류
            FailView.changePasswordFail("비밀번호 변경 중 오류가 발생했습니다.");
        }
    }
}