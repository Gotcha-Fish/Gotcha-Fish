package org.gotchafish.rod.dao;

import org.gotchafish.rod.dto.RodDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RodDAOImpl implements RodDAO {
    private static final RodDAO instance = new RodDAOImpl();

    public static RodDAO getInstance() {
        return instance;
    }

    @Override
    public List<RodDTO> findAllWithQuantity(Connection conn, Long userId) throws SQLException {
        String sql = """
            SELECT
                r.rod_id,
                r.rod_name,
                r.catch_probability,
                r.price,
                COALESCE(ur.quantity, 0) AS quantity
            FROM tbl_rod r
            LEFT JOIN tbl_user_rod ur
                ON r.rod_id = ur.rod_id
                AND ur.user_id = ?
            ORDER BY r.rod_id
        """;

        List<RodDTO> rods = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    rods.add(new RodDTO(
                            rs.getLong("rod_id"),
                            rs.getString("rod_name"),
                            rs.getInt("catch_probability"),
                            rs.getInt("price"),
                            rs.getInt("quantity")
                    ));
                }
            }
        }

        return rods;
    }

    @Override
    public List<RodDTO> findRodsByUserId(Connection conn, Long userId) throws SQLException {
        String sql = """
            SELECT
                r.rod_id,
                r.rod_name,
                r.catch_probability,
                r.price,
                ur.quantity
            FROM tbl_user_rod ur
            JOIN tbl_rod r
                ON ur.rod_id = r.rod_id
            WHERE ur.user_id = ?
              AND ur.quantity > 0
            ORDER BY r.rod_id
        """;

        List<RodDTO> rods = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    rods.add(new RodDTO(
                            rs.getLong("rod_id"),
                            rs.getString("rod_name"),
                            rs.getInt("catch_probability"),
                            rs.getInt("price"),
                            rs.getInt("quantity")
                    ));
                }
            }
        }

        return rods;
    }

    @Override
    public RodDTO findByRodId(Connection conn, Long rodId) throws SQLException {
        String sql = """
            SELECT rod_id, rod_name, catch_probability, price
            FROM tbl_rod
            WHERE rod_id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, rodId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new RodDTO(
                            rs.getLong("rod_id"),
                            rs.getString("rod_name"),
                            rs.getInt("catch_probability"),
                            rs.getInt("price")
                    );
                }
            }
        }

        return null;
    }

    @Override
    public int findUserRodQuantity(Connection conn, Long userId, Long rodId) throws SQLException {
        String sql = """
            SELECT quantity
            FROM tbl_user_rod
            WHERE user_id = ?
              AND rod_id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            stmt.setLong(2, rodId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("quantity");
                }
            }
        }

        return 0;
    }

    @Override
    public boolean existsUserRod(Connection conn, Long userId, Long rodId) throws SQLException {
        String sql = """
            SELECT COUNT(*)
            FROM tbl_user_rod
            WHERE user_id = ?
              AND rod_id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            stmt.setLong(2, rodId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }

        return false;
    }

    @Override
    public boolean insertUserRod(Connection conn, Long userId, Long rodId, int quantity) throws SQLException {
        String sql = """
            INSERT INTO tbl_user_rod (user_id, rod_id, quantity)
            VALUES (?, ?, ?)
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            stmt.setLong(2, rodId);
            stmt.setInt(3, quantity);

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateUserRodQuantity(Connection conn, Long userId, Long rodId, int amount) throws SQLException {
        String sql = """
            UPDATE tbl_user_rod
            SET quantity = quantity + ?
            WHERE user_id = ?
              AND rod_id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, amount);
            stmt.setLong(2, userId);
            stmt.setLong(3, rodId);

            return stmt.executeUpdate() > 0;
        }
    }
}