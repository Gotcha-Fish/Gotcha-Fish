package org.gotchafish.user.dto;

public class LoginResult {
    private UserDTO user;
    private boolean attendanceRewarded;

    public LoginResult(UserDTO user, boolean attendanceRewarded) {
        this.user = user;
        this.attendanceRewarded = attendanceRewarded;
    }

    public UserDTO getUser() {
        return user;
    }

    public boolean isAttendanceRewarded() {
        return attendanceRewarded;
    }
}