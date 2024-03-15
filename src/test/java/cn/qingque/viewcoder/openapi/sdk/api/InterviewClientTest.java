package cn.qingque.viewcoder.openapi.sdk.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import cn.qingque.viewcoder.openapi.sdk.ViewCoderClient;
import cn.qingque.viewcoder.openapi.sdk.api.util.TestUtils;
import cn.qingque.viewcoder.openapi.sdk.model.Credential;
import cn.qingque.viewcoder.openapi.sdk.model.Page;
import cn.qingque.viewcoder.openapi.sdk.model.Result;
import cn.qingque.viewcoder.openapi.sdk.model.ScoreCardDecisionEnum;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewCreateParam;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewCreateResult;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewDetailInfo;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewQueryParam;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewQueryResult;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewResult;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewResultParam;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewScorecardReqDTO;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewTemplate;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewUpdateParam;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewUpdateResult;
import cn.qingque.viewcoder.openapi.sdk.model.interview.UploadResumeResult;

/**
 * @author 塔伊尔 <kurexi@kuaishou.com>
 * Created on 2023-12-05
 */
class InterviewClientTest {

    private static InterviewClient interviewClient;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        Credential credential = new Credential();
        credential.setSecretKey(System.getProperty("secretKey"));
        credential.setKeyId(System.getProperty("keyId"));


        interviewClient = new ViewCoderClient(credential).getInterviewClient();
    }

    @Test
    void uploadResumeTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        String fileName = "resume.pdf";
        File file = new File(classLoader.getResource(fileName).getFile());

        Result<UploadResumeResult> result = interviewClient.uploadResume(file);
        assertTrue(result.isSuccess(), result.getMessage());
        assertNotNull(result.getResult().getResumeId());
    }

    @Test
    void createInterview() {
        InterviewCreateParam param = buildInterviewCreateParam();
        Result<InterviewCreateResult> result = interviewClient.createInterview(param);
        assertTrue(result.isSuccess(), result.getMessage());
        assertNotNull(result.getResult());
        assertNotNull(result.getResult().getInterviewId());
    }

    @Test
    void updateInterview() {
        InterviewQueryResult interview = firstInterviewFromList();
        Long interviewId = interview.getInterviewId();

        InterviewUpdateParam param = buildUpdateParam(interviewId);
        Result<InterviewUpdateResult> result = interviewClient.updateInterview(param);
        assertTrue(result.isSuccess(), result.getMessage());
        assertEquals(result.getResult().getInterviewId(), interview.getInterviewId());
    }

    @Test
    void getInterviewList() {
        Result<Page<InterviewQueryResult>> result = interviewClient.getInterviewList(new InterviewQueryParam());
        assertTrue(result.isSuccess(), result.getMessage());

        Page<InterviewQueryResult> res = result.getResult();

        assertTrue(res.getRecords().size() > 0);

        // all are the same tenantId
        Long tenantId = null;
        List<InterviewQueryResult> queryResults = res.getRecords();
        for (InterviewQueryResult queryResult : queryResults) {

            if (tenantId == null) {
                tenantId = queryResult.getTenantId();
            }

            assertNotNull(queryResult.getTenantId());
            assertEquals(queryResult.getTenantId(), tenantId);
        }
    }

    @Test
    void getInterviewDetailInfo() {
        InterviewQueryResult interview = firstInterviewFromList();
        Result<InterviewDetailInfo> result = interviewClient.getInterviewDetailInfo(
                interview.getInterviewId());
        assertTrue(result.isSuccess(), result.getMessage());
        assertEquals(result.getResult().getInterviewId(), interview.getInterviewId());
    }

    @Test
    void getInterviewResult() {
        InterviewQueryResult interview = firstInterviewFromList();
        Result<InterviewResult> result = interviewClient.getInterviewResult(interview.getInterviewId());
        assertTrue(result.isSuccess(), result.getMessage());
        assertEquals(result.getResult().getInterviewId(), interview.getInterviewId());
    }

    @Test
    void submitInterviewResult() {
        InterviewQueryResult interview = firstInterviewFromList();
        InterviewResultParam resultParam = buildResultParam(interview.getInterviewId());
        Result<Boolean> res = interviewClient.submitInterviewResult(resultParam);
        System.out.println(res.getResult());
    }

    @Test
    void getInterviewTemplate() {
        InterviewQueryResult interview = firstInterviewFromList();
        Result<List<InterviewTemplate>> result = interviewClient.getInterviewTemplate(interview.getInterviewId());
        assertTrue(result.isSuccess(), result.getMessage());
        assertNotNull(result.getResult());
    }

    @Test
    void getRecordTemporaryUrl() {
        InterviewQueryResult interview = firstInterviewFromList();
        Result<String> result = interviewClient.getRecordTemporaryUrl(interview.getInterviewId());
        assertTrue(result.isSuccess(), result.getMessage());
        assertNotNull(result.getResult());
    }

    private InterviewQueryResult firstInterviewFromList() {
        Result<Page<InterviewQueryResult>> listResult = interviewClient.getInterviewList(new InterviewQueryParam());
        List<InterviewQueryResult> records = listResult.getResult().getRecords();
        return records.get(0);
    }

    private InterviewCreateParam buildInterviewCreateParam() {
        InterviewCreateParam param = new InterviewCreateParam();
        List<String> interviewers = new ArrayList<>();
        interviewers.add(TestUtils.newUserMail());
        interviewers.add(TestUtils.newUserMail());
        param.setTitle("title");
        param.setCandidateName(TestUtils.newUserName());
        param.setCandidateMail(TestUtils.newUserMail());
        param.setInterviewers(interviewers);
        param.setRound(1);
        param.setResumeId("428933504190763057");
        param.setScheduleTime(System.currentTimeMillis() + 1000 * 60 * 60 * 24);
        param.setRelatedGroup(1L);
        param.setPosition("Position");
        param.setJobDetail("JobDetail");
        return param;
    }

    public InterviewUpdateParam buildUpdateParam(Long interviewId) {
        InterviewUpdateParam updateParam = new InterviewUpdateParam();

        List<String> interviewers = new ArrayList<>();
        interviewers.add(TestUtils.newUserMail());
        interviewers.add(TestUtils.newUserMail());

        updateParam.setTitle("title_update");
        updateParam.setInterviewers(interviewers);
        updateParam.setInterviewId(interviewId);
        updateParam.setRelatedGroup(2L);
        updateParam.setRound(1);
        updateParam.setCandidateName(TestUtils.newUserName());
        updateParam.setCandidateMail(TestUtils.newUserMail());
        return updateParam;
    }


    public InterviewResultParam buildResultParam(Long interviewId) {
        InterviewResultParam param = new InterviewResultParam();
        param.setInterviewId(interviewId);
        param.setDecision(ScoreCardDecisionEnum.PENDING);
        param.setOverview("很好");
        List<InterviewScorecardReqDTO> dto = new ArrayList<>();
        InterviewScorecardReqDTO reqDTO = new InterviewScorecardReqDTO();
        reqDTO.setItemId(428996026305912884L);
        reqDTO.setItemValue("4");
        dto.add(reqDTO);
        param.setScore(dto);

        return param;
    }

}