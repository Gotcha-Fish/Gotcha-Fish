package org.gotchafish.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface AttendanceDAO {
    /**
     * 해당 회원이 오늘 출석했는지 확인한다.
     * @param userId 출석 여부를 확인할 회원의 ID
     * @return 오늘 출석했다면 true, 출석하지 않았다면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean isAttendedToday(Connection conn, Long userId) throws SQLException;

    /**
     * 회원의 오늘 출석 기록을 저장한다.
     * @param userId 출석할 회원의 ID
     * @return 성공하면 true, 실패하면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean insert(Connection conn, Long userId) throws SQLException;
}