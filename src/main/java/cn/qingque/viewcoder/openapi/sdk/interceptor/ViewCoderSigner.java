package cn.qingque.viewcoder.openapi.sdk.interceptor;

import static cn.qingque.viewcoder.openapi.sdk.model.Consts.AUTHORIZATION;
import static cn.qingque.viewcoder.openapi.sdk.model.Consts.X_VC_SID;
import static cn.qingque.viewcoder.openapi.sdk.model.Consts.X_VC_TIMESTAMP;

import java.io.IOException;

import org.jetbrains.annotations.NotNull;

import cn.qingque.viewcoder.openapi.sdk.model.Credential;
import cn.qingque.viewcoder.openapi.sdk.utils.SigningTools;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

/**
 * @author 塔伊尔 <kurexi@kuaishou.com>
 * Created on 2023-12-04
 */
public class ViewCoderSigner implements Interceptor {

    private final static String[] canonicalHeaderKeys = {"content-type", "host"};

    private final Credential credential;

    private final static String signatureMethod = "V1";

    public ViewCoderSigner(Credential credential) {
        this.credential = credential;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();

        try {
            String method = request.method();
            String canonicalQueryString = request.url().query();
            String canonicalHeader = getCanonicalHeadersString(request);
            Long timestamp = System.currentTimeMillis();
            byte[] bodyContent = request.body() == null ? new byte[0] : getRequestBody(request);
            String bodyHash = SigningTools.sha256hexLowerCase(bodyContent);
            String canonicalRequest =
                    SigningTools.getCanonicalRequest(method, canonicalQueryString, canonicalHeader, timestamp,
                            bodyHash);

            String signString = SigningTools.sha256hexLowerCase(canonicalRequest);

            String sign = SigningTools.sign(signString, credential.getSecretKey().getBytes());

            String authorization =
                    SigningTools.getAuthorization(credential, canonicalHeaderKeys, sign, signatureMethod);

            builder.addHeader(X_VC_SID, credential.getKeyId());
            builder.addHeader(X_VC_TIMESTAMP, timestamp.toString());
            builder.addHeader(AUTHORIZATION, authorization);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return chain.proceed(builder.build());
    }

    private String getCanonicalHeadersString(Request request) {
        StringBuilder sb = new StringBuilder();
        for (String key : canonicalHeaderKeys) {
            String value = request.header(key);
            if (value == null) {
                continue;
            }
            sb.append(key).append(":").append(value).append("\n");
        }
        return sb.toString();
    }

    private byte[] getRequestBody(Request request) {
        if (request.body() == null) {
            return new byte[0];
        }

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            assert copy.body() != null;
            copy.body().writeTo(buffer);
            return buffer.readByteArray();
        } catch (IOException e) {
            return new byte[0];
        }
    }
}
