package org.gotchafish.spot.view;

import org.gotchafish.spot.controller.SpotController;
import org.gotchafish.spot.dto.SpotDTO;
import org.gotchafish.user.dto.Session;

import static org.gotchafish.common.ConsoleColor.*;

import java.util.List;
import java.util.Scanner;

public class SpotView {
    private final Scanner sc = new Scanner(System.in);

    private final SpotController spotController = new SpotController();

    public void unlockSpot() {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "═".repeat(40) + RESET);
        System.out.println(BOLD + BRIGHT_YELLOW + "             🌊 낚시터 상점" + RESET);
        System.out.println(BRIGHT_CYAN +  "═".repeat(40) + RESET);
        System.out.println();

        spotController.spotList(Session.getUserId());

        System.out.println(YELLOW + "  [0] " + RESET + "뒤로가기");
        System.out.println();

        System.out.print(GREEN + "선택 : " + RESET);
        int choice = sc.nextInt();

        if (choice == 0) {
            sc.nextLine();
            return;
        }

        spotController.unlockSpot(Session.getUserId(), (long) choice);
    }

    public Long selectSpot(Long userId) {
        List<SpotDTO> spots = spotController.selectSpot(userId);

        if (spots == null) return null;

        System.out.print(BRIGHT_CYAN + "선택 > " + RESET);
        int choice = sc.nextInt();
        sc.nextLine();

        boolean found = false;

        for (SpotDTO spot : spots) {
            if (spot.getSpotId() == (long) choice) {
                found = true;
                if (!spot.isUnlocked()) {
                    FailView.selectSpotFail("잠긴 낚시터 입니다. 먼저 상점에서 해제해주세요.");
                    return null;
                }
            }
        }
        if (!found) {
            FailView.selectSpotFail("존재하지 않는 낚시터 입니다.");
            return null;
        }
        return (long) choice;
    }

}
