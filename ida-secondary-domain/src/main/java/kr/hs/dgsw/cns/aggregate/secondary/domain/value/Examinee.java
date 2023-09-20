package kr.hs.dgsw.cns.aggregate.secondary.domain.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Examinee {

    //수험 범호
    private Long examCode;

}
