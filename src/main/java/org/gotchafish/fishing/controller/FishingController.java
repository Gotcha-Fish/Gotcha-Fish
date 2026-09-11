package org.gotchafish.fishing.controller;

import org.gotchafish.dictionary.dao.DictionaryDAO;
import org.gotchafish.dictionary.dao.DictionaryDAOImpl;
import org.gotchafish.fish.dto.FishDTO;
import org.gotchafish.fish.service.FishService;
import org.gotchafish.fish.service.FishServiceImpl;
import org.gotchafish.fishing.view.FailView;
import org.gotchafish.fishing.view.SuccessView;
import org.gotchafish.rod.dto.RodDTO;
import org.gotchafish.rod.service.RodService;
import org.gotchafish.rod.service.RodServiceImpl;
import org.gotchafish.spot.service.SpotService;
import org.gotchafish.spot.service.SpotServiceImpl;
import org.gotchafish.user.service.UserService;
import org.gotchafish.user.service.UserServiceImpl;

import java.sql.SQLException;

public class FishingController {
    private final SpotService spotService = SpotServiceImpl.getInstance();
    private final FishService fishService = FishServiceImpl.getInstance();
    private final RodService rodService = RodServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();
    private final DictionaryDAO dictionaryDAO = DictionaryDAOImpl.getInstance();

    private static final FishingController instance = new FishingController();

    public static FishingController getInstance() {
        return instance;
    }

    public FishDTO generateFish(Long spotId) {
        try {
            FishDTO fish = fishService.generateFish(spotId);
            SuccessView.fishAppeared(fish);
            return fish;
        } catch (RuntimeException e) {
            FailView.fishingFail(e.getMessage());
        } catch (SQLException e) {
            FailView.fishingFail("물고기 생성에 실패했습니다.");
        }
        return null;
    }

    public RodDTO getRod(Long rodId) {
        try {
            RodDTO rods = rodService.getRod(rodId);
            SuccessView.rodGet(rods);
            return rods;
        } catch (RuntimeException e) {
            FailView.fishingFail(e.getMessage());
        } catch (SQLException e) {
            FailView.fishingFail("낚시대 조회에 실패했습니다.");
        }

        return null;
    }

    public boolean isCaught(int catchProbability) {
        int random = (int) (Math.random() * 100);
        return random < catchProbability;
    }
}
