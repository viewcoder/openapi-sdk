package cn.qingque.viewcoder.openapi.sdk.model;

import lombok.Data;

/**
 * @author 塔伊尔 <kurexi@kuaishou.com>
 * Created on 2023-11-30
 */
@Data
public class Credential {
    private Long tenantId;
    private String secretKey;
}
