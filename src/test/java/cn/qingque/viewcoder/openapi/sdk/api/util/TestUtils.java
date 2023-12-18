package cn.qingque.viewcoder.openapi.sdk.api.util;

import lombok.experimental.UtilityClass;

/**
 * @author 塔伊尔 <kurexi@kuaishou.com>
 * Created on 2023-12-05
 */
@UtilityClass
public class TestUtils {
    public String newUserName() {
        return "user_" + randomString(8);
    }

    public String newUserMail() {
        return randomString(8) + "@test.test";
    }

    private String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int)(Math.random() * str.length());
            sb.append(str.charAt(index));
        }
        return sb.toString();
    }
}
