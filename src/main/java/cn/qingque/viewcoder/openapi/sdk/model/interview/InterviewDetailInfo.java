package cn.qingque.viewcoder.openapi.sdk.model.interview;

import java.util.List;

import cn.qingque.viewcoder.openapi.sdk.model.CategoryAndScore;
import lombok.Data;

/**
 * @author yourname <shanrongyang@kuaishou.com>
 * Created on 2023-12-04
 */
@Data
public class InterviewDetailInfo {
    private Long interviewId;

    private String title;

    private String interviewerUrl;

    private String candidateUrl;

    private Integer round;

    private Long scheduleTime;

    private InterviewStatusEnum status;

    private List<CategoryAndScore> score;

    private String decision;

    private List<String> interviewer;

    private String candidate;

    private String creator;

    private String resumeUrl;

    private Long interviewDuration;

    private String position;

    private String jobDetail;
}
