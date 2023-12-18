package cn.qingque.viewcoder.openapi.sdk.api.util;

import java.util.Properties;

import lombok.experimental.UtilityClass;

/**
 * @author 塔伊尔 <kurexi@kuaishou.com>
 * Created on 2023-12-05
 */
@UtilityClass
public class TestUtils {
    public String newUserName() {
        return "user_" + System.currentTimeMillis();
    }

    public String newUserMail() {
        return System.currentTimeMillis() + "@test.test";
    }
}
