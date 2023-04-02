package kr.hs.dgsw.cns.aggregate.member.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.hs.dgsw.cns.aggregate.member.domain.Member;
import kr.hs.dgsw.cns.aggregate.member.entity.MemberEntity;
import kr.hs.dgsw.cns.aggregate.member.entity.QMemberEntity;
import kr.hs.dgsw.cns.aggregate.member.mapper.MemberIdMapper;
import kr.hs.dgsw.cns.aggregate.member.mapper.MemberMapper;
import kr.hs.dgsw.cns.aggregate.member.spi.query.QueryMemberSpi;
import kr.hs.dgsw.cns.domain.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository implements QueryMemberSpi {

    private final JPAQueryFactory queryFactory;
    private final MemberIdMapper idMapper;
    private final MemberMapper mapper;

    @Override
    public Optional<Member> findById(MemberId memberId) {
        QMemberEntity memberEntity = QMemberEntity.memberEntity;
        MemberEntity entity = queryFactory.selectFrom(memberEntity)
                .where(memberEntity.id.id.eq(memberId.getId()))
                .fetchOne();
        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(mapper.entityToDomain(entity));
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        QMemberEntity memberEntity = QMemberEntity.memberEntity;
        MemberEntity entity = queryFactory.selectFrom(memberEntity)
                .where(memberEntity.email.eq(email))
                .fetchOne();
        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(mapper.entityToDomain(entity));
    }
}
