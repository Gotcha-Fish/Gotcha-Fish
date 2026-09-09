package org.gotchafish.ranking.service;

import org.gotchafish.common.JDBCUtil;
import org.gotchafish.ranking.dao.RankingDAO;
import org.gotchafish.ranking.dao.RankingDAOImpl;
import org.gotchafish.ranking.dto.RankingDTO;
import org.gotchafish.user.dao.UserDAO;
import org.gotchafish.user.dao.UserDAOImpl;
import org.gotchafish.user.dto.UserDTO;
import org.gotchafish.user.util.DbManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RankingServiceImpl implements RankingService {
    private final RankingDAO rankingDAO = RankingDAOImpl.getInstance();
    private final UserDAO userDAO = UserDAOImpl.getInstance();

    private static final RankingServiceImpl instance = new RankingServiceImpl();

    public static RankingServiceImpl getInstance() { return instance; }

    @Override
    public List<RankingDTO> getFishingRanking(int page) throws SQLException {
        // DB 연결 생성 및 자동 반납
        try (Connection conn = JDBCUtil.getConnection()) {
            // 낚은 물고기 수 랭킹 리스트 조회
            List<RankingDTO> rankings = rankingDAO.getFishingRanking(conn, page);

            if (rankings.isEmpty()) {
                throw new RuntimeException("랭킹 조회에 실패했습니다.");
            }

            return rankings;
        }
    }

    @Override
    public RankingDTO getMyFishingRank(Long userId) throws SQLException {
        // DB 연결 생성 및 자동 반납
        try (Connection conn = JDBCUtil.getConnection()) {
            // 사용자 정보 조회
            UserDTO user = userDAO.findByUserId(conn, userId);

            if (user == null) {
                throw new RuntimeException("존재하지 않는 회원입니다.");
            }

            // 해당 회원의 물고기 수 랭킹 조회
            RankingDTO ranking = rankingDAO.getMyFishingRank(conn, userId);

            if (ranking == null) {
                throw new RuntimeException("회원 랭킹 조회에 실패했습니다.");
            }

            return ranking;
        }
    }

    @Override
    public List<RankingDTO> getGoldRanking(int page) throws SQLException {
        // DB 연결 생성 및 자동 반납
        try (Connection conn = JDBCUtil.getConnection()) {
            // 보유 골드 랭킹 리스트 조회
            List<RankingDTO> rankings = rankingDAO.getGoldRanking(conn, page);

            if (rankings.isEmpty()) {
                throw new RuntimeException("랭킹 조회에 실패했습니다.");
            }

            return rankings;
        }
    }

    @Override
    public RankingDTO getMyGoldRank(Long userId) throws SQLException {
        // DB 연결 생성 및 자동 반납
        try (Connection conn = JDBCUtil.getConnection()) {
            // 사용자 정보 조회
            UserDTO user = userDAO.findByUserId(conn, userId);

            if (user == null) {
                throw new RuntimeException("존재하지 않는 회원입니다.");
            }

            // 회원의 낚은 물고기 수 랭킹 조회
            RankingDTO ranking = rankingDAO.getMyGoldRank(conn, userId);

            if (ranking == null) {
                throw new RuntimeException("회원 랭킹 조회에 실패했습니다.");
            }

            return ranking;
        }
    }
}