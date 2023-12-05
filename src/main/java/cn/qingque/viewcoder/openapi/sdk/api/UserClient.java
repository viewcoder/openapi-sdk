package cn.qingque.viewcoder.openapi.sdk.api;

import java.util.List;

import cn.qingque.viewcoder.openapi.sdk.model.Result;
import cn.qingque.viewcoder.openapi.sdk.model.user.BatchAddUserResult;
import cn.qingque.viewcoder.openapi.sdk.model.user.InviteUserParam;
import cn.qingque.viewcoder.openapi.sdk.model.user.InviteUserResult;
import cn.qingque.viewcoder.openapi.sdk.model.user.InviteUsersParam;
import cn.qingque.viewcoder.openapi.sdk.model.user.RemoveUserResult;
import cn.qingque.viewcoder.openapi.sdk.model.user.UserInfo;
import cn.qingque.viewcoder.openapi.sdk.model.user.UserMeta;
import feign.Headers;
import feign.RequestLine;

/**
 * @author yourname <shanrongyang@kuaishou.com>
 * Created on 2023-12-04
 */
@Headers({"Content-Type: application/json"})
public interface UserClient {
    /**
     * 添加团队成员
     */
    @RequestLine("POST /user/add")
    Result<UserInfo> addUser(UserMeta param);

    /**
     * 批量添加团队成员
     */
    @RequestLine("POST /user/add/batch")
    Result<BatchAddUserResult> batchAddUser(List<UserMeta> users);

    /**
     * 邀请团队成员
     */
    @RequestLine("POST /user/invite")
    Result<String> inviteUser(InviteUserParam param);

    /**
     * 批量邀请团队成员
     */
    @RequestLine("POST /user/invite/batch")
    Result<InviteUserResult> batchInviteUser(InviteUsersParam param);

    /**
     * 移除团队成员
     */
    @RequestLine("POST /user/remove")
    Result<Boolean> removeUser(String email);

    /**
     * 批量移除团队成员
     */
    @RequestLine("POST /user/remove/batch")
    Result<RemoveUserResult> batchRemoveUser(List<String> emails);
}
