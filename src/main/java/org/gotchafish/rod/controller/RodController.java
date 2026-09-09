package org.gotchafish.rod.controller;

import org.gotchafish.rod.dto.RodDTO;
import org.gotchafish.rod.service.RodService;
import org.gotchafish.rod.service.RodServiceImpl;
import org.gotchafish.rod.view.SuccessView;
import org.gotchafish.rod.view.FailView;
import org.gotchafish.user.dto.UserDTO;
import org.gotchafish.user.service.UserService;
import org.gotchafish.user.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class RodController {
    private final RodService rodService = RodServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

    private static final RodController rodController = new RodController();

    public static RodController getInstance() {
        return rodController;
    }

    public boolean getRodShopInfo(Long userId) {
        try {
            UserDTO user = userService.getUser(userId);

            List<RodDTO> rodList = rodService.getRodShop(userId);

            // 조회 성공
            SuccessView.rodShopInfoSuccess(user, rodList);
            return true;
        } catch (RuntimeException e) {
            // 조회 실패
            FailView.rodShopInfoFail(e.getMessage());
        } catch (SQLException e) {
            // DB 오류
            FailView.rodShopInfoFail(e.getMessage());
        }
        return false;
    }

    public void buyRod(Long userId, Long rodId, int quantity) {
        try {
            if(rodService.buyRod(userId, rodId, quantity)) {
                // 구입 성공
                SuccessView.rodBuySuccess(rodService.getRod(rodId), quantity);
            }
        } catch (RuntimeException e) {
            // 구입 실패
            FailView.rodBuyFail(e.getMessage());
        } catch (SQLException e) {
            // DB 오류
            FailView.rodBuyFail(e.getMessage());
        }
    }

    public boolean getMyRodSInfo(Long userId) {
        try {
            List<RodDTO> rodList = rodService.getMyRods(userId);

            // 조회 성공
            SuccessView.MyRodsInfoSuccess(rodList);
            return true;
        } catch (RuntimeException e) {
            // 조회 실패
            FailView.myRodsInfoFail(e.getMessage());
        } catch (SQLException e) {
            // DB 오류
            FailView.myRodsInfoFail(e.getMessage());
        }
        return false;
    }

    public void useRod(Long userId, Long rodId) {
        try {
            if(rodService.useRod(userId, rodId)) {
                // 사용 성공
                SuccessView.rodUseSuccess(rodService.getRod(rodId));
            }
        } catch (RuntimeException e) {
            // 사용 실패
            FailView.rodUseFail(e.getMessage());
        } catch (SQLException e) {
            // DB 오류
            FailView.rodUseFail(e.getMessage());
        }
    }
}