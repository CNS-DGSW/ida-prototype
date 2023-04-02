package kr.hs.dgsw.cns.aggregate.member.spi.service;

import kr.hs.dgsw.cns.aggregate.member.dto.MemberRequest;

public interface MemberRegisterService {

    void register(MemberRequest registerRequest);

}
