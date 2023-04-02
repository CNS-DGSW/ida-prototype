package kr.hs.dgsw.cns.aggregate.member.usecase;

import kr.hs.dgsw.cns.aggregate.member.domain.Member;
import kr.hs.dgsw.cns.aggregate.member.domain.value.Password;
import kr.hs.dgsw.cns.aggregate.member.dto.MemberRequest;
import kr.hs.dgsw.cns.aggregate.member.spi.query.QueryMemberSpi;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class MemberLoginUseCase {

    private final QueryMemberSpi queryMemberSpi;

    public long login(final MemberRequest memberRequest) {
        Member member = queryMemberSpi.findByEmail(memberRequest.getEmail())
                .orElseThrow();
        // TODO: must check password
        if (!member.getPassword().equals(Password.of(memberRequest.getPassword()))) {
            throw new IllegalStateException();
        }

        return member.getId().getId();
    }
}
