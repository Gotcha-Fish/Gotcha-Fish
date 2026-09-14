package org.gotchafish.fish.service;

import org.gotchafish.common.JDBCUtil;
import org.gotchafish.dictionary.dao.DictionaryDAO;
import org.gotchafish.dictionary.dao.DictionaryDAOImpl;
import org.gotchafish.fish.dao.FishDAO;
import org.gotchafish.fish.dao.FishDAOImpl;
import org.gotchafish.fish.dto.FishDTO;
import org.gotchafish.fish.dto.Rarity;
import org.gotchafish.user.dao.UserDAO;
import org.gotchafish.user.dao.UserDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FishServiceImpl implements FishService {
    private final FishDAO fishDAO = FishDAOImpl.getInstance();
    private final UserDAO userDAO = UserDAOImpl.getInstance();
    private final DictionaryDAO dictionaryDAO = DictionaryDAOImpl.getInstance();

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

    @Override
    public FishDTO generateFish(Long spotId) throws SQLException {
        try (Connection conn = JDBCUtil.getConnection()) {
            List<FishDTO> candidates = fishDAO.findBySpotId(conn, spotId);

            List<FishDTO> matched;
            Rarity rarity;

            // 일단 하나 뽑는다
            do {
                rarity = Rarity.pickRandom();
                matched = new ArrayList<>();

                for (FishDTO fish : candidates) {
                    if (fish.getRarity() == rarity) matched.add(fish);
                }
            } while (matched.isEmpty()); // 없으면 다시 시도

            int index = (int) (Math.random() * matched.size());
            return matched.get(index);
        }
    }

    @Override
    public boolean catchFish(Long userId, Long fishId) throws SQLException {
        Connection conn = null;

        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);

            if (fishDAO.existsUserFish(conn, userId, fishId)) {
                if (!fishDAO.updateUserFishQuantity(conn, userId, fishId, 1)) {
                    throw new RuntimeException("물고기 획득에 실패했습니다.");
                }
            } else {
                if (!fishDAO.insertUserFish(conn, userId, fishId, 1)) {
                    throw new RuntimeException("물고기 획득에 실패했습니다.");
                }
            }

            if (!dictionaryDAO.existsByUserIdAndFishId(conn, userId, fishId)) {
                if (!dictionaryDAO.insert(conn, userId, fishId)) {
                    throw new RuntimeException("도감 등록에 실패했습니다.");
                }
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
    public boolean loseFish(Long userId, Long fishId) throws SQLException {
        Connection conn = null;

        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);

            FishDTO fish = fishDAO.findById(conn, fishId);
            if (fish == null) throw new RuntimeException("존재하지 않는 물고기 입니다.");

            int myQuantity = fishDAO.findUserFishQuantity(conn, userId, fishId);
            if (myQuantity < 1) throw new RuntimeException("보유한 물고기 수량이 부족합니다.");

            if (!fishDAO.updateUserFishQuantity(conn, userId, fishId, -1))
                throw new RuntimeException("물고기 차감에 실패했습니다.");

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
}
