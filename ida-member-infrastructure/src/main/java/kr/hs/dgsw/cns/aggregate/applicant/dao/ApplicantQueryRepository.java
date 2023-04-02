package kr.hs.dgsw.cns.aggregate.applicant.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.hs.dgsw.cns.aggregate.applicant.domain.Applicant;
import kr.hs.dgsw.cns.aggregate.applicant.entity.ApplicantEntity;
import kr.hs.dgsw.cns.aggregate.applicant.entity.QApplicantEntity;
import kr.hs.dgsw.cns.aggregate.applicant.mapper.ApplicantMapper;
import kr.hs.dgsw.cns.aggregate.applicant.spi.query.QueryApplicantSpi;
import kr.hs.dgsw.cns.domain.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ApplicantQueryRepository implements QueryApplicantSpi {

    private final JPAQueryFactory queryFactory;
    private final ApplicantMapper mapper;

    @Override
    public Optional<Applicant> findById(MemberId memberId) {
        QApplicantEntity applicantEntity = QApplicantEntity.applicantEntity;
        ApplicantEntity entity = queryFactory.selectFrom(applicantEntity)
                .where(applicantEntity.embeddedMemberId.id.eq(memberId.getId()))
                .fetchOne();
        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(mapper.entityToDomain(entity));
    }
}
