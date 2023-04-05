package kr.hs.dgsw.cns.aggregate.member.webadapter;

import kr.hs.dgsw.cns.aggregate.member.dto.MemberRequest;
import kr.hs.dgsw.cns.aggregate.member.ro.TokenRO;
import kr.hs.dgsw.cns.aggregate.member.usecase.MemberLoginUseCase;
import kr.hs.dgsw.cns.aggregate.member.usecase.MemberRegisterUseCase;
import kr.hs.dgsw.cns.global.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberWebAdapter {

    private final MemberRegisterUseCase registerUseCase;
    private final MemberLoginUseCase loginUseCase;
    private final JwtProvider jwtProvider;

    public void register(MemberRequest memberRequest) {
        registerUseCase.register(memberRequest);
    }

    public TokenRO login(MemberRequest memberRequest) {
        final long id = loginUseCase.login(memberRequest);
        return new TokenRO(
                jwtProvider.generateAccessToken(id),
                jwtProvider.generateRefreshToken(id),
                jwtProvider.getExpirationTime()
        );
    }

}
