package org.gotchafish.rod.service;

import org.gotchafish.common.JDBCUtil;
import org.gotchafish.rod.dao.RodDAO;
import org.gotchafish.rod.dao.RodDAOImpl;
import org.gotchafish.rod.dto.RodDTO;
import org.gotchafish.user.dao.UserDAO;
import org.gotchafish.user.dao.UserDAOImpl;
import org.gotchafish.user.dto.UserDTO;

import java.sql.*;
import java.util.List;

public class RodServiceImpl implements RodService {
    private final RodDAO rodDAO = RodDAOImpl.getInstance();
    private final UserDAO userDAO = UserDAOImpl.getInstance();

    private static final RodService instance = new RodServiceImpl();

    public static RodService getInstance() {
        return instance;
    }

    @Override
    public List<RodDTO> getRodShop(Long userId) throws SQLException {
        // DB 연결 생성 및 자동 반납
        try (Connection conn = JDBCUtil.getConnection()) {
            // 낚시대 상점의 전체 낚시대 목록 조회
            List<RodDTO> rods = rodDAO.findAllWithQuantity(conn, userId);

            if (rods.isEmpty()) {
                throw new RuntimeException("구매 가능한 낚시대가 없습니다.");
            }

            return rods;
        }
    }

    @Override
    public List<RodDTO> getMyRods(Long userId) throws SQLException {
        // DB 연결 생성 및 자동 반납
        try (Connection conn = JDBCUtil.getConnection()) {
            // 사용자가 보유한 낚시대 목록 조회
            List<RodDTO> rods = rodDAO.findRodsByUserId(conn, userId);

            if (rods.isEmpty()) {
                throw new RuntimeException("선택 가능한 낚시대가 없습니다.");
            }

            return rods;
        }
    }

    @Override
    public RodDTO getRod(Long rodId) throws SQLException {
        // DB 연결 생성 및 자동 반납
        try (Connection conn = JDBCUtil.getConnection()) {
            // 사용자가 보유한 낚시대 목록 조회
            RodDTO rod = rodDAO.findByRodId(conn, rodId);

            if (rod == null) {
                throw new RuntimeException("존재하지 않는 낚시대입니다.");
            }

            return rod;
        }
    }

    @Override
    public boolean buyRod(Long userId, Long rodId, int quantity) throws SQLException {
        Connection conn = null;

        try {
            // 트랜잭션에 사용할 Connection 생성
            conn = JDBCUtil.getConnection();

            // 자동 커밋 끄기
            conn.setAutoCommit(false);

            // 구입할 낚시대 정보 조회
            RodDTO rod = rodDAO.findByRodId(conn, rodId);

            if (rod == null) {
                throw new RuntimeException("존재하지 않는 낚시대입니다.");
            }

            if (quantity <= 0) {
                throw new RuntimeException("수량은 1개 이상이어야 합니다.");
            }

            // 사용자 정보 조회
            UserDTO user = userDAO.findByUserId(conn, userId);

            if (user == null) {
                throw new RuntimeException("존재하지 않는 회원입니다.");
            }

            // 골드가 충분한지 확인
            if (user.getGold() < rod.getPrice() * quantity) {
                throw new RuntimeException("사용자 골드가 부족합니다.");
            }

            // 이미 해당 낚시대를 보유하고 있는지 확인
            if (rodDAO.existsUserRod(conn, userId, rodId)) {
                // 이미 보유하고 있다면 수량 +quantity
                if (!rodDAO.updateUserRodQuantity(conn, userId, rodId, quantity)) {
                    throw new RuntimeException("낚시대 구입에 실패했습니다.");
                }
            } else {
                // 보유하고 있지 않다면 새로운 낚시대 정보 생성
                if (!rodDAO.insertUserRod(conn, userId, rodId, quantity)) {
                    throw new RuntimeException("낚시대 구입에 실패했습니다.");
                }
            }

            // 낚시대 가격만큼 골드 차감
            if (!userDAO.updateGold(conn, userId, -rod.getPrice() * quantity)) {
                throw new RuntimeException("낚시대 구입에 실패했습니다.");
            }

            // 모든 작업 성공
            conn.commit();

            return true;
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
    public boolean useRod(Long userId, Long rodId) throws SQLException {
        Connection conn = null;

        try {
            // 트랜잭션에 사용할 Connection 생성
            conn = JDBCUtil.getConnection();

            // 자동 커밋 끄기
            conn.setAutoCommit(false);

            // 사용자 정보 조회
            UserDTO user = userDAO.findByUserId(conn, userId);

            if (user == null) {
                throw new RuntimeException("존재하지 않는 회원입니다.");
            }

            // 사용할 낚시대 정보 조회
            RodDTO rod = rodDAO.findByRodId(conn, rodId);

            if (rod == null) {
                throw new RuntimeException("존재하지 않는 낚시대입니다.");
            }

            int quantity = rodDAO.findUserRodQuantity(conn, userId, rodId);

            if (quantity <= 0) {
                throw new RuntimeException("낚시대 보유 수량이 0입니다.");
            }

            //실제로 사용하기
            if (!rodDAO.updateUserRodQuantity(conn, userId, rodId, -1)) {
                throw new RuntimeException("낚시대 사용에 실패했습니다.");
            }

            // 모든 작업 성공
            conn.commit();

            return true;
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
}