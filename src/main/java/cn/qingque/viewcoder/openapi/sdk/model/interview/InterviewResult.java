package cn.qingque.viewcoder.openapi.sdk.model.interview;

import java.util.List;

import lombok.Data;

/**
 * @author yourname <shanrongyang@kuaishou.com>
 * Created on 2023-12-04
 */
@Data
public class InterviewResult {
    private Long interviewId;
    private String decision;
    private List<InterviewScorecardResult> score;
    private String overview;
    private String submitter;

}
