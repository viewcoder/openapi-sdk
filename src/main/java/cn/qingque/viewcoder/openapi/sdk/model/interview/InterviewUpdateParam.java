package cn.qingque.viewcoder.openapi.sdk.model.interview;

import java.util.List;

import lombok.Data;

/**
 * @author yourname <shanrongyang@kuaishou.com>
 * Created on 2023-12-04
 */
@Data
public class InterviewUpdateParam {
    private Long interviewId;
    private String title;
    private String candidateName;
    private String candidateMail;
    private List<String> interviewers;
    private Integer round;
    private Long scheduleTime;
    private Long relatedGroup;
    private String position;
    private String jobDetail;
    private String resumeId;
    private String creator;
}
