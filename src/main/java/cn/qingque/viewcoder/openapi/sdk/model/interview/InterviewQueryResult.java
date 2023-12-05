package cn.qingque.viewcoder.openapi.sdk.model.interview;

import java.util.List;

import cn.qingque.viewcoder.openapi.sdk.model.ScoreCardDecisionEnum;
import lombok.Data;

/**
 * @author yourname <shanrongyang@kuaishou.com>
 * Created on 2023-12-04
 */
@Data
public class InterviewQueryResult {
    private Long interviewId;
    private String title;
    private String interviewerUrl;
    private String candidateUrl;
    private Integer round;
    private Long scheduleTime;
    private InterviewStatusEnum status;
    private Double score;
    private ScoreCardDecisionEnum decision;
    private List<String> interviewersEmail;
    private String candidateName;
    private Long tenantId;
}
