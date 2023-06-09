package kr.hs.dgsw.cns.aggregate.member.usecase;

import kr.hs.dgsw.cns.aggregate.member.dto.MemberRequest;
import kr.hs.dgsw.cns.aggregate.member.spi.service.MemberRegisterService;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class MemberRegisterUseCase {

    private final MemberRegisterService registerService;

    public void register(MemberRequest registerRequest) {
        registerService.register(registerRequest);
    }

}
