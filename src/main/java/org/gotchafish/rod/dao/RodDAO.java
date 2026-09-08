package org.gotchafish.rod.dao;

import org.gotchafish.rod.dto.RodDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RodDAO {
    /**
     * 전체 낚시대 목록과 사용자의 보유 수량을 조회한다.
     * 보유하지 않은 낚시대의 수량은 0으로 조회한다.
     * @param conn DB 연결 객체
     * @param userId 조회할 회원의 ID
     * @return 전체 낚시대 목록
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    List<RodDTO> findAllWithQuantity(Connection conn, Long userId) throws SQLException;

    /**
     * 사용자가 보유한 낚시대 목록을 조회한다.
     * 보유 수량이 0인 낚시대는 조회하지 않는다.
     * @param conn DB 연결 객체
     * @param userId 조회할 회원의 ID
     * @return 사용자가 보유한 낚시대 목록
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    List<RodDTO> findRodsByUserId(Connection conn, Long userId) throws SQLException;

    /**
     * 낚시대 ID로 낚시대 정보를 조회한다.
     * @param conn DB 연결 객체
     * @param rodId 조회할 낚시대의 ID
     * @return 낚시대 정보, 존재하지 않으면 null
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    RodDTO findByRodId(Connection conn, Long rodId) throws SQLException;

    /**
     * 사용자가 해당 낚시대를 보유한 이력이 있는지 확인한다.
     * @param conn DB 연결 객체
     * @param userId 회원의 ID
     * @param rodId 낚시대의 ID
     * @return 보유 이력이 있으면 true, 없으면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean existsUserRod(Connection conn, Long userId, Long rodId) throws SQLException;

    /**
     * 사용자의 낚시대 보유 정보를 추가한다.
     * @param conn DB 연결 객체
     * @param userId 회원의 ID
     * @param rodId 낚시대의 ID
     * @param quantity 추가할 수량
     * @return 성공하면 true, 실패하면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean insertUserRod(Connection conn, Long userId, Long rodId, int quantity) throws SQLException;

    /**
     * 사용자가 보유한 낚시대 수량을 변경한다.
     * @param conn DB 연결 객체
     * @param userId 회원의 ID
     * @param rodId 낚시대의 ID
     * @param quantity 변경할 수량
     * @return 성공하면 true, 실패하면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean updateUserRodQuantity(Connection conn, Long userId, Long rodId, int quantity) throws SQLException;
}