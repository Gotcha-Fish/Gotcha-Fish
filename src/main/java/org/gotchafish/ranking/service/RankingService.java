package org.gotchafish.ranking.service;

import org.gotchafish.ranking.dto.RankingDTO;

import java.sql.SQLException;
import java.util.List;

public interface RankingService {
    /**
     * 낚은 물고기 수 기준 상위 랭킹 조회
     * @param page 조회할 랭킹 페이지
     * @return 해당 페이지의 랭킹 객체 목록
     * @throws RuntimeException 랭킹 조회 실패
     * @throws SQLException DB 처리 중 예외가 발생
     */
    List<RankingDTO> getFishingRanking(int page) throws SQLException;

    /**
     * 현재 사용자의 낚은 물고기 수 기준 순위 조회
     * @param userId 조회할 회원의 ID
     * @return 사용자의낚은 물고기 수 랭킹 객체
     * @throws RuntimeException 존재하지 않는 사용자, 랭킹 조회 실패
     * @throws SQLException DB 처리 중 예외가 발생
     */
    RankingDTO getMyFishingRank(Long userId) throws SQLException;

    /**
     * 보유 골드 기준 상위 랭킹 조회
     * @param page 조회할 랭킹 페이지
     * @return 해당 페이지의 랭킹 객체 목록
     * @throws RuntimeException 랭킹 조회 실패
     */
    List<RankingDTO> getGoldRanking(int page) throws SQLException;

    /**
     * 현재 사용자의 보유 골드 기준 순위 조회
     * @param userId 조회할 회원의 ID
     * @return 사용자 보유 골드 랭킹 객체
     * @throws RuntimeException 존재하지 않는 사용자, 랭킹 조회 실패
     * @throws SQLException DB 처리 중 예외가 발생
     */
    RankingDTO getMyGoldRank(Long userId) throws SQLException;
}