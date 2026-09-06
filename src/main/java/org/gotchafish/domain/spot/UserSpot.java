package org.gotchafish.domain.spot;

import java.time.LocalDateTime;

public class UserSpot {
    private int userSpotId;
    private int userId;
    private int spotId;
    private LocalDateTime unlockDate;

    public UserSpot() {
    }

    public UserSpot(int userSpotId, int userId, int spotId, LocalDateTime unlockDate) {
        this.userSpotId = userSpotId;
        this.userId = userId;
        this.spotId = spotId;
        this.unlockDate = unlockDate;
    }

    public int getUserSpotId() {
        return userSpotId;
    }

    public void setUserSpotId(int userSpotId) {
        this.userSpotId = userSpotId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public LocalDateTime getUnlockDate() {
        return unlockDate;
    }

    public void setUnlockDate(LocalDateTime unlockDate) {
        this.unlockDate = unlockDate;
    }

    @Override
    public String toString() {
        return "UserSpot{" +
                "해제낚시터 = " + userSpotId +
                ", 유저 = " + userId +
                ", 낚시터 = " + spotId +
                ", 잠금해제일 = " + unlockDate +
                '}';
    }
}
