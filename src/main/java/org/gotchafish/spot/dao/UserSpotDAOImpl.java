package org.gotchafish.spot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserSpotDAOImpl implements UserSpotDAO {
    private static final UserSpotDAO instance = new UserSpotDAOImpl();

    private UserSpotDAOImpl() {
    }

    public static UserSpotDAO getInstance() {
        return instance;
    }

    @Override
    public boolean insert(Connection conn, Long userId, int spotId) throws SQLException {
        String sql = """
            INSERT INTO tbl_user_spot (user_id, spot_id) VALUES (?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, userId);
            ps.setInt(2, spotId);

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean existsByUserIdAndSpotId(Connection conn, Long userId, int spotId) throws SQLException {
        String sql = """
            SELECT count(*) FROM tbl_user_spot
            WHERE user_id = ? AND spot_id = ?
        """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, userId);
            ps.setInt(2, spotId);

            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    if (rs.getInt(1) > 0) return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Integer> findSpotIdsByUserId(Connection conn, Long userId) throws SQLException {
        String sql = """
            SELECT spot_id FROM tbl_user_spot
            WHERE user_id = ?
        """;
        List<Integer> spotIds = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int spotId = rs.getInt(1);
                    spotIds.add(spotId);
                }
            }
        }
        return spotIds;
    }
}
