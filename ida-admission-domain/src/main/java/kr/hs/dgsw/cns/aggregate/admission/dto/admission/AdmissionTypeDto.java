package kr.hs.dgsw.cns.aggregate.admission.dto.admission;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.constraint.AdmissionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class AdmissionTypeDto {

    private AdmissionType type;

}
