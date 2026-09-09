package org.gotchafish.fish.service;

import org.gotchafish.common.JDBCUtil;
import org.gotchafish.fish.dao.FishDAO;
import org.gotchafish.fish.dao.FishDAOImpl;
import org.gotchafish.fish.dto.FishDTO;
import org.gotchafish.user.dao.UserDAO;
import org.gotchafish.user.dao.UserDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FishServiceImpl implements FishService {
    private final FishDAO fishDAO = FishDAOImpl.getInstance();
    private final UserDAO userDAO = UserDAOImpl.getInstance();

    private static final FishService instance = new FishServiceImpl();

    public static FishService getInstance() {
        return instance;
    }

    // 사용자의 보유 물고기 목록 조회
    @Override
    public List<FishDTO> getMyFish(Long userId) throws SQLException {

        try (Connection conn = JDBCUtil.getConnection()) {
            List<FishDTO> fishList = fishDAO.findFishByUserId(conn, userId);

            if (fishList.isEmpty()) throw new RuntimeException("보유한 물고기가 없습니다.");

            return fishList;
        }
    }

    @Override
    public FishDTO sellFish(Long userId, Long fishId, int quantity) throws SQLException {
        Connection conn = null;

        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);

            FishDTO fish = fishDAO.findById(conn, fishId);

            // 물고기 목록 확인 및 이름/가격 확인
            if (fish == null) throw new RuntimeException("존재하지 않는 물고기입니다.");

            // 물고기 판매 수량 확인
            if (quantity <= 0) throw new RuntimeException("판매 수량은 1개 이상이어야 합니다.");

            // 물고기 판매 수량 검증
            int myQuantity = fishDAO.findUserFishQuantity(conn, userId, fishId);

            if (myQuantity < quantity) throw new RuntimeException("보유한 물고기 수량이 부족합니다.");

            // 수량 차감, 골드 지급, 커밋
            if (!fishDAO.updateUserFishQuantity(conn, userId, fishId, -quantity))
                throw new RuntimeException("물고기 판매에 실패했습니다.");

            if (!userDAO.updateGold(conn, userId, fish.getPrice() * quantity))
                throw new RuntimeException("골드 지급에 실패했습니다.");

            conn.commit();
            return fish;

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
}
