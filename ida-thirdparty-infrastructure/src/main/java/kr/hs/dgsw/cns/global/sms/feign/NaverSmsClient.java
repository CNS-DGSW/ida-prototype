package kr.hs.dgsw.cns.global.sms.feign;

import kr.hs.dgsw.cns.global.sms.feign.dto.request.NaverSmsRequest;
import kr.hs.dgsw.cns.global.sms.feign.dto.response.NaverSmsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "NaverSmsFeignClient",
        url = "https://sens.apigw.ntruss.com/sms/v2/services/"
)
public interface NaverSmsClient {

    @PostMapping("/{serviceId}/messages")
    NaverSmsResponse sendSms(
            @PathVariable("serviceId") String serviceId,
            @RequestHeader("x-ncp-apigw-timestamp") String timestamp,
            @RequestHeader("x-ncp-iam-access-key") String accessKey,
            @RequestHeader("x-ncp-apigw-signature-v2") String signature,
            @RequestBody NaverSmsRequest request
    );
}
