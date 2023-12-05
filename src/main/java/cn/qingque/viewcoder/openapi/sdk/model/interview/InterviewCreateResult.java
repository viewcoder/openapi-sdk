package cn.qingque.viewcoder.openapi.sdk.model.interview;

import java.util.List;

import lombok.Data;

/**
 * @author yourname <shanrongyang@kuaishou.com>
 * Created on 2023-12-04
 */
@Data
public class InterviewCreateResult {
    private String interviewId;
    private String creatorName;
    private String creatorEmail;

    private String interviewerUrl;
    private String candidateUrl;
    private String relatedGroup;

    private String resumeUrl;

    private String candidateName;
    private String candidateEmail;
    private String interviewTitle;
    private Long tenantId;
    private List<String> interviewers;

}
