package org.gotchafish.user.service;

import org.gotchafish.user.dto.UserDTO;
import org.gotchafish.user.dto.LoginResult;

import java.sql.SQLException;

public interface UserService {
    /**
     * 회원가입을 처리한다.
     * 아이디와 닉네임의 중복 여부를 확인하고, 회원 정보를 저장한다.
     * 회원가입 성공 시 기본 골드와 기본 낚시대가 지급된다.
     * @param user 가입할 회원 객체
     * @return 회원가입 성공시 회원 객체, 실패시 null
     * @throws RuntimeException 아이디/닉네임 중복, 저장 실패, 보상 지급 실패
     * @throws SQLException DB 처리 중 예외가 발생
     */
    UserDTO signUp(UserDTO user) throws SQLException;

    /**
     * 로그인을 처리한다.
     * 입력받은 로그인 아이디를 이용해 회원 정보를 조회하고,
     * 비밀번호가 일치하는지 확인한다.
     * 회원이 오늘 출석했는지 확인하고,
     * 출석하지 않았다면 출석을 진행한다.
     * @param loginId 로그인 아이디
     * @param password 비밀번호
     * @return 로그인한 회원 객체, 로그인 실패 시 null
     */
    LoginResult login(String loginId, String password) throws SQLException;

    /**
     * 회원 정보를 조회한다.
     * @param userId 조회할 회원의 ID
     * @return 회원 객체, 존재하지 않으면 null
     */
    UserDTO getUser(Long userId) throws SQLException;

    /**
     * 회원의 닉네임을 변경한다.
     * 회원의 비밀번호 일치 여부를 확인하고,
     * 변경할 닉네임의 중복 여부를 확인한 후 수정한다.
     * @param userId 수정할 회원의 ID
     * @param nickname 변경할 닉네임
     * @param password 현재 비밀번호
     * @return 수정 성공시 true, 실패시 false
     */
    boolean updateNickname(Long userId, String nickname, String password) throws SQLException;

    /**
     * 회원의 비밀번호를 변경한다.
     * 기존 비밀번호 일치 여부를 확인한 후 수정한다.
     * @param userId 수정할 회원의 ID
     * @param oldPassword 수정할 회원의 ID
     * @param newPassword 변경할 비밀번호
     * @return 수정 성공시 true, 실패시 false
     */
    boolean updatePassword(Long userId, String oldPassword, String newPassword)  throws SQLException;
}