package kr.hs.dgsw.cns.aggregate.member.spi.query;

import kr.hs.dgsw.cns.domain.MemberId;
import kr.hs.dgsw.cns.aggregate.member.domain.Member;
import kr.hs.dgsw.cns.domain.spi.QuerySpi;

import java.util.Optional;

public interface QueryMemberSpi extends QuerySpi<Member, MemberId> {

    Optional<Member> findByEmail(String email);

}
