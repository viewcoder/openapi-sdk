package cn.qingque.viewcoder.openapi.sdk.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import cn.qingque.viewcoder.openapi.sdk.ViewCoderClient;
import cn.qingque.viewcoder.openapi.sdk.api.util.TestUtils;
import cn.qingque.viewcoder.openapi.sdk.model.Credential;
import cn.qingque.viewcoder.openapi.sdk.model.Result;
import cn.qingque.viewcoder.openapi.sdk.model.user.BatchAddUserResult;
import cn.qingque.viewcoder.openapi.sdk.model.user.InviteUserParam;
import cn.qingque.viewcoder.openapi.sdk.model.user.InviteUserResult;
import cn.qingque.viewcoder.openapi.sdk.model.user.InviteUserResult.InviteItem;
import cn.qingque.viewcoder.openapi.sdk.model.user.InviteUsersParam;
import cn.qingque.viewcoder.openapi.sdk.model.user.RemoveUserResult;
import cn.qingque.viewcoder.openapi.sdk.model.user.UserInfo;
import cn.qingque.viewcoder.openapi.sdk.model.user.UserMeta;

/**
 * @author 塔伊尔 <kurexi@kuaishou.com>
 * Created on 2023-12-05
 */
class UserClientTest {

    private static UserClient userClient;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        Properties properties = TestUtils.getProperties();

        Credential credential = new Credential();
        credential.setSecretKey(properties.getProperty("secretKey"));
        credential.setKeyId(properties.getProperty("keyId"));


        userClient = new ViewCoderClient(credential).getUserClient();
    }

    @Test
    void addUser() {
        Result<UserInfo> result = addNewUser();
        assertTrue(result.isSuccess(), result.getMessage());
    }

    @Test
    void inviteUser() {
        UserInfo user = addNewUser().getResult();

        InviteUserParam param = new InviteUserParam();
        param.setInvitee(user.getUserMail());
        param.setInviter(user.getUserMail());
        Result<String> result = userClient.inviteUser(param);
        assertFalse(result.isSuccess(), result.getMessage());

        param.setInvitee(TestUtils.newUserMail());
        result = userClient.inviteUser(param);
        assertTrue(result.isSuccess(), result.getMessage());
    }

    @Test
    void removeUser() {
        UserInfo user = addNewUser().getResult();
        Result<Boolean> result = userClient.removeUser(user.getUserMail());
        System.out.println(result);
        assertTrue(result.isSuccess(), result.getMessage());
    }

    @Test
    void batchAddUser() {
        List<UserMeta> users = buildUserInfos();
        Result<BatchAddUserResult> result = userClient.batchAddUser(users);
        System.out.println(result);
        assertTrue(result.isSuccess(), result.getMessage());
        assertTrue(result.getResult().getFailList().isEmpty());
        for (UserInfo user : result.getResult().getSuccessList()) {
            assertNotNull(user.getUserMail());
            assertNotNull(user.getUserName());
        }
    }

    @Test
    void batchInviteUser() {
        InviteUsersParam userInfos = buildUsersParam();
        Result<InviteUserResult> result = userClient.batchInviteUser(userInfos);
        System.out.println(result);
        assertTrue(result.isSuccess(), result.getMessage());
    }

    @Test
    void batchRemoveUser() {
        InviteUsersParam users = buildUsersParam();
        Result<InviteUserResult> inviteResult = userClient.batchInviteUser(users);
        List<String> emails = inviteResult.getResult().getSuccessList().stream().map(InviteItem::getUserMail)
                .collect(Collectors.toList());

        Result<RemoveUserResult> result = userClient.batchRemoveUser(emails);
        System.out.println(result);
        assertTrue(result.isSuccess(), result.getMessage());
    }

    private Result<UserInfo> addNewUser() {
        UserMeta user = new UserMeta();
        user.setUserName(TestUtils.newUserName());
        user.setUserMail(TestUtils.newUserMail());
        return userClient.addUser(user);
    }

    public List<UserMeta> buildUserInfos() {
        List<UserMeta> userMetas = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            UserMeta user = new UserMeta();
            user.setUserName(TestUtils.newUserName());
            user.setUserMail(TestUtils.newUserMail());
            userMetas.add(user);
        }

        return userMetas;
    }

    public InviteUsersParam buildUsersParam() {
        InviteUsersParam usersParam = new InviteUsersParam();
        List<String> users = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            users.add(TestUtils.newUserMail());
        }

        usersParam.setInvitees(users);
        usersParam.setInviter(TestUtils.newUserMail());
        return usersParam;
    }
}