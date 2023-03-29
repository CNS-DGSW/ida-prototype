package kr.hs.dgsw.cns.aggregate.applicant.domain.value.grade;

import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Point;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Subject;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Grade;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Semester;
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
