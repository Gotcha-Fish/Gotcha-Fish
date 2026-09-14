package org.gotchafish.dictionary.controller;

import org.gotchafish.dictionary.dto.DictionaryEntryDTO;
import org.gotchafish.dictionary.service.DictionaryService;
import org.gotchafish.dictionary.service.DictionaryServiceImpl;
import org.gotchafish.dictionary.view.FailView;
import org.gotchafish.dictionary.view.SuccessView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DictionaryController {
    private final DictionaryService dictionaryService = DictionaryServiceImpl.getInstance();

    private static final DictionaryController instance = new DictionaryController();

    public static DictionaryController getInstance() { return instance; }

    public boolean showDictionaryRate(Long userId) {
        try {
            List<DictionaryEntryDTO> entries = dictionaryService.getDictionary(userId);

            int total = entries.size();
            int collectedCount = 0;

            for (DictionaryEntryDTO entry : entries) {
                if (entry.isCollected()) {
                    collectedCount++;
                }
            }

            SuccessView.dictionaryRateSuccess(collectedCount, total);
            return true;
        } catch (RuntimeException e) {
            FailView.dictionaryInfoFail(e.getMessage());
        } catch (SQLException e) {
            FailView.dictionaryInfoFail("도감 조회에 실패했습니다.");
        }
        return false;
    }

    public boolean showDictionaryPage(Long userId, int page) {
        try {
            List<DictionaryEntryDTO> entries = dictionaryService.getDictionary(userId);

            Map<String, List<DictionaryEntryDTO>> grouped = new LinkedHashMap<>();
            for (DictionaryEntryDTO entry : entries) {
                grouped.computeIfAbsent(entry.getSpotName(), k -> new ArrayList<>()).add(entry);
            }

            List<String> spotNames = new ArrayList<>(grouped.keySet());

            if (page - 1 >= spotNames.size()) {
                FailView.noMorePage();
                return false;
            }

            String spotName = spotNames.get(page - 1);
            SuccessView.dictionaryCategorySuccess(spotName, grouped.get(spotName));
            return true;
        } catch (RuntimeException e) {
            FailView.dictionaryInfoFail(e.getMessage());
        } catch (SQLException e) {
            FailView.dictionaryInfoFail("도감 조회에 실패했습니다.");
        }
        return false;
    }
}
