package cn.qingque.viewcoder.openapi.sdk.model.user;

import java.util.List;

import lombok.Data;

/**
 * @author yourname <shanrongyang@kuaishou.com>
 * Created on 2023-12-04
 */
@Data
public class RemoveUserResult {
    private List<FailItem> failList;
    private List<String> successList;

    @Data
    public static class FailItem {
        private String email;
        private String reason;
    }
}
