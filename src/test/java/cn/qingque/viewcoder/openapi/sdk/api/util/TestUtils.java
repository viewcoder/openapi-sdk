package cn.qingque.viewcoder.openapi.sdk.api.util;

import java.util.Properties;

import lombok.experimental.UtilityClass;

/**
 * @author 塔伊尔 <kurexi@kuaishou.com>
 * Created on 2023-12-05
 */
@UtilityClass
public class TestUtils {
    // load properties from src/test/resources/test.properties
    public Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(TestUtils.class.getClassLoader().getResourceAsStream("test.properties"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return properties;
    }

    public String newUserName() {
        return "user_" + System.currentTimeMillis();
    }

    public String newUserMail() {
        return System.currentTimeMillis() + "@test.test";
    }
}
