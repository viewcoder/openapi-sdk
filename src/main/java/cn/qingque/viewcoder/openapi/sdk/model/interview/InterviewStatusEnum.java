package cn.qingque.viewcoder.openapi.sdk.model.interview;

/**
 * @author lwt
 */

public enum InterviewStatusEnum {
    RUNNING(0, "已开始"),
    NOT_STARTED(1, "未开始"),
    CLOSED(2, "已结束"),
    CANCELED(3, "已取消"),
    ;
    private int code;
    private String desc;

    InterviewStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static InterviewStatusEnum getByCode(Integer code) {
        InterviewStatusEnum[] values = InterviewStatusEnum.values();
        for (InterviewStatusEnum value : values) {
            if (value.code == code) {
                return value;
            }
        }
        throw new RuntimeException("Unknown parameter: " + code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
