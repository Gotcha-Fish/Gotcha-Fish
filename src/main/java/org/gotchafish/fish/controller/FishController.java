package org.gotchafish.fish.controller;

import org.gotchafish.fish.dto.FishDTO;
import org.gotchafish.fish.service.FishService;
import org.gotchafish.fish.service.FishServiceImpl;
import org.gotchafish.fish.view.FailView;
import org.gotchafish.fish.view.SuccessView;

import java.sql.SQLException;
import java.util.List;

public class FishController {
    private final FishService fishService = FishServiceImpl.getInstance();

    private static final FishController instance = new FishController();

    public static FishController getInstance() {
        return instance;
    }

    public boolean getMyFishInfo(Long userId) {
        try {
            List<FishDTO> fishList = fishService.getMyFish(userId);

            // 조회 성공
            SuccessView.myFishInfoSuccess(fishList);
            return true;
        } catch (RuntimeException e) {
            // 조회 실패
            FailView.myFishInfoFail(e.getMessage());
        } catch (SQLException e) {
            // DB 오류
            FailView.myFishInfoFail(e.getMessage());
        }

        return false;
    }

    public void sellFish(Long userId, Long fishId, int quantity) {
        try {
            FishDTO fish = fishService.sellFish(userId, fishId, quantity);

            // 판매 성공
            SuccessView.fishSellSuccess(fish, quantity);
        } catch (RuntimeException e) {
            // 판매 실패
            FailView.fishSellFail(e.getMessage());
        } catch (SQLException e) {
            // DB 오류
            FailView.fishSellFail(e.getMessage());
        }
    }
}
