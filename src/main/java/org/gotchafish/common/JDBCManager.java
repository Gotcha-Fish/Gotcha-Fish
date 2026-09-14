package org.gotchafish.common;

public interface JDBCManager {
    public static final String DRIVER_NAME="com.mysql.cj.jdbc.Driver";
    String URL = PropertyLoader.get("DB.URL");
    String USER_ID = PropertyLoader.get("DB.USER_ID");
    String USER_PW = PropertyLoader.get("DB.USER_PW");
}
