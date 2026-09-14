package org.gotchafish.raid.service;

import org.gotchafish.common.JDBCUtil;
import org.gotchafish.fish.dao.FishDAO;
import org.gotchafish.fish.dao.FishDAOImpl;
import org.gotchafish.fish.dto.FishDTO;
import org.gotchafish.raid.dao.RaidDAO;
import org.gotchafish.raid.dao.RaidDAOImpl;
import org.gotchafish.raid.dto.RaidResultDTO;
import org.gotchafish.user.dao.UserDAO;
import org.gotchafish.user.dao.UserDAOImpl;
import org.gotchafish.user.dto.UserDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class RaidServiceImpl implements RaidService {
    private final UserDAO userDAO = UserDAOImpl.getInstance();
    private final FishDAO fishDAO = FishDAOImpl.getInstance();
    private final RaidDAO raidDAO = RaidDAOImpl.getInstance();

    public static final RaidService instance = new RaidServiceImpl();

    public static RaidService getInstance() { return instance; }

    @Override
    public boolean insertRaidResult(RaidResultDTO raidResultDTO) throws SQLException {
        // DB 연결 생성 및 자동 반납
        try (Connection conn = JDBCUtil.getConnection()) {
            // 사용자 정보 조회
            UserDTO user = userDAO.findByUserId(conn, raidResultDTO.getHostId());

            if (user == null) {
                throw new RuntimeException("존재하지 않는 회원입니다.");
            }

            user = userDAO.findByUserId(conn, raidResultDTO.getGuestId());

            if (user == null) {
                throw new RuntimeException("존재하지 않는 회원입니다.");
            }

            user = userDAO.findByUserId(conn, raidResultDTO.getWinnerId());

            if (user == null) {
                throw new RuntimeException("존재하지 않는 회원입니다.");
            }

            // 물고기 정보 조회
            FishDTO fish = fishDAO.findById(conn, raidResultDTO.getHostFishId());

            if (fish == null) {
                throw new RuntimeException("존재하지 않는 물고기입니다.");
            }

            fish = fishDAO.findById(conn, raidResultDTO.getGuestFishId());

            if (fish == null) {
                throw new RuntimeException("존재하지 않는 물고기입니다.");
            }

            if (!raidDAO.insertRaidResult(conn, raidResultDTO)) {
                throw new RuntimeException("대결 결과 저장에 실패했습니다.");
            }

            return true;
        }
    }
}