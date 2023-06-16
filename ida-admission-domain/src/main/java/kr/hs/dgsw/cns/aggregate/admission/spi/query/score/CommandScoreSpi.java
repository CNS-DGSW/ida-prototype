package kr.hs.dgsw.cns.aggregate.admission.spi.query.score;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.Score;

public interface CommandScoreSpi {

    void save(Score score);

}
