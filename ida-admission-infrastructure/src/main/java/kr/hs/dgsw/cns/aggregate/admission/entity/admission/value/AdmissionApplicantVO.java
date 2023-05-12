package kr.hs.dgsw.cns.aggregate.admission.entity.admission.value;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.constraint.AdmissionType;
import kr.hs.dgsw.cns.aggregate.applicant.entity.ApplicantEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
@Embeddable
@AllArgsConstructor @NoArgsConstructor
public class AdmissionApplicantVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2776868143460154080L;

    @OneToOne
    @JoinColumn(name = "applicant_id")
    private ApplicantEntity applicant;

    @Enumerated(EnumType.STRING)
    @Column(name = "admission_type")
    private AdmissionType admissionType;

}
