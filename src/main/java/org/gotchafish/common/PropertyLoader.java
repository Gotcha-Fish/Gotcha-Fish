package org.gotchafish.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    private static final Properties prop = new Properties();

    static {
        // TODO: try-with-resources로 InputStream 열고 prop.load(is) 호출
        // 예외 발생 시 RuntimeException으로 감싸서 던지기
        try (InputStream is = PropertyLoader.class.getResourceAsStream("/db.properties")) {
            prop.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }

}
