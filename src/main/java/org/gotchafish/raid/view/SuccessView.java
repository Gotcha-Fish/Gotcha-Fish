package org.gotchafish.raid.view;

import org.gotchafish.raid.dto.RaidRoomDTO;

import java.util.List;

public class SuccessView {
    public static void findRoomsSuccess(List<RaidRoomDTO> rooms) {
        System.out.println("번호   방 이름           방장");
        System.out.println("--------------------------------------");

        for (RaidRoomDTO room : rooms) {
            String roomId = String.valueOf(room.getRoomId());
            String roomName = room.getRoomName();
            String hostName = room.getHostName();

            System.out.print(roomId);
            printSpaces(7 - getDisplayWidth(roomId));

            System.out.print(roomName);
            printSpaces(18 - getDisplayWidth(roomName));

            System.out.println(hostName);
        }

        System.out.println("--------------------------------------");
    }

    private static int getDisplayWidth(String text) {
        int width = 0;

        for (char c : text.toCharArray()) {
            if (c >= 0xAC00 && c <= 0xD7A3) width += 2;
            else width += 1;
        }

        return width;
    }

    private static void printSpaces(int count) {
        System.out.print(" ".repeat(Math.max(0, count)));
    }
}
