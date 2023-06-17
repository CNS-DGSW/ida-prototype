package kr.hs.dgsw.cns.aggregate.admission.dto.admission;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.SchoolGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class SchoolScoreDto {
    private List<SchoolGrade> grades;
}
