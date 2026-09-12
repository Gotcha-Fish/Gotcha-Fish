package org.gotchafish.raid.view;

import static org.gotchafish.common.ConsoleColor.RED;
import static org.gotchafish.common.ConsoleColor.RESET;

public class FailView {
    public static void raidFail(String message) {
        System.out.println(RED + "❌ " + message + RESET);
    }
}
