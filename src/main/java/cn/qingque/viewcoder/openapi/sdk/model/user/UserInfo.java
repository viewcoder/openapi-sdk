package cn.qingque.viewcoder.openapi.sdk.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yourname <shanrongyang@kuaishou.com>
 * Created on 2023-12-04
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UserInfo extends UserMeta {
    private String password;
}
