package org.gotchafish.dictionary.view;

import org.gotchafish.dictionary.controller.DictionaryController;
import org.gotchafish.user.dto.Session;

import java.util.Scanner;

public class DictionaryView {
    private final Scanner sc = new Scanner(System.in);

    private final DictionaryController dictionaryController = DictionaryController.getInstance();

    public void showDictionary() {
        System.out.println();
        System.out.println("================================");
        System.out.println("          물고기 도감");
        System.out.println("================================");

        Long userId = Session.getUserId();

        if (!dictionaryController.showDictionaryRate(userId)) return;

        int page = 1;

        do {
            if (!dictionaryController.showDictionaryPage(userId, page++)) return;

            System.out.println();
            System.out.println("1. 다음 페이지");
            System.out.println("0. 나가기");
            System.out.println();

            System.out.print("선택 : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        } while (true);
    }
}
