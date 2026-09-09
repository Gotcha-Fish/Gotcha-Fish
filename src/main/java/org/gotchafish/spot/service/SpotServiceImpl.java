package org.gotchafish.spot.service;

import org.gotchafish.common.JDBCUtil;
import org.gotchafish.spot.dao.SpotDAO;
import org.gotchafish.spot.dao.SpotDAOImpl;
import org.gotchafish.spot.dao.UserSpotDAO;
import org.gotchafish.spot.dao.UserSpotDAOImpl;
import org.gotchafish.spot.dto.SpotDTO;
import org.gotchafish.user.dao.UserDAO;
import org.gotchafish.user.dao.UserDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SpotServiceImpl implements SpotService {
    private static final SpotService instance = new SpotServiceImpl();
    private final SpotDAO spotDAO = SpotDAOImpl.getInstance();
    private final UserDAO userDAO = UserDAOImpl.getInstance();
    private final UserSpotDAO userSpotDAO = UserSpotDAOImpl.getInstance();

    private SpotServiceImpl() {
    }

    public static SpotService getInstance() {
        return instance;
    }

    @Override
    public List<SpotDTO> getSpotList() throws SQLException {
        try (Connection conn = JDBCUtil.getConnection()) {
            return spotDAO.findAll(conn);
        } catch (Exception e) {
            throw new RuntimeException("낚시터 조회에 실패했습니다.");
        }
    }

    @Override
    public boolean unlockSpot(Long userId, Long spotId) throws SQLException {
        Connection conn = null;

        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);

            // 낚시터 정보 조회 (가격 확인)
            SpotDTO spot = spotDAO.findById(conn, spotId);
            if (spot == null) {
                throw new RuntimeException("존재하지 않는 낚시터 입니다.");
            }

            // 이미 잠금 해제했는지 확인
            if (userSpotDAO.existsByUserIdAndSpotId(conn, userId, spotId)) {
                throw new RuntimeException("이미 잠금 해제된 낚시터입니다.");
            }

            // 골드 차감하기
            if (!userDAO.updateGold(conn, userId, -spot.getUnlockPrice())) {
                throw new RuntimeException("골드 차감에 실패했습니다");
            }
            // 잠금 해제 기록 저장
            if (!userSpotDAO.insert(conn, userId, spotId)) {
                throw new RuntimeException("잠금 해제 기록 저장에 실패했습니다.");
            }

            conn.commit();
            return true;
        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    @Override
    public SpotDTO getSpotById(Long spotId) throws SQLException {
        try (Connection conn = JDBCUtil.getConnection()) {
            return spotDAO.findById(conn, spotId);
        }
    }
}
