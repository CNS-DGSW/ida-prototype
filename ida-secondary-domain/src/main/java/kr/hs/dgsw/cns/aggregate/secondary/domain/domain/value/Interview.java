package kr.hs.dgsw.cns.aggregate.secondary.domain.domain.value;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Interview {

    //학업 및 진로 역량
    private int studyScore;

    //컴퓨팅 사고력 역량
    private int computingScore;

}
