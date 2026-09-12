package org.gotchafish.raid.service;

import org.gotchafish.raid.dto.RaidResultDTO;

import java.sql.SQLException;

public interface RaidService {
    /**
     * 1:1 대결 결과를 저장한다.
     * @param raidResult 대결 결과 정보
     * @return 대결 결과 저장 성공 여부
     * @throws SQLException DB 처리 중 오류 발생 시
     */
    boolean insertRaidResult(RaidResultDTO raidResult) throws SQLException;
}
