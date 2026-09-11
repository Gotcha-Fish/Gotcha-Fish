package org.gotchafish.dictionary.dao;

import org.gotchafish.dictionary.dto.DictionaryEntryDTO;
import org.gotchafish.fish.dto.Rarity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryDAOImpl implements DictionaryDAO {
    private static final DictionaryDAO instance = new DictionaryDAOImpl();

    public static DictionaryDAO getInstance() {
        return instance;
    }


    @Override
    public List<DictionaryEntryDTO> findAll(Connection conn, Long userId) throws SQLException {
        String sql = """
            SELECT
                f.fish_id, f.fish_name, f.rarity,
                s.spot_name,
                CASE WHEN d.dictionary_id IS NOT NULL THEN true ELSE false END
            AS collected
            FROM tbl_fish f
            JOIN tbl_spot s
                ON f.spot_id = s.spot_id
            LEFT JOIN tbl_fish_dictionary d
                ON f.fish_id = d.fish_id
                AND d.user_id = ?
        """;
        List<DictionaryEntryDTO> dictionary = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    DictionaryEntryDTO entry = new DictionaryEntryDTO(
                        rs.getLong("fish_id"),
                        rs.getString("fish_name"),
                        Rarity.valueOf(rs.getString("rarity")),
                        rs.getString("spot_name"),
                        rs.getBoolean("collected")
                    );
                    dictionary.add(entry);
                }
            }
        }

        return dictionary;
    }

    @Override
    public boolean existsByUserIdAndFishId(Connection conn, Long userId, Long fishId) throws SQLException {
        String sql = """
            SELECT COUNT(*) FROM tbl_fish_dictionary
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
    public boolean insert(Connection conn, Long userId, Long fishId) throws SQLException {
        String sql = """
            INSERT INTO tbl_fish_dictionary (user_id, fish_id)
            VALUES (?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, userId);
            ps.setLong(2, fishId);

            return ps.executeUpdate() > 0;
        }
    }
}
