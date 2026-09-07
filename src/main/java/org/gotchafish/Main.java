package org.gotchafish;

import org.gotchafish.common.JDBCUtil;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            JDBCUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
