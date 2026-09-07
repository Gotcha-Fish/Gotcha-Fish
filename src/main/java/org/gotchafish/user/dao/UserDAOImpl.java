package org.gotchafish.user.dao;

import org.gotchafish.user.dto.UserDTO;

import java.sql.*;

public class UserDAOImpl implements UserDAO {
    private static final UserDAO instance = new UserDAOImpl();

    public static UserDAO getInstance() {
        return instance;
    }

    @Override
    public Long insert(Connection conn, UserDTO user) throws SQLException {
        String sql = """
            INSERT INTO tbl_user (login_id, password, nickname)
            VALUES (?, ?, ?)
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getLoginId());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getNickname());

            int result = stmt.executeUpdate();

            if (result == 0) {
                return null;
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }

            return null;
        }
    }

    @Override
    public UserDTO findByUserId(Connection conn, Long userId) throws SQLException {
        String sql = """
            SELECT * FROM tbl_user
            WHERE user_id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UserDTO(
                            rs.getLong("user_id"),
                            rs.getString("login_id"),
                            rs.getString("password"),
                            rs.getString("nickname"),
                            rs.getInt("gold"),
                            rs.getInt("total_fishing"),
                            rs.getTimestamp("reg_date").toLocalDateTime(),
                            rs.getTimestamp("mod_date").toLocalDateTime()
                    );
                }
            }

            return null;
        }
    }

    @Override
    public UserDTO findByLoginId(Connection conn, String loginId) throws SQLException {
        String sql = """
            SELECT * FROM tbl_user
            WHERE login_id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, loginId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UserDTO(
                            rs.getLong("user_id"),
                            rs.getString("login_id"),
                            rs.getString("password"),
                            rs.getString("nickname"),
                            rs.getInt("gold"),
                            rs.getInt("total_fishing"),
                            rs.getTimestamp("reg_date").toLocalDateTime(),
                            rs.getTimestamp("mod_date").toLocalDateTime()
                    );
                }
            }

            return null;
        }
    }

    @Override
    public boolean existsByLoginId(Connection conn, String loginId) throws SQLException {
        String sql = """
            SELECT COUNT(*) FROM tbl_user
            WHERE login_id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, loginId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

            return false;
        }
    }

    @Override
    public boolean existsByNickname(Connection conn, String nickname) throws SQLException {
        String sql = """
            SELECT COUNT(*) FROM tbl_user
            WHERE nickname = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nickname);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

            return false;
        }
    }

    @Override
    public boolean updateNickname(Connection conn, Long userId, String nickname) throws SQLException {
        String sql = """
            UPDATE tbl_user
            SET nickname = ?
            WHERE user_id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nickname);
            stmt.setLong(2, userId);

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updatePassword(Connection conn, Long userId, String password) throws SQLException {
        String sql = """
            UPDATE tbl_user
            SET password = ?
            WHERE user_id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, password);
            stmt.setLong(2, userId);

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateGold(Connection conn, Long userId, int amount) throws SQLException {
        String sql = """
            UPDATE tbl_user
            SET gold = gold + ?
            WHERE user_id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, amount);
            stmt.setLong(2, userId);

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateFishingCount(Connection conn, Long userId) throws SQLException {
        String sql = """
            UPDATE tbl_user
            SET total_fishing = total_fishing + 1
            WHERE user_id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);

            return stmt.executeUpdate() > 0;
        }
    }
}