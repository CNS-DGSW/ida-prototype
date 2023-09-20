package kr.hs.dgsw.cns.aggregate.secondary.spi.query;

import kr.hs.dgsw.cns.aggregate.secondary.domain.Secondary;
import kr.hs.dgsw.cns.domain.spi.QuerySpi;

import java.util.List;
import java.util.Optional;

public interface QuerySecondarySpi extends QuerySpi<Secondary, Long> {
    Optional<Secondary> findByExamCode(Long ExamCode);

    Optional<Secondary> findBySecondaryId(Long secondaryId);

    List<Secondary> findByAll();

    @Override
    default Optional<Secondary> findById(Long aLong) {
        return findBySecondaryId(aLong);
    }
}
