package kr.hs.dgsw.cns.aggregate.applicant.domain.value.grade;

import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Grade;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.constraint.Semester;
import lombok.Getter;

@Getter
public class LeaderShipPoint {

    private Grade grade;

    private Semester semester;

    private boolean check;

}
