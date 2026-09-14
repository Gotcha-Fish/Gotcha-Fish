package org.gotchafish.dictionary.dto;

import org.gotchafish.fish.dto.Rarity;

public class DictionaryEntryDTO {
    private Long fishId;
    private String fishName;
    private Rarity rarity;
    private String spotName; // tbl_spot.spot_name (조인해서 가져올 값)
    private boolean collected; // tbl_fish_dictionary에 이 유저 + 물고기 조합이 있는지 여부

    public DictionaryEntryDTO() {}

    public DictionaryEntryDTO(Long fishId, String fishName, Rarity rarity, String spotName, boolean collected) {
        this.fishId = fishId;
        this.fishName = fishName;
        this.rarity = rarity;
        this.spotName = spotName;
        this.collected = collected;
    }

    public Long getFishId() {
        return fishId;
    }

    public void setFishId(Long fishId) {
        this.fishId = fishId;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    @Override
    public String toString() {
        return "DictionaryEntryDTO{" +
                "fishId=" + fishId +
                ", fishName='" + fishName + '\'' +
                ", rarity=" + rarity +
                ", spotName='" + spotName + '\'' +
                ", collected=" + collected +
                '}';
    }
}
