package org.gotchafish.dictionary.view;

public class FailView {
    public static void dictionaryInfoFail(String message) {
        System.out.println(message);
    }
    public static void noMorePage() {
        System.out.println();
        System.out.println("더 이상 낚시터가 없습니다.");
    }
}

