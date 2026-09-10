package org.gotchafish.raid.room;

import org.gotchafish.raid.socket.RaidClientThread;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RaidRoomManager {
    private final Map<Long, RaidRoom> rooms = new ConcurrentHashMap<>();
    private Long nextRoomId = 1L;

    /**
     * 새로운 대결방을 생성한다.
     * @param hostThread 방을 생성한 Host의 Thread
     * @return 생성된 RaidRoom
     */
    public synchronized RaidRoom createRoom(RaidClientThread hostThread) {
        Long roomId = nextRoomId++;

        RaidRoom room = new RaidRoom(roomId, hostThread);
        rooms.put(roomId, room);

        return room;
    }

    /**
     * 방 번호로 대결방을 조회한다.
     * @param roomId 방 번호
     * @return 해당 방 객체, 존재하지 않으면 null
     */
    public RaidRoom findRoom(Long roomId) {
        return rooms.get(roomId);
    }

    /**
     * 대결방에 참가한다.
     * @param roomId 방 번호
     * @param guestThread 참가하는 Guest의 Thread
     * @return 참가한 RaidRoom
     */
    public RaidRoom joinRoom(Long roomId, RaidClientThread guestThread) {
        RaidRoom room = findRoom(roomId);

        if (room == null) {
            throw new RuntimeException("존재하지 않는 방입니다.");
        }

        // 같은 방의 정원 확인과 참가 처리를 하나의 작업으로 보호한다.
        synchronized (room) {
            if (room.isFull()) {
                throw new RuntimeException("대결이 시작된 방입니다.");
            }

            room.join(guestThread);
            return room;
        }
    }

    /**
     * 대결방을 삭제한다.
     * @param roomId 방 번호
     */
    public void removeRoom(Long roomId) {
        rooms.remove(roomId);
    }

    /**
     * 현재 참가 가능한 대결방 목록 조회
     * @return 참가 가능한 RaidRoom 리스트
     */
    public List<RaidRoom> findWaitingRooms() {
        return rooms.values().stream()
                .filter(room -> !room.isFull())
                .toList();
    }
}