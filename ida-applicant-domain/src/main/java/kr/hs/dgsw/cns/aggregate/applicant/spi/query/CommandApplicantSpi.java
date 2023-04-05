package kr.hs.dgsw.cns.aggregate.applicant.spi.query;

import kr.hs.dgsw.cns.aggregate.applicant.domain.Applicant;

public interface CommandApplicantSpi {

    Applicant save(Applicant domain);

}
