package org.gotchafish.spot.service;

import org.gotchafish.spot.dto.SpotDTO;

import java.sql.SQLException;
import java.util.List;

public interface SpotService {
    /**
     * 등록된 낚시터 전체 목록을 조회한다.
     * 사용자가 잠금 해제한 낚시터는 해제상태로,
     * 그렇지 않은 낚시터는 잠금상태로 조회한다.
     *
     * @param userId 사용자가 잠금 해제한 낚시터를 조회할 회원의 ID
     * @return 낚시터 목록
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    List<SpotDTO> getSpotList(Long userId) throws SQLException;

    /**
     * 특정 낚시터의 잠금을 해제한다.
     * 회원의 보유 골드가 낚시터의 잠금해제 가격 이상인지 확인하고,
     * 골드를 차감한 뒤 잠금 해제 기록을 저장한다.
     *
     * @param userId 잠금 해제를 요청한 회원 ID
     * @param spotId 잠금 해제할 낚시터 ID
     * @return 잠금 해제 성공시 true
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean unlockSpot(Long userId, Long spotId) throws SQLException;

    /**
     * 낚시터 ID로 특정 낚시터의 상세 정보를 조회한다.
     *
     * @param spotId 조회할 낚시터 ID
     * @return 낚시터 정보 (이름, 잠금해제 가격 포함), 없으면 null
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    SpotDTO getSpotById(Long spotId) throws SQLException;

}
