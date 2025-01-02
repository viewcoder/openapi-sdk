package cn.qingque.viewcoder.openapi.sdk.model.interview;

import lombok.Data;

/**
 * @author haoshuai03 <haoshaui03@kuaishou.com>
 * Created on 2024-09-26
 */
@Data
public class InterviewMediaOpenVO {
    private Long interviewId;
    private String mediaType;
    private String fileUrl;
    private Long startTime;
    private Long endTime;
    private String rtcType;
}