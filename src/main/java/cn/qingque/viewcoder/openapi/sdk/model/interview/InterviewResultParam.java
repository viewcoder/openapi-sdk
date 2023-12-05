package cn.qingque.viewcoder.openapi.sdk.model.interview;

import java.util.List;

import cn.qingque.viewcoder.openapi.sdk.model.ScoreCardDecisionEnum;
import lombok.Data;

/**
 * @author yourname <shanrongyang@kuaishou.com>
 * Created on 2023-12-04
 */
@Data
public class InterviewResultParam {


    private Long interviewId;

    private ScoreCardDecisionEnum decision;

    private List<InterviewScorecardReqDTO> score;

    private String overview;

    private String submitter;

}
