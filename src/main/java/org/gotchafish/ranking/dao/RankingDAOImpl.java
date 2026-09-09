package org.gotchafish.ranking.dao;

import org.gotchafish.ranking.dto.RankingDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RankingDAOImpl implements RankingDAO {
    private static final RankingDAO instance = new RankingDAOImpl();

    public static RankingDAO getInstance() { return instance; }

    @Override
    public List<RankingDTO> getFishingRanking(Connection conn, int page) throws SQLException {
        String sql = """
            SELECT user_id, nickname, total_fishing
            FROM tbl_user
            ORDER BY total_fishing DESC, user_id
            LIMIT 10 OFFSET ?
        """;

        List<RankingDTO> rankingList = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, (page - 1) * 10);

            try (ResultSet rs = stmt.executeQuery()) {
                int rank = (page - 1) * 10 + 1;

                while (rs.next()) {
                    rankingList.add(new RankingDTO(
                            rs.getLong("user_id"),
                            rank++,
                            rs.getString("nickname"),
                            rs.getInt("total_fishing")
                    ));
                }
            }
        }

        return rankingList;
    }


    @Override
    public RankingDTO getMyFishingRank(Connection conn, Long userId) throws SQLException {
        String sql = """
            SELECT (
                SELECT COUNT(*)
                FROM tbl_user
                WHERE total_fishing > (
                    SELECT total_fishing
                    FROM tbl_user
                    WHERE user_id = ?
                )
            ) + 1 AS ranking,
                user_id,
                nickname,
                total_fishing
            FROM tbl_user
            WHERE user_id = ?
        """;

        RankingDTO rankingDTO = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            stmt.setLong(2, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new RankingDTO(
                            rs.getLong("user_id"),
                            rs.getInt("ranking"),
                            rs.getString("nickname"),
                            rs.getInt("total_fishing"));
                }
            }
        }

        return null;
    }

    @Override
    public List<RankingDTO> getGoldRanking(Connection conn, int page) throws SQLException {
        String sql = """
            SELECT user_id, nickname, gold
            FROM tbl_user
            ORDER BY gold DESC, user_id
            LIMIT 10 OFFSET ?
        """;

        List<RankingDTO> rankingList = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, (page - 1) * 10);

            try (ResultSet rs = stmt.executeQuery()) {
                int rank = (page - 1) * 10 + 1;

                while (rs.next()) {
                    rankingList.add(new RankingDTO(
                            rs.getLong("user_id"),
                            rank++,
                            rs.getString("nickname"),
                            rs.getInt("gold")
                    ));
                }
            }
        }

        return rankingList;
    }

    @Override
    public RankingDTO getMyGoldRank(Connection conn, Long userId) throws SQLException {
        String sql = """
            SELECT (
                SELECT COUNT(*)
                FROM tbl_user
                WHERE gold > (
                    SELECT gold
                    FROM tbl_user
                    WHERE user_id = ?
                )
            ) + 1 AS ranking,
                user_id,
                nickname,
                gold
            FROM tbl_user
            WHERE user_id = ?
        """;

        RankingDTO rankingDTO = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            stmt.setLong(2, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new RankingDTO(
                            rs.getLong("user_id"),
                            rs.getInt("ranking"),
                            rs.getString("nickname"),
                            rs.getInt("gold"));
                }
            }
        }

        return null;
    }
}