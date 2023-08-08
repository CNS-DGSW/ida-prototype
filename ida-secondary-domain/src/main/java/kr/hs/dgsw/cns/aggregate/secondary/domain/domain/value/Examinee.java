package kr.hs.dgsw.cns.aggregate.secondary.domain.domain.value;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.Admission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Examinee {

    private Admission admission;

    //수험 범호
    private Long examCode;

}
