package kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.Point;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VolunteerPoint {

    private Grade grade;

    private Point point;

}
