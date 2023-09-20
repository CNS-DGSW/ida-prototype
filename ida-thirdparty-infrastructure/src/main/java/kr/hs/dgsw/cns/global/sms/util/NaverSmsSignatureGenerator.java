package kr.hs.dgsw.cns.global.sms.util;

import lombok.experimental.UtilityClass;
import org.bouncycastle.jcajce.BCFKSLoadStoreParameter;
import org.springframework.http.HttpMethod;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@UtilityClass
public class NaverSmsSignatureGenerator {

    public String makeSignature(
            String serviceId,
            String accessKey,
            String secretKey,
            String currentTime
    ) {
        String space = " ";
        String newLine = "\n";
        String method = HttpMethod.POST.name();
        String url = "/sms/v2/services/" + serviceId + "/messages";

        String message =
                method +
                        space +
                        url +
                        newLine +
                        currentTime +
                        newLine +
                        accessKey;
        try{
            SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), BCFKSLoadStoreParameter.MacAlgorithm.HmacSHA3_512.name());
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

}
