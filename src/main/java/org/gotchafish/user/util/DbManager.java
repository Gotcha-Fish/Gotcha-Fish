package org.gotchafish.user.util;

import java.sql.*;

public class DbManager {
    // 로드
    static {
        try {
            Class.forName(DbProperties.DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 연결
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DbProperties.URL, DbProperties.USERNAME, DbProperties.PASSWORD);
    }
}