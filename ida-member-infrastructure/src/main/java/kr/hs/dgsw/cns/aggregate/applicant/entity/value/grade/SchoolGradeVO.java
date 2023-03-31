package kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Grade;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Point;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Semester;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Subject;
import kr.hs.dgsw.cns.aggregate.converter.PointConverter;
import kr.hs.dgsw.cns.aggregate.converter.SubjectConverter;
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
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Semester semester;

    @Column(nullable = false)
    @Convert(converter = SubjectConverter.class)
    private Subject subject;

    private boolean doubled;

    @Column(nullable = false)
    @Convert(converter = PointConverter.class)
    private Point point;

}
