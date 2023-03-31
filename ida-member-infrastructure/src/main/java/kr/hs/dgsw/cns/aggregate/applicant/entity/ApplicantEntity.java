package kr.hs.dgsw.cns.aggregate.applicant.entity;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.AdmissionType;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.PersonalInformation;
import kr.hs.dgsw.cns.global.embedd.EmbeddedMemberId;
import lombok.*;

@Entity
@Getter
@Builder
@ToString
@Table(name = "applicant")
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantEntity {

    @EmbeddedId
    @AttributeOverrides(
            @AttributeOverride(name = "id", column = @Column(name = "applicant_id"))
    )
    private EmbeddedMemberId embeddedMemberId;

    @Embedded
    private PersonalInformation information;

    @OneToOne
    @JoinColumn(name = "score_score_id")
    private AbstractScore score;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AdmissionType type;

}
