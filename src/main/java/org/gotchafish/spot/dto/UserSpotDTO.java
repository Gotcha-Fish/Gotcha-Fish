package org.gotchafish.spot.dto;

import java.time.LocalDateTime;

public class UserSpotDTO {
    private Long userSpotId;
    private Long userId;
    private Long spotId;
    private LocalDateTime unlockDate;

    public UserSpotDTO() {
    }

    public UserSpotDTO(Long userSpotId, Long userId, Long spotId, LocalDateTime unlockDate) {
        this.userSpotId = userSpotId;
        this.userId = userId;
        this.spotId = spotId;
        this.unlockDate = unlockDate;
    }

    public Long getUserSpotId() {
        return userSpotId;
    }

    public void setUserSpotId(Long userSpotId) {
        this.userSpotId = userSpotId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSpotId() {
        return spotId;
    }

    public void setSpotId(Long spotId) {
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
