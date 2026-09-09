package org.gotchafish.rod.service;

import org.gotchafish.rod.dto.RodDTO;

import java.sql.SQLException;
import java.util.List;

public interface RodService {
    /**
     * 낚시대 상점의 전체 낚시대 목록을 조회한다.
     * 사용자가 보유하지 않은 낚시대의 수량은 0으로 조회한다.
     * @param userId 낚시대를 조회할 회원의 ID
     * @return 전체 낚시대 목록과 사용자의 보유 수량
     * @throws RuntimeException 낚시대 목록 조회 실패
     * @throws SQLException DB 처리 중 예외가 발생
     */
    List<RodDTO> getRodShop(Long userId) throws SQLException;

    /**
     * 사용자가 선택할 수 있는 낚시대 목록을 제공한다.
     * 보유량이 0인 낚시대는 제외하고, 1개 이상인 낚시대만 조회한다.
     * @param userId 낚시대를 조회할 회원의 ID
     * @return 사용자가 보유한 낚시대 목록
     * @throws RuntimeException 사용자 보유 낚시대 조회 실패
     * @throws SQLException DB 처리 중 예외가 발생
     */
    List<RodDTO> getMyRods(Long userId) throws SQLException;


    /**
     * 낚시대 ID로 낚시대 정보를 조회한다.
     * @param rodId 조회할 낚시대의 ID
     * @return 조회된 낚시대 정보
     * @throws RuntimeException 존재하지 않는 낚시대
     * @throws SQLException DB 처리 중 예외가 발생
     */
    RodDTO getRod(Long rodId) throws SQLException;

    /**
     * 낚시대를 구입한다.
     * 사용자 골드가 충분한한지 확인해야 하며,
     * 이미 보유한 낚시대라면 수량을 증가시킨다.
     * 보유하지 않은 낚시대라면 새로운 보유 정보를 생성한다.
     * 낚시대 구입에 성공하면 구입한 가격만큼 사용자의 골드를 차감한다.
     * @param userId 낚시대를 구입하는 회원의 ID
     * @param rodId 구입할 낚시대의 ID
     * @return 구입 성공시 true, 실패시 false
     * @throws RuntimeException 존재하지 않는 낚시대/사용자, 수량 오류, 골드 부족, 구입 실패
     * @throws SQLException DB 처리 중 예외가 발생
     */
    boolean buyRod(Long userId, Long rodId, int quantity) throws SQLException;

    /**
     * 낚시에 사용한 낚싯대의 수량을 1개 차감한다.
     * @param userId 낚싯대를 사용하는 회원의 ID
     * @param rodId 사용하는 낚싯대의 ID
     * @return 차감 성공시 true, 실패시 false
     * @throws RuntimeException 존재하지 않는 낚시대/사용자, 수량 부족, 사용 실패
     * @throws SQLException DB 처리 중 예외가 발생
     */
    boolean useRod(Long userId, Long rodId) throws SQLException;
}