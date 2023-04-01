package kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint.Grade;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint.Semester;
import lombok.Getter;

@Getter
public class LeaderShipPoint {

    private Grade grade;

    private Semester semester;

    private boolean check;

}
