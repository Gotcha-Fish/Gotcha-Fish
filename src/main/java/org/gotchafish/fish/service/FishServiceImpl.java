package org.gotchafish.fish.service;

import org.gotchafish.fish.dao.FishDAO;
import org.gotchafish.fish.dao.FishDAOImpl;
import org.gotchafish.fish.dto.FishDTO;
import org.gotchafish.user.dao.UserDAO;
import org.gotchafish.user.dao.UserDAOImpl;

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

    }

    @Override
    public boolean sellFish(Long userId, Long fishId, int quantity) throws SQLException {
        return false;
    }
}
