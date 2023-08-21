package kr.hs.dgsw.cns.aggregate.admission.entity.score.util;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint.AchieveLevel;
import org.springframework.stereotype.Component;

@Component
public class ConvertGradeRecord {

    public int getValue(AchieveLevel level) {
        return level.getValue();
    }
}
