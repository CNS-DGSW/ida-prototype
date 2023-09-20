package kr.hs.dgsw.cns.aggregate.auth.usecase;

import kr.hs.dgsw.cns.aggregate.auth.dto.SendAuthMessageRequest;
import kr.hs.dgsw.cns.aggregate.auth.spi.service.SendAuthMessageService;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SendAuthMessageUseCase {

    private final SendAuthMessageService sendAuthMessageService;

    public void send(SendAuthMessageRequest request) {
        sendAuthMessageService.send(request);
    }
}