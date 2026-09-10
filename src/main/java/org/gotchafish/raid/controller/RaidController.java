package org.gotchafish.raid.controller;

import org.gotchafish.raid.dto.RaidRoomDTO;
import org.gotchafish.raid.socket.RaidClient;
import org.gotchafish.raid.view.FailView;
import org.gotchafish.raid.view.RaidView;
import org.gotchafish.raid.view.SuccessView;

import java.io.IOException;
import java.util.List;

public class RaidController {
    private static final RaidController instance = new RaidController();

    public static RaidController getInstance() { return instance; }

    public void createRoom(Long userId, String roomName) {
        try {
            // 클라이언트 Socket 생성
            RaidClient client = new RaidClient(userId);

            // 대결방 생성 요청
            client.createRoom(roomName);
        } catch (IOException e) {
            FailView.raidFail("서버와 통신 중 오류가 발생했습니다.");
        } catch (RuntimeException e) {
            FailView.raidFail(e.getMessage());
        }
    }

    public boolean findRooms(Long UserId) {
        try {
            // 클라이언트 Socket 생성
            RaidClient client = new RaidClient(UserId);

            // 참여 가능한 대결방 리스트 조회
            List<RaidRoomDTO> rooms = client.findWaitingRooms();

            // 대결방 리스트 조회 성공
            SuccessView.findRoomsSuccess(rooms);

            return true;
        } catch (IOException e) {
            FailView.raidFail("서버와 통신 중 오류가 발생했습니다.");
        } catch (RuntimeException e) {
            FailView.raidFail(e.getMessage());
        }

        return false;
    }

    public void joinRoom(Long userId, Long roomId) {
        try {
            // 클라이언트 Socket 생성
            RaidClient client = new RaidClient(userId);

            // 대결방 참가
            client.joinRoom(roomId);
        } catch (IOException e) {
            FailView.raidFail("서버와 통신 중 오류가 발생했습니다.");
        } catch (RuntimeException e) {
            FailView.raidFail(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new RaidView().showRaidMenu(2L);
    }
}