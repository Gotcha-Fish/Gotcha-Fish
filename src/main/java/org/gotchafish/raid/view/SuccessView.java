package org.gotchafish.raid.view;

import org.gotchafish.raid.dto.RaidRoomDTO;

import java.util.List;

import static org.gotchafish.common.ConsoleColor.*;

public class SuccessView {

    public static void findRoomsSuccess(List<RaidRoomDTO> rooms) {
        System.out.println("  " + BOLD + "번호    방 이름               호스트" + RESET);
        System.out.println(BRIGHT_CYAN + "  " + "═".repeat(36) + RESET);

        for (RaidRoomDTO room : rooms) {
            String roomId = String.valueOf(room.getRoomId());
            String roomName = room.getRoomName();
            String hostName = room.getHostName();

            System.out.print("  " + BRIGHT_BLUE + roomId + RESET);
            printSpaces(8 - getDisplayWidth(roomId));

            System.out.print(roomName);
            printSpaces(14 - getDisplayWidth(roomName));

            printSpaces(14 - getDisplayWidth(hostName));
            System.out.println(hostName);
        }

        System.out.println(BRIGHT_CYAN + "  " + "═".repeat(36) + RESET);
    }

    private static int getDisplayWidth(String text) {
        int width = 0;

        for (char c : text.toCharArray()) {
            if (c >= 0xAC00 && c <= 0xD7A3) {
                width += 2;
            } else {
                width += 1;
            }
        }

        return width;
    }

    private static void printSpaces(int count) {
        System.out.print(" ".repeat(Math.max(0, count)));
    }
}