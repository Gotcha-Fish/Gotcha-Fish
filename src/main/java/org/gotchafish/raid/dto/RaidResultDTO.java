package org.gotchafish.raid.dto;

public class RaidResultDTO {
    private final Long hostId;
    private final Long guestId;
    private final Long hostFishId;
    private final Long guestFishId;
    private final Long winnerId;

    public RaidResultDTO(Long hostId, Long guestId, Long hostFishId, Long guestFishId, Long winnerId) {
        this.hostId = hostId;
        this.guestId = guestId;
        this.hostFishId = hostFishId;
        this.guestFishId = guestFishId;
        this.winnerId = winnerId;
    }

    public Long getHostId() {
        return hostId;
    }

    public Long getGuestId() {
        return guestId;
    }

    public Long getHostFishId() {
        return hostFishId;
    }

    public Long getGuestFishId() {
        return guestFishId;
    }

    public Long getWinnerId() {
        return winnerId;
    }
}