package cn.qingque.viewcoder.openapi.sdk.model.interview;

import java.util.List;

import cn.qingque.viewcoder.openapi.sdk.model.ScoreCardDecisionEnum;
import lombok.Data;

/**
 * @author yourname <shanrongyang@kuaishou.com>
 * Created on 2023-12-04
 */
@Data
public class InterviewQueryParam {

    private List<InterviewStatusEnum> status;
    private List<ScoreCardDecisionEnum> decision;
    private List<InterviewScoreQueryEnum> score;

    private List<String> candidate;
    private List<String> interviewer;

    private String title;

    private int current;
    private int size;
}
