package org.gotchafish.dictionary.view;

import org.gotchafish.dictionary.controller.DictionaryController;
import org.gotchafish.user.dto.Session;

import java.util.Scanner;

import static org.gotchafish.common.ConsoleColor.*;

public class DictionaryView {
    private final Scanner sc = new Scanner(System.in);

    private final DictionaryController dictionaryController = DictionaryController.getInstance();

    public void showDictionary() {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "═".repeat(40) + RESET);
        System.out.println(BOLD + BRIGHT_YELLOW + "             📖 물고기 도감" + RESET);
        System.out.println(BRIGHT_CYAN + "═".repeat(40) + RESET);

        Long userId = Session.getUserId();

        if (!dictionaryController.showDictionaryRate(userId)) return;

        int page = 1;

        do {
            if (!dictionaryController.showDictionaryPage(userId, page++)) return;

            System.out.println();
            System.out.println(YELLOW + "  [1] " + RESET + "다음 페이지");
            System.out.println(YELLOW + "  [0] " + RESET + "나가기");
            System.out.println();

            System.out.print(GREEN + "선택 : " + RESET);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    break;
                case 0:
                    return;
                default:
                    System.out.println(RED + "잘못된 선택입니다." + RESET);
            }
        } while (true);
    }
}
