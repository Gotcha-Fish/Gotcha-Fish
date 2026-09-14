package org.gotchafish.spot.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserSpotDAO {
    /**
     * 회원의 낚시터 잠금 해제 기록을 저장한다.
     *
     * 낚시터 잠금 해제 시, 골드 차감과 함께 이 기록이 저장되어야 한다.
     *
     * @param conn 트랜잭션에 사용할 Connection
     * @param userId 잠금 해제한 회원 ID
     * @param spotId 잠금 해제한 낚시터 ID
     * @return 성공하면 true, 실패하면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean insert(Connection conn, Long userId, Long spotId) throws SQLException;

    /**
     * 회원이 특정 낚시터를 이미 잠금 해제했는지 확인한다.
     *
     * 중복으로 잠금 해제(중복 구매)되는 것을 막기 위해 사용된다.
     *
     * @param conn 조회에 사용할 Connection
     * @param userId 확인할 회원 ID
     * @param spotId 확인할 낚시터 ID
     * @return 이미 잠금 해제했으면 true, 아니면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean existsByUserIdAndSpotId(Connection conn, Long userId, Long spotId) throws SQLException;

    /**
     * 회원이 잠금 해제한 낚시터 ID 목록을 조회한다.
     *
     * "낚시터 상점"에서 잠금/해제 상태를 표시할 때,
     * 전체 낚시터 목록과 이 목록을 비교해서 상태를 판단하는데 사용된다.
     *
     * @param conn 조회에 사용할 Connection
     * @param userId 조회할 회원 ID
     * @return 잠금 해제한 낚시터 ID 목록
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    List<Long> findSpotIdsByUserId(Connection conn, Long userId) throws SQLException;
}
