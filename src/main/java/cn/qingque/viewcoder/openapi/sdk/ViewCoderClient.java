package cn.qingque.viewcoder.openapi.sdk;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import cn.qingque.viewcoder.openapi.sdk.api.InterviewClient;
import cn.qingque.viewcoder.openapi.sdk.api.UserClient;
import cn.qingque.viewcoder.openapi.sdk.interceptor.ViewCoderSigner;
import cn.qingque.viewcoder.openapi.sdk.model.Credential;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import okhttp3.OkHttpClient.Builder;

/**
 * @author 塔伊尔 <kurexi@kuaishou.com>
 * Created on 2023-12-05
 */
public class ViewCoderClient {
    private static final String ENDPOINT = "https://viewcoder.qingque.cn/backend/openapi";
    private final OkHttpClient httpClient;
    private final Encoder encoder;
    private final Decoder decoder;


    public ViewCoderClient(Credential credential) {
        httpClient = new OkHttpClient(new Builder()
                .addNetworkInterceptor(new ViewCoderSigner(credential))
                .build());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        encoder = new JacksonEncoder(objectMapper);
        decoder = new JacksonDecoder(objectMapper);
    }

    public InterviewClient getInterviewDevClient(String devEndPoint) {
        return Feign.builder()
                .client(httpClient)
                .encoder(new FormEncoder(encoder))
                .decoder(decoder)
                .target(InterviewClient.class, devEndPoint);
    }

    public InterviewClient getInterviewClient() {
        return Feign.builder()
                .client(httpClient)
                .encoder(new FormEncoder(encoder))
                .decoder(decoder)
                .target(InterviewClient.class, ENDPOINT);
    }

    public UserClient getUserClient() {
        return Feign.builder()
                .client(httpClient)
                .encoder(encoder)
                .decoder(decoder)
                .target(UserClient.class, ENDPOINT);
    }
}
