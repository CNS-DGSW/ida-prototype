package kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint.Grade;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint.Semester;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AttendancePoint {

    private Grade grade;

    private Semester semester;

    private short absence;

    private short tardiness;

    private short earlyLeave;

    private short skipped;

}
