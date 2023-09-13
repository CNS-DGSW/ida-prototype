package kr.hs.dgsw.cns.aggregate.secondary.entity;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.secondary.entity.value.AptitudeVO;
import kr.hs.dgsw.cns.aggregate.secondary.entity.value.ExamineeVO;
import kr.hs.dgsw.cns.aggregate.secondary.entity.value.InterviewVO;
import lombok.*;

@Getter
@Entity
@Builder
@Table(name = "secondary_score")
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SecondaryScoreEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ExamineeVO examinee;

    @Embedded
    private AptitudeVO aptitude;

    @Embedded
    private InterviewVO interview;

}
