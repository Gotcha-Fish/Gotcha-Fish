package org.gotchafish.fish.dto;

public class FishDTO {
    private Long fishId;
    private Long spotId;
    private Rarity rarity;
    private String fishName;
    private int price;

    private int quantity;

    public FishDTO() {
    }

    public FishDTO(Long fishId, Long spotId, Rarity rarity, String fishName, int price) {
        this.fishId = fishId;
        this.spotId = spotId;
        this.rarity = rarity;
        this.fishName = fishName;
        this.price = price;
    }

    public Long getFishId() {
        return fishId;
    }

    public void setFishId(Long fishId) {
        this.fishId = fishId;
    }

    public Long getSpotId() {
        return spotId;
    }

    public void setSpotId(Long spotId) {
        this.spotId = spotId;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "FishDTO{" +
                "fishId=" + fishId +
                ", spotId=" + spotId +
                ", rarity=" + rarity +
                ", fishName='" + fishName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
