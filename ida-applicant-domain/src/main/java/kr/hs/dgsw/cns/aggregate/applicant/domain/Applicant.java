package kr.hs.dgsw.cns.aggregate.applicant.domain;

import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.AdmissionType;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Privacy;
import kr.hs.dgsw.cns.domain.MemberId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Applicant {

    private final MemberId id;

    private AdmissionType admissionType;

    private Privacy privacy;

    private Score score;

    public void updateAdmissionType(AdmissionType type) {
        this.admissionType = type;
    }

}
