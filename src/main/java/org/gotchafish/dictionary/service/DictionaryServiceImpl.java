package org.gotchafish.dictionary.service;

import org.gotchafish.common.JDBCUtil;
import org.gotchafish.dictionary.dao.DictionaryDAO;
import org.gotchafish.dictionary.dao.DictionaryDAOImpl;
import org.gotchafish.dictionary.dto.DictionaryEntryDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DictionaryServiceImpl implements DictionaryService {
    private static final DictionaryService instance = new DictionaryServiceImpl();

    private final DictionaryDAO dictionaryDAO = DictionaryDAOImpl.getInstance();

    private DictionaryServiceImpl() {}

    public static DictionaryService getInstance() { return instance; }

    @Override
    public List<DictionaryEntryDTO> getDictionary(Long userId) throws SQLException {
        try (Connection conn = JDBCUtil.getConnection()) {
            List<DictionaryEntryDTO> entries = dictionaryDAO.findAll(conn, userId);

            if (entries.isEmpty()) {
                throw new RuntimeException("도감 정보를 조회할 수 없습니다.");
            }
            return entries;
        }
    }
}
