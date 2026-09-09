package org.gotchafish.fish.dao;

import org.gotchafish.fish.dto.FishDTO;
import org.gotchafish.fish.dto.Rarity;
import org.gotchafish.rod.dao.RodDAO;
import org.gotchafish.rod.dao.RodDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FishDAOImpl implements FishDAO {
    private static final FishDAO instance = new FishDAOImpl();

    public static FishDAO getInstance() {
        return instance;
    }


    @Override
    public List<FishDTO> findFishByUserId(Connection conn, Long userId) throws SQLException {
        String sql = """
            SELECT
                f.fish_id, f.spot_id, f.rarity, f.fish_name, f.price,
                uf.quantity
            FROM tbl_user_fish uf
            JOIN tbl_fish f
                ON uf.fish_id = f.fish_id
            WHERE uf.user_id = ?
            AND uf.quantity > 0
        """;

        List<FishDTO> fishList = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    FishDTO fishDTO = new FishDTO(
                            rs.getLong("fish_id"),
                            rs.getLong("spot_id"),
                            Rarity.valueOf(rs.getString("rarity")),
                            rs.getString("fish_name"),
                            rs.getInt("price")
                    );
                    fishDTO.setQuantity(rs.getInt("quantity"));
                    fishList.add(fishDTO);
                }
            }
        }
        return fishList;
    }

    @Override
    public FishDTO findById(Connection conn, Long fishId) throws SQLException {

        String sql = """
            SELECT * FROM tbl_fish
            WHERE fish_id = ?
        """;
        FishDTO fishDTO = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, fishId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    fishDTO = new FishDTO(
                            rs.getLong("fish_id"),
                            rs.getLong("spot_id"),
                            Rarity.valueOf(rs.getString("rarity")),
                            rs.getString("fish_name"),
                            rs.getInt("price")
                    );
                }
                return fishDTO;
            }
        }
    }

    @Override
    public boolean existsUserFish(Connection conn, Long userId, Long fishId) throws SQLException {
        String sql = """
            SELECT COUNT(*) FROM tbl_user_fish
            WHERE user_id = ?
            AND fish_id = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, userId);
            ps.setLong(2, fishId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    @Override
    public boolean insertUserFish(Connection conn, Long userId, Long fishId, int quantity) throws SQLException {
        String sql = """
            INSERT INTO tbl_user_fish (user_id, fish_id, quantity)
            VALUES (?, ?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, userId);
            ps.setLong(2, fishId);
            ps.setInt(3, quantity);

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateUserFishQuantity(Connection conn, Long userId, Long fishId, int amount) throws SQLException {
        String sql = """
            UPDATE tbl_user_fish
            SET quantity = quantity + ?
            WHERE user_id = ?
            AND fish_id = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, amount);
            ps.setLong(2, userId);
            ps.setLong(3, fishId);

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<FishDTO> findBySpotId(Connection conn, Long spotId) throws SQLException {
        String sql = """
            SELECT * FROM tbl_fish
            WHERE spot_id = ?
        """;
        List<FishDTO> fishList = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, spotId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    FishDTO fishDTO = new FishDTO(
                            rs.getLong("fish_id"),
                            rs.getLong("spot_id"),
                            Rarity.valueOf(rs.getString("rarity")),
                            rs.getString("fish_name"),
                            rs.getInt("price")
                    );
                    fishList.add(fishDTO);
                }
                return fishList;
            }
        }
    }
}
