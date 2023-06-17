package kr.hs.dgsw.cns.aggregate.admission.spi.query.admission;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.Admission;

public interface CommandAdmissionSpi {

    void save(Admission admission);

}
