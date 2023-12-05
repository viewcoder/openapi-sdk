package cn.qingque.viewcoder.openapi.sdk.model.user;

import java.util.List;

import lombok.Data;

/**
 * @author yourname <shanrongyang@kuaishou.com>
 * Created on 2023-12-04
 */
@Data
public class InviteUsersParam {
    private List<String> invitees;
    private String inviter;
}
