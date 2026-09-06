package org.gotchafish.domain.spot;

public class Spot {
    private int spotId;
    private String spotName;
    private int unlockPrice;

    public Spot() {}

    public Spot(int spotId, String spotName, int unlockPrice) {
        this.spotId = spotId;
        this.spotName = spotName;
        this.unlockPrice = unlockPrice;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
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

    @Override
    public String toString() {
        return "Spot{" +
                "낚시터 = " + spotId +
                ", 낚시터이름 = " + spotName + '\'' +
                ", 잠금해제가격 = " + unlockPrice +
                '}';
    }
}
