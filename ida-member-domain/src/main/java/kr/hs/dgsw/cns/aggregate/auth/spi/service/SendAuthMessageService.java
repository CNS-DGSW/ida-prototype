package kr.hs.dgsw.cns.aggregate.auth.spi.service;

import kr.hs.dgsw.cns.aggregate.auth.dto.SendAuthMessageRequest;

public interface SendAuthMessageService {

    void send(SendAuthMessageRequest request);

}
