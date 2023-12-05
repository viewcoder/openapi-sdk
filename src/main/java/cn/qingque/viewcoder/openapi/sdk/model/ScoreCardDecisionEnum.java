package cn.qingque.viewcoder.openapi.sdk.model;

/**
 * @author lihai03
 * Created on 2022-12-05
 */
public enum ScoreCardDecisionEnum {
    STRONG_NO(0, "STRONG_NO"),
    NO(1, "NO"),
    YES(2, "YES"),
    STRONG_YES(3, "STRONG_YES"),
    NONE(4, "NONE"),
    PENDING(5, "PENDING");
    private int code;
    private String desc;

    ScoreCardDecisionEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ScoreCardDecisionEnum getByCode(Integer code) {
        ScoreCardDecisionEnum[] values = ScoreCardDecisionEnum.values();
        for (ScoreCardDecisionEnum value : values) {
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
