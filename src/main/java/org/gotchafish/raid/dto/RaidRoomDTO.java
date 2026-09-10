package org.gotchafish.raid.dto;

public class RaidRoomDTO {
    private Long roomId;
    private String roomName;
    private String hostName;

    public  RaidRoomDTO(Long roomId, String roomName, String hostName) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.hostName = hostName;
    }

    public Long getRoomId() {
        return roomId;
    }

    public String getRoomName() { return roomName; }

    public String getHostName() { return hostName; }
}