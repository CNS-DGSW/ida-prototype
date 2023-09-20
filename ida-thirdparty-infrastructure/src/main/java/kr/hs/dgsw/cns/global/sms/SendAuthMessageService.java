package kr.hs.dgsw.cns.global.sms;

import kr.hs.dgsw.cns.global.sms.dto.request.SendMessageRequest;
import kr.hs.dgsw.cns.global.sms.feign.NaverSmsClient;
import kr.hs.dgsw.cns.global.sms.feign.dto.request.NaverSmsRequest;
import kr.hs.dgsw.cns.global.sms.feign.property.NaverSmsProperties;
import kr.hs.dgsw.cns.global.sms.util.NaverSmsSignatureGenerator;
import kr.hs.dgsw.cns.global.util.RandomNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SendAuthMessageService {
    private final NaverSmsClient naverSmsClient;
    private final NaverSmsProperties naverSmsProperties;

    public void send(
            SendMessageRequest request
    ) {
        List<NaverSmsRequest.Message> messages = new ArrayList<>();
        messages.add(
                NaverSmsRequest.Message.builder()
                        .to(request.getContact())
                        .content(authMessage(RandomNumberGenerator.generateRandomNumberWithString(6)))
                        .build()
        );

        NaverSmsRequest naverSmsRequest = NaverSmsRequest.builder()
                .type("SMS")
                .from(naverSmsProperties.getFrom())
                .content("[대구소프트웨어마이스터고등학교]")
                .messages(messages)
                .build();

        String timestamp = Long.toString(System.currentTimeMillis());

        naverSmsClient.sendSms(
                naverSmsProperties.getServiceId(),
                timestamp,
                naverSmsProperties.getAccessKey(),
                NaverSmsSignatureGenerator.makeSignature(
                        naverSmsProperties.getServiceId(),
                        naverSmsProperties.getAccessKey(),
                        naverSmsProperties.getSecretKey(),
                        timestamp
                ),
                naverSmsRequest
        );
    }

    private static String authMessage (String code) {
        return String.format("[대구소프트웨어마이스터고등학교] \n 인증번호 [%s] 본인확인 인증번호입니다.", code);
    }
}
