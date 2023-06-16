package kr.hs.dgsw.cns.aggregate.admission.entity.score.dao;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.Score;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.mapper.ScoreMapper;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.score.CommandScoreSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScoreCommandRepository implements CommandScoreSpi {

    private final ScoreRepository repository;
    private final ScoreMapper scoreMapper;

    @Override
    public void save(Score score) {
        repository.save(scoreMapper.domainToEntity(score));
    }
}
