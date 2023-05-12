package kr.hs.dgsw.cns.aggregate.admission.entity.admission;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.constraint.Progress;
import kr.hs.dgsw.cns.aggregate.admission.entity.admission.value.AdmissionApplicantVO;
import kr.hs.dgsw.cns.aggregate.admission.entity.admission.value.AdmissionStatusVO;
import kr.hs.dgsw.cns.aggregate.admission.entity.admission.value.DocumentVO;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.AbstractScore;
import lombok.*;

@Getter
@Entity
@Builder
@Table(name = "admission")
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdmissionEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AdmissionApplicantVO applicant;

    @Embedded
    private DocumentVO document;

    @Embedded
    private AdmissionStatusVO admissionStatus;

    @Enumerated(EnumType.STRING)
    private Progress progress;

    @OneToOne
    @JoinColumn(name = "score_id")
    private AbstractScore score;

}
