package kr.hs.dgsw.cns.aggregate.admission.domain.admission.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Document {

    // 자기소개서
    private String introduction;

    // 학업계획서
    private String studyPlan;

}
