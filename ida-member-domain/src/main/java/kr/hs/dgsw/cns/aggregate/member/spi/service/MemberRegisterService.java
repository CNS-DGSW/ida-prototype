package kr.hs.dgsw.cns.aggregate.member.spi.service;

import kr.hs.dgsw.cns.aggregate.member.dto.MemberRegisterRequest;

public interface MemberRegisterService {

    void register(MemberRegisterRequest registerRequest);

}
