package kr.hs.dgsw.cns.aggregate.auth.service;

import kr.hs.dgsw.cns.aggregate.auth.dao.AuthCodeCommandRepository;
import kr.hs.dgsw.cns.aggregate.auth.domain.AuthCode;
import kr.hs.dgsw.cns.aggregate.auth.dto.SendAuthMessageRequest;
import kr.hs.dgsw.cns.aggregate.auth.spi.service.SendAuthMessageService;
import kr.hs.dgsw.cns.domain.value.PhoneNumber;
import kr.hs.dgsw.cns.global.sms.SendAuthMessageSmsService;
import kr.hs.dgsw.cns.global.sms.dto.request.SendMessageRequest;
import kr.hs.dgsw.cns.global.util.RandomNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SendAuthMessageServiceImpl implements SendAuthMessageService {

    private final SendAuthMessageSmsService sendAuthMessageSmsService;
    private final AuthCodeCommandRepository authCodeCommandRepository;


    @Override
    public void send(SendAuthMessageRequest request) {
        final String code = RandomNumberGenerator.generateRandomNumberWithString(AuthCode.SIZE);
        authCodeCommandRepository.save(
                AuthCode.builder()
                        .phoneNumber(PhoneNumber.of(request.getContact()))
                        .code(code)
                        .build()
        );
        sendAuthMessageSmsService.send(
                SendMessageRequest.of(request.getContact()),
                code
        );
    }
}
