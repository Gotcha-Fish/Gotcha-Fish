package org.gotchafish.spot.dao;

import org.gotchafish.common.JDBCUtil;
import org.gotchafish.spot.dto.SpotDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpotDAOImpl implements SpotDAO {
    private static final SpotDAO instance = new SpotDAOImpl();

    private SpotDAOImpl() {
    }

    public static SpotDAO getInstance() {
        return instance;
    }

    @Override
    public List<SpotDTO> findAll(Connection conn) throws SQLException {

        List<SpotDTO> list = new ArrayList<>();
        String sql = """
            SELECT * FROM tbl_spot
            ORDER BY spot_id
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Long spotId = rs.getLong(1);
                    String spotName = rs.getString(2);
                    int unlockPrice = rs.getInt(3);

                    SpotDTO spotDTO = new SpotDTO(spotId, spotName, unlockPrice);
                    list.add(spotDTO);
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("낚시터 목록 조회 중 오류가 발생했습니다.");
        }
    }

    @Override
    public SpotDTO findById(Connection conn, Long spotId) throws SQLException {

        String sql = """
            SELECT * FROM tbl_spot
            WHERE spot_id = ?
        """;
        SpotDTO spotDTO = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, spotId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    spotDTO = new SpotDTO(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getInt(3)
                    );
                }
                return spotDTO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("낚시터가 없습니다.");
        }
    }




}
