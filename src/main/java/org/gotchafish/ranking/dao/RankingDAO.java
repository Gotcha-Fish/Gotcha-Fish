package org.gotchafish.ranking.dao;

import org.gotchafish.ranking.dto.RankingDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RankingDAO {
    /**
     * 낚은 물고기 수 기준 상위 랭킹 조회
     * @param conn DB 연결 객체
     * @param page 조회할 페이지 번호
     * @return 상위 랭킹 RankingDTO 리스트
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    List<RankingDTO> getFishingRanking(Connection conn, int page) throws SQLException;

    /**
     * 특정 사용자의 낚은 물고기 수 기준 순위 조회
     * @param conn DB 연결 객체
     * @param userId 조회할 회원의 ID
     * @return 해당 회원의 RankingDTO
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    RankingDTO getMyFishingRank(Connection conn, Long userId) throws SQLException;

    /**
     * 보유 골드 수 기준 상위 랭킹 조회
     * @param conn DB 연결 객체
     * @param page 조회할 페이지 번호
     * @return 상위 랭킹 RankingDTO 리스트
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    List<RankingDTO> getGoldRanking(Connection conn, int page) throws SQLException;

    /**
     * 특정 사용자의 보유 골드 기준 순위 조회
     * @param conn DB 연결 객체
     * @param userId 조회할 회원의 ID
     * @return 해당 회원의
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    RankingDTO getMyGoldRank(Connection conn, Long userId) throws SQLException;
}