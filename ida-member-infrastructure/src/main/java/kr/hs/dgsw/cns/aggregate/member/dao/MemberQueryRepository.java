package kr.hs.dgsw.cns.aggregate.member.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.hs.dgsw.cns.aggregate.member.domain.Member;
import kr.hs.dgsw.cns.aggregate.member.entity.MemberEntity;
import kr.hs.dgsw.cns.aggregate.member.entity.QMemberEntity;
import kr.hs.dgsw.cns.aggregate.member.mapper.MemberIdMapper;
import kr.hs.dgsw.cns.aggregate.member.mapper.MemberMapper;
import kr.hs.dgsw.cns.aggregate.member.spi.query.QueryMemberSpi;
import kr.hs.dgsw.cns.domain.MemberId;
import kr.hs.dgsw.cns.global.embedd.QMemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository implements QueryMemberSpi {

    private final JPAQueryFactory jpaQuery;
    private final MemberIdMapper idMapper;
    private final MemberMapper mapper;


    @Override
    public Member findById(MemberId memberId) {
        QMemberEntity memberEntity = QMemberEntity.memberEntity;
        MemberEntity entity = jpaQuery.selectFrom(memberEntity)
                .where(QMemberId.memberId.eq(idMapper.domainToEntity(memberId)))
                .fetchOne();
        assert entity != null;
        return mapper.entityToDomain(entity);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.empty();
    }
}
