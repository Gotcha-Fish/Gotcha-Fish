package org.gotchafish.fish.service;

import org.gotchafish.fish.dto.FishDTO;

import java.sql.SQLException;
import java.util.List;

public interface FishService {
    /**
     * 사용자가 선택할 수 있는 물고기 목록을 제공한다.
     * 보유량이 0인 물고기는 제외하고, 1개 이상인 물고기만 조회한다.
     * @param userId 물고기를 조회할 회원의 ID
     * @return 사용자가 보유한 물고기 목록
     * @throws RuntimeException 사용자 보유 물고기 조회 실패
     * @throws SQLException DB 처리 중 예외가 발생
     */
    List<FishDTO> getMyFish(Long userId) throws SQLException;

    /**
     * 물고기를 판매한다.
     * <P>
     * 사용자가 판매하려는 수량만큼 물고기를 보유하고 있는지를 확인해야 하며,
     * 판매에 성공하면 판매한 가격만큼 사용자의 골드를 지급한다.
     *
     * @param userId 물고기를 판매하는 회원의 ID
     * @param fishId 판매할 물고기의 ID
     * @param quantity 판매할 물고기의 수량
     * @return 판매된 물고기 정보
     * @throws RuntimeException 존재하지 않는 물고기/사용자, 수량 부족, 판매 실패
     * @throws SQLException DB 처리 중 예외가 발생
     */
    FishDTO sellFish(Long userId, Long fishId, int quantity) throws SQLException;

    /**
     * 낚시터에 출몰하는 물고기 중 하나를 희귀도 확률에 따라 등장시킨다.
     * <p>
     * "낚시하기" 기능에서 낚시터 선택 직후, 어떤 물고기가 나타날지 결정할 때 사용된다.
     *
     * @param spotId 물고기가 출몰할 낚시터의 ID
     * @return 등장한 물고기 정보
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    FishDTO generateFish(Long spotId) throws SQLException;
}
