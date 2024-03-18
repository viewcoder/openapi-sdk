package cn.qingque.viewcoder.openapi.sdk.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import cn.qingque.viewcoder.openapi.sdk.ViewCoderClient;
import cn.qingque.viewcoder.openapi.sdk.model.Credential;
import cn.qingque.viewcoder.openapi.sdk.model.OpenApiResultCode;
import cn.qingque.viewcoder.openapi.sdk.model.Page;
import cn.qingque.viewcoder.openapi.sdk.model.Result;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewQueryParam;
import cn.qingque.viewcoder.openapi.sdk.model.interview.InterviewQueryResult;

/**
 * @author 塔伊尔 <kurexi@kuaishou.com>
 * Created on 2023-12-06
 */
public class ViewCoderClientTest {

    private static InterviewClient validInterviewClient;
    private static InterviewClient invalidInterviewClient;
    private static InterviewClient invalidSecretIdInterviewClient;
    private static InterviewClient secretNotFoundInterviewClient;


    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        Credential validCredential = new Credential();
        validCredential.setSecretKey(System.getProperty("secretKey"));
        validCredential.setKeyId(System.getProperty("keyId"));

        Credential invalidCredential = new Credential();
        invalidCredential.setSecretKey(System.getProperty("secretKey") + "invalid");
        invalidCredential.setKeyId(System.getProperty("keyId"));

        Credential invalidSecretIdCredential = new Credential();
        invalidSecretIdCredential.setSecretKey("InvalidSecretKey");
        invalidSecretIdCredential.setKeyId("InvalidTenantId");

        Credential secretNotFoundCredential = new Credential();
        secretNotFoundCredential.setSecretKey(System.getProperty("secretKey"));
        secretNotFoundCredential.setKeyId(System.getProperty("keyId") + "0");


        validInterviewClient = new ViewCoderClient(validCredential).getInterviewClient();
        invalidInterviewClient = new ViewCoderClient(invalidCredential).getInterviewClient();
        invalidSecretIdInterviewClient = new ViewCoderClient(invalidSecretIdCredential).getInterviewClient();
        secretNotFoundInterviewClient = new ViewCoderClient(secretNotFoundCredential).getInterviewClient();
    }

    @Test
    void validCredential() {
        Result<Page<InterviewQueryResult>> result =
                validInterviewClient.getInterviewList(new InterviewQueryParam());
        assertTrue(result.isSuccess());
    }

    @Test
    void invalidCredential() {
        Result<Page<InterviewQueryResult>> result =
                invalidInterviewClient.getInterviewList(new InterviewQueryParam());
        assertFalse(result.isSuccess());
        assertEquals(OpenApiResultCode.of(result.getCode()), OpenApiResultCode.SIGNATURE_FAILURE);
    }

    @Test
    void secretNotFoundTest() {
        Result<Page<InterviewQueryResult>> result =
                secretNotFoundInterviewClient.getInterviewList(new InterviewQueryParam());
        assertFalse(result.isSuccess());
        assertEquals(OpenApiResultCode.of(result.getCode()), OpenApiResultCode.SECRET_ID_NOT_FOUND);
    }

    @Test
    void invalidSecretId() {
        Result<Page<InterviewQueryResult>> result =
                invalidSecretIdInterviewClient.getInterviewList(new InterviewQueryParam());
        assertFalse(result.isSuccess());
        assertEquals(OpenApiResultCode.of(result.getCode()), OpenApiResultCode.INVALID_SECRET_ID);
    }
}
