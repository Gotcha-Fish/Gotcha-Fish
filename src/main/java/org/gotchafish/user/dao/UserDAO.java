package org.gotchafish.user.dao;

import org.gotchafish.user.dto.UserDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDAO {
    /**
     * 회원 정보를 저장한다.
     * @param conn DB 연결 객체
     * @param user 저장할 회원 객체
     * @return 생성된 회원의 ID, 실패시 null
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    Long insert(Connection conn, UserDTO user) throws SQLException;

    /**
     * 회원 ID로 회원 정보를 조회한다.
     * @param conn DB 연결 객체
     * @param userId 조회할 회원의 ID
     * @return 조회된 User 객체, 존재하지 않으면 null
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    UserDTO findByUserId(Connection conn, Long userId) throws SQLException;

    /**
     * 로그인 ID로 회원 정보를 조회한다.
     * @param conn DB 연결 객체
     * @param loginId 조회할 로그인 ID
     * @return 조회된 User 객체, 존재하지 않으면 null
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    UserDTO findByLoginId(Connection conn, String loginId) throws SQLException;

    /**
     * 회원의 아이디 중복 여부를 확인한다.
     * @param conn DB 연결 객체
     * @param loginId 중복 확인할 로그인 아이디
     * @return 이미 존재하면 true, 존재하지 않으면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean existsByLoginId(Connection conn, String loginId) throws SQLException;

    /**
     * 회원의 닉네임 중복 여부를 확인한다.
     * @param conn DB 연결 객체
     * @param nickname 중복 확인할 닉네임
     * @return 이미 존재하면 true, 존재하지 않으면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean existsByNickname(Connection conn, String nickname) throws SQLException;

    /**
     * 회원의 닉네임을 수정한다.
     * @param conn DB 연결 객체
     * @param userId 수정할 회원의 ID
     * @param nickname 변경할 닉네임
     * @return 성공하면 true, 실패하면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean updateNickname(Connection conn, Long userId, String nickname) throws SQLException;

    /**
     * 회원의 비밀번호를 수정한다.
     * @param conn DB 연결 객체
     * @param userId 수정할 회원의 ID
     * @param password 변경할 비밀번호
     * @return 성공하면 true, 실패하면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean updatePassword(Connection conn, Long userId, String password) throws SQLException;

    /**
     * 회원의 보유 골드를 변경한다.
     * @param conn DB 연결 객체
     * @param userId 골드를 변경할 회원의 ID
     * @param amount 변경할 골드량(양수: 골드 증가 / 음수: 골드 감소)
     * @return 성공하면 true, 실패하면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean updateGold(Connection conn, Long userId, int amount) throws SQLException;

    /**
     * 회원의 총 낚시 횟수를 1 증가시킨다.
     * @param conn DB 연결 객체
     * @param userId 낚시 횟수를 증가시킬 회원의 ID
     * @return 성공하면 true, 실패하면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean updateFishingCount(Connection conn, Long userId) throws SQLException;
}