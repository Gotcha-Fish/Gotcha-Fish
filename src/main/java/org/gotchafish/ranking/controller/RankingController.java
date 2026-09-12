package org.gotchafish.ranking.controller;

import org.gotchafish.ranking.dto.RankingDTO;
import org.gotchafish.ranking.service.RankingServiceImpl;
import org.gotchafish.ranking.view.SuccessView;
import org.gotchafish.ranking.view.FailView;

import java.sql.SQLException;
import java.util.List;

public class RankingController {
    private final RankingServiceImpl rankingService = RankingServiceImpl.getInstance();

    private static final RankingController getInstance = new RankingController();

    public static RankingController getInstance() { return getInstance; }

    public void showMyFishingRanking(Long userId) {
        try {
            RankingDTO ranking = rankingService.getMyFishingRank(userId);
            SuccessView.myFishingRankingSuccess(ranking);
        } catch (RuntimeException e) {
            // 조회 실패
            FailView.myRankingFail();
        } catch (SQLException e) {
            // DB 오류
            FailView.myRankingFail();
        }
    }

    public boolean showFishingRanking(int page) {
        try {
            List<RankingDTO> rankings = rankingService.getFishingRanking(page);
            SuccessView.fishingRankingSuccess(rankings);
            return true;
        } catch (RuntimeException e) {
            // 조회 실패
            FailView.myRankingFail();
        } catch (SQLException e) {
            // DB 오류
            FailView.myRankingFail();
        }
        return false;
    }

    public void showMyGoldRanking(Long userId) {
        try {
            RankingDTO ranking = rankingService.getMyGoldRank(userId);
            SuccessView.myGoldRankingSuccess(ranking);
        } catch (RuntimeException e) {
            // 조회 실패
            FailView.myRankingFail();
        } catch (SQLException e) {
            // DB 오류
            FailView.myRankingFail();
        }
    }

    public boolean showGoldRanking(int page) {
        try {
            List<RankingDTO> rankings = rankingService.getGoldRanking(page);
            SuccessView.goldRankingSuccess(rankings);
            return true;
        } catch (RuntimeException e) {
            // 조회 실패
            FailView.myRankingFail();
        } catch (SQLException e) {
            // DB 오류
            FailView.myRankingFail();
        }
        return false;
    }
}