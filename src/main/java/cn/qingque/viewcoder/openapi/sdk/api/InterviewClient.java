package cn.qingque.viewcoder.openapi.sdk.api;


import java.io.File;
import java.util.List;

import cn.qingque.viewcoder.openapi.sdk.model.Page;
import cn.qingque.viewcoder.openapi.sdk.model.Result;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewCreateParam;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewCreateResult;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewDetailInfo;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewQueryParam;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewQueryResult;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewResult;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewResultParam;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewTemplate;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewUpdateParam;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewUpdateResult;
import cn.qingque.viewcoder.openapi.sdk.model.interview.UploadResumeResult;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers({"Content-Type: application/json"})
public interface InterviewClient {
    /**
     * 上传简历
     */
    @RequestLine("POST /interview/resume/upload")
    @Headers("Content-Type: multipart/form-data")
    Result<UploadResumeResult> uploadResume(@Param("file") File file);

    /**
     * 创建面试
     */
    @RequestLine("POST /interview/create")
    Result<InterviewCreateResult> createInterview(InterviewCreateParam param);

    /**
     * 编辑面试
     */
    @RequestLine("POST /interview/update")
    Result<InterviewUpdateResult> updateInterview(InterviewUpdateParam param);

    /**
     * 获取面试列表
     */
    @RequestLine("POST /interview/list")
    Result<Page<InterviewQueryResult>> getInterviewList(InterviewQueryParam param);

    /**
     * 获取面试详情
     */
    @RequestLine("GET /interview/detail?interviewId={interviewId}")
    Result<InterviewDetailInfo> getInterviewDetailInfo(@Param("interviewId") Long interviewId);

    /**
     * 获取面试结果
     */
    @RequestLine("GET /interview/result?interviewId={interviewId}")
    Result<InterviewResult> getInterviewResult(@Param("interviewId") Long interviewId);

    /**
     * 提交面试结果
     */
    @RequestLine("POST /interview/result/submit")
    Result<Boolean> submitInterviewResult(InterviewResultParam param);

    /**
     * 获取面试结果
     */
    @RequestLine("GET /interview/template?interviewId={interviewId}")
    Result<List<InterviewTemplate>> getInterviewTemplate(@Param("interviewId") Long interviewId);
}
