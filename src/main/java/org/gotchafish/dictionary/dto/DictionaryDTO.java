package org.gotchafish.dictionary.dto;

import java.time.LocalDateTime;

public class DictionaryDTO {

    private Long dictionaryId;
    private Long userId;
    private Long fishId;
    private LocalDateTime catchDate;

    public DictionaryDTO() {}
    public DictionaryDTO(Long dictionaryId, Long userId, Long fishId, LocalDateTime catchDate) {
        this.dictionaryId = dictionaryId;
        this.userId = userId;
        this.fishId = fishId;
        this.catchDate = catchDate;
    }

    public Long getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFishId() {
        return fishId;
    }

    public void setFishId(Long fishId) {
        this.fishId = fishId;
    }

    public LocalDateTime getCatchDate() {
        return catchDate;
    }

    public void setCatchDate(LocalDateTime catchDate) {
        this.catchDate = catchDate;
    }

    @Override
    public String toString() {
        return "DictionaryDTO{" +
                "dictionaryId=" + dictionaryId +
                ", userId=" + userId +
                ", fishId=" + fishId +
                ", catchDate=" + catchDate +
                '}';
    }
}
