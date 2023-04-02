package kr.hs.dgsw.cns.aggregate.applicant.spi.query;

import kr.hs.dgsw.cns.aggregate.applicant.domain.Applicant;

public interface CommandApplicantSpi {

    <S extends Applicant> S save(S entity);

}
