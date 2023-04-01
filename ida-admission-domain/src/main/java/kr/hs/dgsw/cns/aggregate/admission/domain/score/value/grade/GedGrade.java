package kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.Point;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.Subject;
import lombok.*;

@Getter
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GedGrade {

    private Subject subject;

    private Point point;

}
