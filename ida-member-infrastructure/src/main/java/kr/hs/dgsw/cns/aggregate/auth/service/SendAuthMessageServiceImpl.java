package kr.hs.dgsw.cns.aggregate.auth.service;

import kr.hs.dgsw.cns.aggregate.auth.dto.SendAuthMessageRequest;
import kr.hs.dgsw.cns.aggregate.auth.spi.service.SendAuthMessageService;
import kr.hs.dgsw.cns.global.sms.dto.request.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SendAuthMessageServiceImpl implements SendAuthMessageService {

    private final kr.hs.dgsw.cns.global.sms.SendAuthMessageService sendAuthMessageService;

    @Override
    public void send(SendAuthMessageRequest request) {
        sendAuthMessageService.send(
                SendMessageRequest.of(request.getContact())
        );
    }
}
