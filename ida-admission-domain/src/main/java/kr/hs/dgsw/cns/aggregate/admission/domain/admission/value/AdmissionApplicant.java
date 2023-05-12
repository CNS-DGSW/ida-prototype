package kr.hs.dgsw.cns.aggregate.admission.domain.admission.value;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.constraint.AdmissionType;
import kr.hs.dgsw.cns.domain.MemberId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AdmissionApplicant {

    private final MemberId id;

    // 지원 전형
    private AdmissionType type;

}
