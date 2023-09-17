package kr.hs.dgsw.cns.aggregate.secondary.domain.domain;

import kr.hs.dgsw.cns.aggregate.secondary.domain.value.Aptitude;
import kr.hs.dgsw.cns.aggregate.secondary.domain.value.Examinee;
import kr.hs.dgsw.cns.aggregate.secondary.domain.value.Interview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Secondary {

    private Long id;

    //수험자
    private Examinee examinee;

    //직무적성소양 검사
    private Aptitude aptitude;

    //심층 면접
    private Interview interview;

}
