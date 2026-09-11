package org.gotchafish.dictionary.controller;

import org.gotchafish.dictionary.dto.DictionaryEntryDTO;
import org.gotchafish.dictionary.service.DictionaryService;
import org.gotchafish.dictionary.service.DictionaryServiceImpl;
import org.gotchafish.dictionary.view.FailView;
import org.gotchafish.dictionary.view.SuccessView;

import java.sql.SQLException;
import java.util.List;

public class DictionaryController {
    private final DictionaryService dictionaryService = DictionaryServiceImpl.getInstance();

    private static final DictionaryController instance = new DictionaryController();

    public static DictionaryController getInstance() { return instance; }

    public boolean getDictionaryInfo(Long userId) {
        try {
            List<DictionaryEntryDTO> entries = dictionaryService.getDictionary(userId);

            SuccessView.dictionaryInfoSuccess(entries);
            return true;
        } catch (RuntimeException e) {
            FailView.dictionaryInfoFail(e.getMessage());
        } catch (SQLException e) {
            FailView.dictionaryInfoFail("도감 조회에 실패했습니다.");
        }
        return false;
    }
}
