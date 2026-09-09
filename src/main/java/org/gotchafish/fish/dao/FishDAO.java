package org.gotchafish.fish.dao;

import org.gotchafish.fish.dto.FishDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface FishDAO {

    /**
     * 사용자가 보유한 물고기 목록을 조회한다.
     * <p>
     * 보유 수량이 0인 물고기는 조회하지 않으며,
     * "물고기 조회"(판매 화면)에서 현재 보유 중인 물고기를 보여줄 때 사용된다.
     *
     * @param conn DB 연결 객체
     * @param userId 조회할 회원의 ID
     * @return 사용자가 보유한 물고기 목록
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    List<FishDTO> findFishByUserId(Connection conn, Long userId) throws SQLException;

    /**
     * 물고기 ID로 특정 물고기 하나를 조회한다.
     * <p>
     * 물고기 판매 화면에서 사용자가 선택한 물고기의
     * 이름과 판매 가격을 확인할 때 사용된다.
     *
     * @param fishId 조회할 물고기의 ID
     * @return 해당 ID를 가진 물고기 정보 (없으면 null)
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    FishDTO findById(Connection conn, Long fishId) throws SQLException;

    /**
     * 사용자가 해당 물고기를 보유한 이력이 있는지 확인한다.
     * <p>
     * "물고기 획득" 시, 새로 보유 이력을 추가(insert)할지
     * 기존 수량만 증가(update)시킬지 판단할 때 사용된다.
     *
     * @param conn DB 연결 객체
     * @param userId 회원의 ID
     * @param fishId 물고기의 ID
     * @return 보유 이력이 있으면 true, 없으면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean existsUserFish(Connection conn, Long userId, Long fishId) throws SQLException;

    /**
     * 사용자의 물고기 보유 정보를 새로 추가한다.
     * <p>
     * 해당 물고기를 처음 낚았을 때, 보유 이력을 최초로 등록하는 데 사용된다.
     *
     * @param conn DB 연결 객체
     * @param userId 회원의 ID
     * @param fishId 물고기의 ID
     * @param quantity 최초 등록할 수량
     * @return 성공하면 true, 실패하면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean insertUserFish(Connection conn, Long userId, Long fishId, int quantity) throws SQLException;

    /**
     * 사용자가 보유한 물고기 수량을 변경한다.
     * <p>
     * 물고기 획득 시 양수를, 물고기 판매 시 음수를 전달하여
     * 기존 보유 수량에 더하는 방식으로 사용된다.
     *
     * @param conn DB 연결 객체
     * @param userId 회원의 ID
     * @param fishId 물고기의 ID
     * @param amount 변경할 수량 (증가는 양수, 감소는 음수)
     * @return 성공하면 true, 실패하면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    boolean updateUserFishQuantity(Connection conn, Long userId, Long fishId, int amount) throws SQLException;

    /**
     * 낚시터 ID로 해당 낚시터에 출몰하는 물고기 목록을 조회한다.
     * <p>
     * "낚시하기" 기능에서 랜덤 추첨할 물고기 후보군을 가져올 때 사용된다.
     *
     * @param spotId 조회할 낚시터의 ID
     * @return 해당 낚시터에서 출몰하는 물고기 목록
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    List<FishDTO> findBySpotId(Connection conn, Long spotId) throws SQLException;
}
