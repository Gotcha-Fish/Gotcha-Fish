package org.gotchafish.common;

import java.sql.*;

public class JDBCUtil {

    // DB DRIVER 로드
    static {
        try {
            Class.forName(JDBCManager.DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // DB 연결
    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                JDBCManager.URL,
                JDBCManager.USER_ID,
                JDBCManager.USER_PW
        );
        //System.out.println("DB 연결 성공");
        return con;
    }
}