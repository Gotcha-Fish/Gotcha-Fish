package org.gotchafish.raid.dto;

public class RaidRoomDTO {
    private Long roomId;
    private Long hostUserId;

    public  RaidRoomDTO(Long roomId, Long hostUserId) {
        this.roomId = roomId;
        this.hostUserId = hostUserId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Long getHostUserId() {
        return hostUserId;
    }
}