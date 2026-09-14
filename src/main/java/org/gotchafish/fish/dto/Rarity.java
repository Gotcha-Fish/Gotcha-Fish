package org.gotchafish.fish.dto;

public enum Rarity {
    NORMAL(50),
    SPECIAL(25),
    RARE(15),
    EPIC(7),
    LEGEND(3);

    private final int probability;

    Rarity(int probability) {
        this.probability = probability;
    }

    public int getProbability() {
        return probability;
    }

    public static Rarity pickRandom() {
        // 0 ~ 99 랜덤 확률 주기
        int random = (int) (Math.random() * 100);

        // 누적값으로 공간 할당
        int accumulated = 0;
        for (Rarity rarity : values()) {
            accumulated += rarity.getProbability();
            if (random < accumulated) return rarity;
        }

        return null; // for에서 못찾을 경우 혹시를 대비해서 null
    }
}
