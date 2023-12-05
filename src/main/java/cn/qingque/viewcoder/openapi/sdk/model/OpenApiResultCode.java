package cn.qingque.viewcoder.openapi.sdk.model;

/**
 * @author 塔伊尔 <kurexi@kuaishou.com>
 * Created on 2023-12-06
 */
public enum OpenApiResultCode {

    INTERNAL_ERROR(999999, "InternalError"),
    SIGNATURE_EXPIRED(110001, "SignatureExpired"),
    SECRET_ID_NOT_FOUND(110002, "SecretIdNotFound"),
    SIGNATURE_FAILURE(110003, "SignatureFailure"),
    INVALID_SECRET_ID(110004, "InvalidSecretId"),
    ;

    private final int value;
    private final String desc;

    OpenApiResultCode(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    static public OpenApiResultCode of(int i) {
        for (OpenApiResultCode resultCode : OpenApiResultCode.values()) {
            if (resultCode.getValue() == i) {
                return resultCode;
            }
        }

        throw new IllegalArgumentException("No matching constant for [" + i + "]");
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
