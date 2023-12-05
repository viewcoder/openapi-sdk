package cn.qingque.viewcoder.openapi.sdk.model.interview;

/**
 * @author lihai03
 * Created on 2022-12-05
 */
public enum InterviewScoreQueryEnum {
    STRONG_NO(1, "STRONG_NO"),
    NO(2, "NO"),
    YES(3, "YES"),
    STRONG_YES(4, "STRONG_YES"),
    ;
    private int code;
    private String desc;

    InterviewScoreQueryEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static InterviewScoreQueryEnum getByCode(Integer code) {
        InterviewScoreQueryEnum[] values = InterviewScoreQueryEnum.values();
        for (InterviewScoreQueryEnum value : values) {
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
