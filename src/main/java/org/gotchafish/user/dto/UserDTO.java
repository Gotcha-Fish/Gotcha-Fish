package org.gotchafish.user.dto;

import java.time.LocalDateTime;

public class UserDTO {
    private Long userId;
    private String loginId;
    private String password;
    private String nickname;
    private int gold;
    private int totalFishing;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public UserDTO() {
    }

    public UserDTO(String loginId, String password, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }

    public UserDTO(Long userId, String loginId, String password, String nickname,
                   int gold, int totalFishing, LocalDateTime regDate, LocalDateTime modDate) {
        this.userId = userId;
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.gold = gold;
        this.totalFishing = totalFishing;
        this.regDate = regDate;
        this.modDate = modDate;
    }

    public Long getUserId() {
        return userId;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public int getGold() {
        return gold;
    }

    public int getTotalFishing() {
        return totalFishing;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public LocalDateTime getModDate() {
        return modDate;
    }

    public void setUserId(Long userId) { this.userId = userId; }

    public void setLoginId(String loginId) { this.loginId = loginId; }

    public void setPassword(String password) { this.password = password; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public void setGold(int gold) { this.gold = gold; }

    public void setTotalFishing(int totalFishing) { this.totalFishing = totalFishing; }

    public void setRegDate(LocalDateTime regDate) { this.regDate = regDate; }

    public void setModDate(LocalDateTime modDate) { this.modDate = modDate; }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gold=" + gold +
                ", totalFishing=" + totalFishing +
                ", regDate=" + regDate +
                ", modDate=" + modDate +
                '}';
    }
}