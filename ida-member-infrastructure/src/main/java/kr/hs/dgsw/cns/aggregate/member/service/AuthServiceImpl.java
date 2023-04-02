package kr.hs.dgsw.cns.aggregate.member.service;

import kr.hs.dgsw.cns.aggregate.member.spi.query.QueryMemberSpi;
import kr.hs.dgsw.cns.domain.MemberId;
import kr.hs.dgsw.cns.global.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService<Long> {

    private final QueryMemberSpi queryMemberSpi;

    @Override
    public Object loadAuthByIdentify(Long identify) {
        return queryMemberSpi.findById(new MemberId(identify))
                .orElseThrow();
    }
}
