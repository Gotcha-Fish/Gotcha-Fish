package org.gotchafish.user.service;

import org.gotchafish.rod.dao.RodDAO;
import org.gotchafish.rod.dao.RodDAOImpl;
import org.gotchafish.user.dto.Session;
import org.gotchafish.user.dto.UserDTO;
import org.gotchafish.user.dao.AttendanceDAO;
import org.gotchafish.user.dao.AttendanceDAOImpl;
import org.gotchafish.user.dao.UserDAO;
import org.gotchafish.user.dao.UserDAOImpl;
import org.gotchafish.user.util.DbManager;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = UserDAOImpl.getInstance();
    private final AttendanceDAO attendanceDAO = AttendanceDAOImpl.getInstance();
    private final RodDAO rodDAO = RodDAOImpl.getInstance();

    private static final UserService instance = new UserServiceImpl();

    public static UserService getInstance() {
        return instance;
    }

    @Override
    public UserDTO signUp(UserDTO user) throws SQLException {
        Connection conn = null;

        try {
            // 트랜잭션에 사용할 Connection 생성
            conn = DbManager.getConnection();

            // 자동 커밋 끄기
            conn.setAutoCommit(false);

            // 아이디 중복 확인
            if (userDAO.existsByLoginId(conn, user.getLoginId())) {
                throw new RuntimeException("이미 사용 중인 아이디입니다.");
            }

            // 닉네임 중복 확인
            if (userDAO.existsByNickname(conn, user.getNickname())) {
                throw new RuntimeException("이미 사용 중인 닉네임입니다.");
            }

            // 비밀번호 암호화
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

            // 회원 정보 저장
            Long userId = userDAO.insert(conn, user);

            if (userId == null) {
                throw new RuntimeException("회원 정보 저장에 실패했습니다.");
            }

            // 회원가입 보상 100G 지급
            if (!userDAO.updateGold(conn, userId, 100)) {
                throw new RuntimeException("회원가입 보상 지급에 실패했습니다.");
            }
            
            // 회원가입 보상 기본 낚시대 10개 지급
            if (!rodDAO.insertUserRod(conn, userId, 1L, 10)) {
                throw new RuntimeException("회원가입 보상 지급에 실패했습니다.");
            }

            // 모든 작업 성공
            conn.commit();

            // 저장된 User 객체 반환
            return userDAO.findByUserId(conn, userId);
        } catch (Exception e) {
            // 작업 실패시 되돌리기
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                // Connection 반환 및 자동 커밋 되돌리기
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    @Override
    public UserDTO login(String loginId, String password) throws SQLException {
        Connection conn = null;

        try {
            // 트랜잭션에 사용할 Connection 생성
            conn = DbManager.getConnection();

            // 자동 커밋 끄기
            conn.setAutoCommit(false);

            // 회원 정보 찾기
            UserDTO user = userDAO.findByLoginId(conn, loginId);

            if (user == null) {
                throw new RuntimeException("존재하지 않는 아이디입니다.");
            }

            // 비밀번호 일치 여부 판단
            if (!BCrypt.checkpw(password, user.getPassword())) {
                throw new RuntimeException("비밀번호가 일치하지 않습니다.");
            }

            // 출석 보상 여부
            boolean attendanceRewarded = false;

            // 출석 여부 판단
            if (!attendanceDAO.isAttendedToday(conn, user.getUserId())) {
                // 오늘 출석 기록 저장
                if (!attendanceDAO.insert(conn, user.getUserId())) {
                    throw new RuntimeException("출석 처리에 실패했습니다.");
                }

                // 출석 보상 10G 지급
                if (!userDAO.updateGold(conn, user.getUserId(), 10)) {
                    throw new RuntimeException("출석 보상 지급에 실패했습니다.");
                }

                // 출석 보상 기본 낚시대 1개 지급
                if (!rodDAO.insertUserRod(conn, user.getUserId(), 1L, 1)) {
                    throw new RuntimeException("출석 보상 지급에 실패했습니다.");
                }

                // 출석 보상 기본 낚시대 1개 지급

                attendanceRewarded = true;
            }

            // 모든 작업 성공
            conn.commit();

            UserDTO userDTO = userDAO.findByUserId(conn, user.getUserId());

            userDTO.setAttendanceRewarded(attendanceRewarded);

            // Session userID 저장
            Session.setUserId(userDTO.getUserId());

            // 로그인 결과 UserDTO 반환
            return userDTO;
        } catch (SQLException e) {
            // 작업 실패시 되돌리기
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                // Connection 반환 및 자동 커밋 되돌리기
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    @Override
    public UserDTO getUser(Long userId) throws SQLException {
        // Connection 생성
        try (Connection conn = DbManager.getConnection()) {

            // 회원 정보 찾기
            UserDTO user = userDAO.findByUserId(conn, userId);

            if (user == null) {
                throw new RuntimeException("존재하지 않는 회원입니다.");
            }

            return user;
        }
    }

    @Override
    public boolean updateNickname(Long userId, String nickname, String password) throws SQLException{
        try (Connection conn = DbManager.getConnection()) {

            // 회원 정보 조회
            UserDTO user = userDAO.findByUserId(conn, userId);

            if (user == null) {
                throw new RuntimeException("존재하지 않는 회원입니다.");
            }

            // 현재 비밀번호 확인
            if (!BCrypt.checkpw(password, user.getPassword())) {
                throw new RuntimeException("비밀번호가 일치하지 않습니다.");
            }

            // 닉네임 중복 확인
            if (userDAO.existsByNickname(conn, nickname)) {
                throw new RuntimeException("이미 사용 중인 닉네임입니다.");
            }

            // 닉네임 변경
            if (!userDAO.updateNickname(conn, userId, nickname)) {
                throw new RuntimeException("닉네임 변경에 실패했습니다.");
            }

            return true;
        }
    }

    @Override
    public boolean updatePassword(Long userId, String oldPassword, String newPassword) throws SQLException {
        try (Connection conn = DbManager.getConnection()) {
            // 회원 정보 조회
            UserDTO user = userDAO.findByUserId(conn, userId);

            if (user == null) {
                throw new RuntimeException("존재하지 않는 회원입니다.");
            }

            // 기존 비밀번호 확인
            if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
                throw new RuntimeException("현재 비밀번호가 일치하지 않습니다.");
            }

            // 새 비밀번호 암호화
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

            // 비밀번호 변경
            if (!userDAO.updatePassword(conn, userId, hashedPassword)) {
                throw new RuntimeException("비밀번호 변경에 실패했습니다.");
            }

            return true;
        }
    }
}