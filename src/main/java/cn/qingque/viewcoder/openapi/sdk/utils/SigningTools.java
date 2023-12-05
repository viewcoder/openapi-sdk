package cn.qingque.viewcoder.openapi.sdk.utils;

import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import cn.qingque.viewcoder.openapi.sdk.model.Credential;
import lombok.experimental.UtilityClass;

/**
 * @author 塔伊尔 <kurexi@kuaishou.com>
 * Created on 2023-09-18
 */
@UtilityClass
public class SigningTools {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public String getCanonicalRequest(String httpMethod,
            String canonicalQueryString,
            String canonicalHeader,
            Long requestTimestamp,
            String hashedPayload) {

        canonicalQueryString = normalize(canonicalQueryString);

        return String.format("%s\n%s\n%s\n%s\n%s", httpMethod, canonicalQueryString, canonicalHeader,
                requestTimestamp, hashedPayload);
    }

    private String normalize(String stringValue) {
        return stringValue == null ? "" : stringValue;
    }

    public String getAuthorization(Credential credential, String[] canonicalHeaderKeys, String sign, String method) {
        return String.format("Credential=%s, SignedHeaders=%s, Signature=%s, SignMethod=%s",
                credential.getKeyId(), String.join(";", canonicalHeaderKeys), sign, method);
    }

    public String sha256hexLowerCase(String stringToSign) throws Exception {
        return sha256hexLowerCase(stringToSign.getBytes());
    }

    public String sha256hexLowerCase(byte[] bytes) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] digest = messageDigest.digest(bytes);
        return bytesToHex(digest).toLowerCase();
    }

    public String sign(String stringToSign, byte[] secretKey) throws Exception {
        byte[] signBytes = hmac256(stringToSign, secretKey);
        return bytesToHex(signBytes).toLowerCase();
    }

    private byte[] hmac256(String stringToSign, byte[] secretKey) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, mac.getAlgorithm());
        mac.init(secretKeySpec);
        return mac.doFinal(stringToSign.getBytes());
    }

    private String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
