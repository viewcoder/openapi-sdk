package cn.qingque.viewcoder.openapi.sdk.model.user;

import java.util.List;

import lombok.Data;

/**
 * @author yourname <shanrongyang@kuaishou.com>
 * Created on 2023-12-04
 */
@Data
public class InviteUserResult {
    private List<String> failList;
    private List<InviteItem> successList;

    @Data
    public static class InviteItem {
        private String userMail;
        private String inviteLink;
    }
}
