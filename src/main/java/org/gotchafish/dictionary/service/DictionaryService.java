package org.gotchafish.dictionary.service;

import org.gotchafish.dictionary.dto.DictionaryEntryDTO;

import java.sql.SQLException;
import java.util.List;

public interface DictionaryService {
    /**
     * 전체 물고기 목록과 사용자의 수집 여부를 조회한다.
     * <p>
     * "물고기 도감" 화면에서 사용된다.
     *
     * @param userId 조회할 회원의 ID
     * @return 전체 물고기 목록 (수집 여부 포함)
     * @throws RuntimeException 도감 조회 실패
     * @throws SQLException DB 처리 중 예외가 발생한 경우
     */
    List<DictionaryEntryDTO> getDictionary(Long userId) throws SQLException;
}
