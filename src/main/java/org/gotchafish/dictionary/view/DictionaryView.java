package org.gotchafish.dictionary.view;

import org.gotchafish.dictionary.controller.DictionaryController;
import org.gotchafish.user.dto.Session;

public class DictionaryView {
    private final DictionaryController dictionaryController = DictionaryController.getInstance();

    public void showDictionary() {
        System.out.println();
        System.out.println("================================");
        System.out.println("          물고기 도감");
        System.out.println("================================");

        dictionaryController.getDictionaryInfo(Session.getUserId());
    }
}
