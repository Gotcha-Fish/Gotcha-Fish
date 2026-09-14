package org.gotchafish.user.dto;

public class Session {
    private static Long userId;

    public static void setUserId(Long id) {
        userId = id;
    }

    public static Long getUserId() {
        return userId;
    }
}