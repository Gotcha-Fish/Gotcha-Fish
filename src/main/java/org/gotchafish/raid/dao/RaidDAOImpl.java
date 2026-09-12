package org.gotchafish.raid.dao;

import org.gotchafish.raid.dto.RaidResultDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RaidDAOImpl implements RaidDAO {
    private final static RaidDAO instance = new RaidDAOImpl();

    public static RaidDAO getInstance() { return instance; }

    @Override
    public boolean insertRaidResult(Connection conn, RaidResultDTO raidResultDTO) throws SQLException {

        String sql = """
            INSERT INTO tbl_raid (
                host_id,
                guest_id,
                host_fish_id,
                guest_fish_id,
                winner_id,
                status,
                end_date
            )
            VALUES (?, ?, ?, ?, ?, 'FINISHED', CURRENT_TIMESTAMP)
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, raidResultDTO.getHostId());
            stmt.setLong(2, raidResultDTO.getGuestId());
            stmt.setLong(3, raidResultDTO.getHostFishId());
            stmt.setLong(4, raidResultDTO.getGuestFishId());
            stmt.setLong(5, raidResultDTO.getWinnerId());

            return stmt.executeUpdate() > 0;
        }
    }
}