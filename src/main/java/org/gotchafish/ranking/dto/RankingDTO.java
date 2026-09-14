package org.gotchafish.ranking.dto;

public class RankingDTO {
    private Long userId;
    private int rank;
    private String nickname;
    private int value;

    public RankingDTO(Long userId, int rank, String nickname, int value) {
        this.userId = userId;
        this.rank = rank;
        this.nickname = nickname;
        this.value = value;
    }

    public Long getUserId() {return userId;}

    public int getRank() { return rank; }

    public String getNickname() { return nickname; }

    public int getValue() { return value; }

    @Override
    public String toString() {
        return "RankingDTO{" +
                "userId=" + userId +
                ", rank=" + rank +
                ", nickname='" + nickname + '\'' +
                ", value=" + value +
                '}';
    }
}