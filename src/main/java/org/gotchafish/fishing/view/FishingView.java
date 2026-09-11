package org.gotchafish.fishing.view;

import org.gotchafish.fish.dto.FishDTO;
import org.gotchafish.fishing.controller.FishingController;
import org.gotchafish.rod.controller.RodController;
import org.gotchafish.rod.dto.RodDTO;
import org.gotchafish.spot.view.SpotView;
import org.gotchafish.user.dto.Session;

import java.util.Scanner;

public class FishingView {
    private final Scanner sc = new Scanner(System.in);

    private final FishingController fishingController = FishingController.getInstance();
    private final RodController rodController = RodController.getInstance();
    private final SpotView spotView = new SpotView();

    public void startFishing() {
        System.out.println();
        System.out.println("================================");
        System.out.println("            낚시하기");
        System.out.println("================================");

        Long userId = Session.getUserId();

        // 1. 낚시터 선택
        Long spotId = spotView.selectSpot(userId);

        // 2. 물고기 등장
        FishDTO fish = fishingController.generateFish(spotId);
        if (fish == null) return;

        // 3. 낚시대 선택
        if (!rodController.getMyRodSInfo(userId)) return;

        System.out.println("선택 : ");
        int choice = sc.nextInt();
        sc.nextLine();

        RodDTO rod = fishingController.getRod((long) choice);

        // 4. 낚시 결과 판정
        boolean caught = fishingController.isCaught(rod.getCatchProbability());

        // 5. 마무리
        fishingController.finishFishing(userId, rod.getRodId(), fish, caught);
    }
}
