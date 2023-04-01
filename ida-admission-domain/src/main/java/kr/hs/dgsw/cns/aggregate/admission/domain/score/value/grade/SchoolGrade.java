package kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.Point;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.Subject;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint.Grade;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint.Semester;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SchoolGrade {

    private Grade grade;

    private Semester semester;

    private Subject subject;

    private boolean doubled;

    private Point point;

}
