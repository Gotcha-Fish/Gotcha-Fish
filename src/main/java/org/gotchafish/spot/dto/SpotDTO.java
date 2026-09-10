package org.gotchafish.spot.dto;

public class SpotDTO {
    private Long spotId;
    private String spotName;
    private int unlockPrice;

    private boolean unlocked;

    public SpotDTO() {}

    public SpotDTO(Long spotId, String spotName, int unlockPrice) {
        this.spotId = spotId;
        this.spotName = spotName;
        this.unlockPrice = unlockPrice;
    }

    public Long getSpotId() {
        return spotId;
    }

    public void setSpotId(Long spotId) {
        this.spotId = spotId;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public int getUnlockPrice() {
        return unlockPrice;
    }

    public void setUnlockPrice(int unlockPrice) {
        this.unlockPrice = unlockPrice;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    @Override
    public String toString() {
        return "Spot{" +
                "낚시터 = " + spotId +
                ", 낚시터이름 = " + spotName + '\'' +
                ", 잠금해제가격 = " + unlockPrice +
                '}';
    }
}
