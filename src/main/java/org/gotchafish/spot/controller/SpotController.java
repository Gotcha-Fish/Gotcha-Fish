package org.gotchafish.spot.controller;

import org.gotchafish.spot.dto.SpotDTO;
import org.gotchafish.spot.service.SpotService;
import org.gotchafish.spot.service.SpotServiceImpl;
import org.gotchafish.spot.view.FailView;
import org.gotchafish.spot.view.SuccessView;
import org.gotchafish.user.dto.UserDTO;
import org.gotchafish.user.service.UserService;
import org.gotchafish.user.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class SpotController {
    private final SpotService spotService = SpotServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

    public void spotList(Long userId) {
        try {
            // 낚시터 조회
            List<SpotDTO> result = spotService.getSpotList(userId);
            UserDTO userDTO = userService.getUser(userId);

            SuccessView.spotSuccessView(userDTO, result);
        } catch (RuntimeException e) {
            FailView.spotFailView(e.getMessage());
        } catch (SQLException e) {
            FailView.spotFailView("낚시터 조회에 실패했습니다.");
        }
    }

    public void unlockSpot(Long userId, Long spotId) {
        try {
            SpotDTO spot = spotService.getSpotById(spotId);
            boolean result = spotService.unlockSpot(userId, spotId);

            if (result) {
                SuccessView.unlockSuccessView(spot.getSpotName(), spot.getUnlockPrice());
            }
        } catch (RuntimeException e) {
            FailView.spotFailView(e.getMessage());
        } catch (SQLException e) {
            FailView.spotFailView("낚시터 잠금 해제 중 오류가 발생했습니다.");
        }
    }
}
