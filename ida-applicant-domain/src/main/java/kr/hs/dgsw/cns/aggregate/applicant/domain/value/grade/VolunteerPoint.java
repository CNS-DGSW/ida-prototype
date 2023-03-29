package kr.hs.dgsw.cns.aggregate.applicant.domain.value.grade;

import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Point;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VolunteerPoint {

    private Grade grade;

    private Point point;

}
