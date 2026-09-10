package org.gotchafish.raid.room;

import org.gotchafish.raid.socket.RaidClientThread;

public class RaidRoom {
    private final Long roomId;
    private final RaidClientThread hostThread;
    private RaidClientThread guestThread;

    public RaidRoom(Long roomId, RaidClientThread hostThread) {
        this.roomId = roomId;
        this.hostThread = hostThread;
    }

    public void join(RaidClientThread guestThread) {
        this.guestThread = guestThread;
    }

    public Long getRoomId() {
        return roomId;
    }

    public boolean isFull() {
        return guestThread != null;
    }

    public Long getHostUserId() {
        return hostThread.getUserId();
    }

    public RaidClientThread getHostThread() {
        return hostThread;
    }

    public RaidClientThread getGuestThread() {
        return guestThread;
    }
}
