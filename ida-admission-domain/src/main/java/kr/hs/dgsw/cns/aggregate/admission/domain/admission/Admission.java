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

    // 지원생
    private AdmissionApplicant applicant;

    // 자소서 및 학업계획서
    private Document document;

    // 제출 현황
    private AdmissionStatus status;

    // 지원, 1차, 2차 상태
    private Progress progress;

    // 점수
    private Score score;

    public void updateDocument(Document document) {
        this.document = document;
    }

    public void updateStatus(AdmissionStatus status) {
        this.status = status;
    }

    public void updateProgress(Progress progress) {
        this.progress = progress;
    }
}
