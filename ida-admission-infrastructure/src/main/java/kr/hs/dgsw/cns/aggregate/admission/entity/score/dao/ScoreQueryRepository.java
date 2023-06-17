package kr.hs.dgsw.cns.aggregate.admission.entity.score.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.Score;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.ScoreId;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.score.QueryScoreSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScoreQueryRepository implements QueryScoreSpi {

    private final JPAQueryFactory factory;

    @Override
    public Optional<Score> findById(ScoreId scoreId) {
//        return factory.select();
        return Optional.empty();
    }
}
