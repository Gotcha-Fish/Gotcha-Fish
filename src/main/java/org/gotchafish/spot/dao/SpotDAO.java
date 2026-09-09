package org.gotchafish.spot.dao;

import org.gotchafish.spot.dto.SpotDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SpotDAO {

    /**
     * 등록된 낚시터를 모두 조회한다.
     * <p>
     * "낚시하기" 메뉴에서 잠금 해제된 낚시터 목록을 보여줄 때,
     * 그리고 "낚시터 상점" 화면에서 잠금/해제 상태를 포함한 전체 목록을 보여줄 때 사용된다.
     *
     * @return 낚시터 목록 (spot_id, spot_name, unlock_price 정보를 담은 SpotDTO 리스트)
     * @throws SQLException DB 처리중 예외가 발생한 경우
     */

    List<SpotDTO> findAll(Connection conn) throws SQLException;

    /**
     * 낚시터 ID로 특정 낚시터 하나를 조회한다.
     * <p>
     * 낚시터 구입/잠금해제 화면에서 사용자가 선택한 낚시터의
     * 이름과 잠금해제 가격을 확인할 때 사용된다.
     *
     * @param spotId 조회할 낚시터의 ID
     * @return 해당 ID를 가진 낚시터 정보 (없으면 null)
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    SpotDTO findById(Connection conn, Long spotId) throws SQLException;


}
