package kr.hs.dgsw.cns.aggregate.admission.spi.query.admission;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.Admission;
import kr.hs.dgsw.cns.domain.spi.QuerySpi;

import java.util.Optional;

public interface QueryAdmissionSpi extends QuerySpi<Admission, Long> {

    Optional<Admission> findByMemberId(Long memberId);

    @Override
    default Optional<Admission> findById(Long aLong) {
        return findByMemberId(aLong);
    }
}
