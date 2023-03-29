package kr.hs.dgsw.cns.aggregate.applicant.domain.value.grade;

import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Point;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Subject;
import lombok.*;

@Getter
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GedGrade {

    private Subject subject;

    private Point point;

}
