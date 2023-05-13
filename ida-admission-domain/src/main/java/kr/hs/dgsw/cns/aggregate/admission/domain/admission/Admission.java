package kr.hs.dgsw.cns.aggregate.admission.domain.admission;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.AdmissionApplicant;
import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.AdmissionStatus;
import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.Document;
import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.constraint.Progress;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.Score;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Admission {

    private Long id;

    private AdmissionApplicant applicant;

    private Document document;

    private AdmissionStatus status;

    private Progress progress;

    private Score score;

}
