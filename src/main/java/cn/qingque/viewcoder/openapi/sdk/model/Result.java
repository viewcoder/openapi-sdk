package cn.qingque.viewcoder.openapi.sdk.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result<T> {

    /**
     * 返回码
     */
    private int code;

    /**
     * 本地化信息
     */
    private String message;

    /**
     * 实际数据
     */
    private T result;

    private String traceId;

    public boolean isSuccess() {
        return code == 0;
    }
}