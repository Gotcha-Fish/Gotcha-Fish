package org.gotchafish.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceDAOImpl implements AttendanceDAO {
    private static final AttendanceDAO instance = new AttendanceDAOImpl();

    public static AttendanceDAO getInstance() {
        return instance;
    }

    @Override
    public boolean isAttendedToday(Connection conn, Long userId) throws SQLException {
        String sql = """
            SELECT COUNT(*) FROM tbl_attendance
            WHERE user_id = ?
            AND attendance_date = CURRENT_DATE
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

            return false;
        }
    }

    @Override
    public boolean insert(Connection conn, Long userId) throws SQLException {
        String sql = """
            INSERT INTO tbl_attendance (user_id)
            VALUES (?)
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);

            return stmt.executeUpdate() > 0;
        }
    }
}