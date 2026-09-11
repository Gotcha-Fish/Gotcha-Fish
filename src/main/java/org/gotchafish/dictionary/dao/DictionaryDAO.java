package org.gotchafish.dictionary.dao;

import org.gotchafish.dictionary.dto.DictionaryEntryDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DictionaryDAO {
    /**
     * 전체 물고기 목록과 사용자의 수집 여부를 조회한다.
     * <p>
     * "물고기 도감" 화면에서 전체 물고기 정보와 수집 상태를 함께 보여줄 때 사용된다.
     *
     * @param conn DB 연결 객체
     * @param userId 조회할 회원의 ID
     * @return 전체 물고기 목록 (수집 여부 포함)
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     * */
    List<DictionaryEntryDTO> findAll(Connection conn, Long userId) throws SQLException;


    /**
     * 사용자가 해당 물고기를 낚은 기록이 있는지 확인한다.
     * <p>
     * "물고기 획득" 시, 도감에 중복 기록되는 것을 막기 위해 사용된다.
     *
     * @param conn DB 연결 객체
     * @param userId 회원의 ID
     * @param fishId 물고기의 ID
     * @return 기록이 있으면 true, 없으면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     * */
    boolean existsByUserIdAndFishId(Connection conn, Long userId, Long fishId) throws SQLException;

    /**
     * 물고기를 낚은 기록을 도감에 저장한다.
     * <p>
     * 처음 낚은 물고기일 때, "물고기 획득" 로직에서 함께 호출된다.
     *
     * @param conn DB 연결 객체
     * @param userId 회원의 ID
     * @param fishId 물고기의 ID
     * @return 성공하면 true, 실패하면 false
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     * */
    boolean insert(Connection conn, Long userId, Long fishId) throws SQLException;
}
