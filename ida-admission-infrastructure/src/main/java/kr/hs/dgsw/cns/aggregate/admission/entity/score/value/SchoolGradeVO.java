package kr.hs.dgsw.cns.aggregate.admission.entity.score.value;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.admission.converter.SubjectConverter;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.Subject;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint.AchieveLevel;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint.Grade;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint.Semester;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Embeddable
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SchoolGradeVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3021000144434854018L;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Grade grade;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Semester semester;

    @Column(nullable = false)
    @Convert(converter = SubjectConverter.class)
    private Subject subject;

    private boolean doubled;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AchieveLevel point;

}
