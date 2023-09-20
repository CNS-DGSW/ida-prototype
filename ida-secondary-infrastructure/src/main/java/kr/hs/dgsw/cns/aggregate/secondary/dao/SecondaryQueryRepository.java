package kr.hs.dgsw.cns.aggregate.secondary.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.hs.dgsw.cns.aggregate.secondary.domain.Secondary;
import kr.hs.dgsw.cns.aggregate.secondary.entity.QSecondaryScoreEntity;
import kr.hs.dgsw.cns.aggregate.secondary.entity.SecondaryScoreEntity;
import kr.hs.dgsw.cns.aggregate.secondary.mapper.SecondaryScoreMapper;
import kr.hs.dgsw.cns.aggregate.secondary.spi.query.QuerySecondarySpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SecondaryQueryRepository implements QuerySecondarySpi {
    private final JPAQueryFactory queryFactory;
    private final SecondaryScoreMapper mapper;

    @Override
    public Optional<Secondary> findByExamCode(Long ExamCode) {
        QSecondaryScoreEntity secondaryScoreEntity = QSecondaryScoreEntity.secondaryScoreEntity;
        SecondaryScoreEntity entity = queryFactory.selectFrom(secondaryScoreEntity)
                .where(secondaryScoreEntity.examinee.examCode.eq(ExamCode))
                .fetchOne();
        if (entity == null) {
            return Optional.empty();
        }
        return Optional.of(mapper.entityToDomain(entity));
    }

    @Override
    public Optional<Secondary> findBySecondaryId(Long secondaryId) {
        QSecondaryScoreEntity secondaryScoreEntity = QSecondaryScoreEntity.secondaryScoreEntity;
        SecondaryScoreEntity entity = queryFactory.selectFrom(secondaryScoreEntity)
                .where(secondaryScoreEntity.id.eq(secondaryId))
                .fetchOne();
        if (entity == null) {
            return Optional.empty();
        }
        return Optional.of(mapper.entityToDomain(entity));
    }
}
