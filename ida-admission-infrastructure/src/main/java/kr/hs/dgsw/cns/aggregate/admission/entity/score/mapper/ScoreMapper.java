package kr.hs.dgsw.cns.aggregate.admission.entity.score.mapper;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.GedScore;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.SchoolScore;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.Score;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.AbstractScore;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.QualificationScoreEntity;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.SchoolScoreEntity;
import kr.hs.dgsw.cns.global.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScoreMapper implements Mapper<Score, AbstractScore> {

    private final QualificationScoreMapper qualificationScoreMapper;
    private final SchoolScoreMapper schoolScoreMapper;

    @Override
    public AbstractScore domainToEntity(Score domain) {
        if (domain instanceof SchoolScore score) {
            return schoolScoreMapper.domainToEntity(score);
        }
        else if (domain instanceof GedScore score) {
            return qualificationScoreMapper.domainToEntity(score);
        }
        else {
            throw new IllegalArgumentException("cannot found instance of domain!");
        }
    }

    @Override
    public Score entityToDomain(AbstractScore entity) {
        if (entity instanceof SchoolScoreEntity score) {
            return schoolScoreMapper.entityToDomain(score);
        }
        else if (entity instanceof QualificationScoreEntity score) {
            return qualificationScoreMapper.entityToDomain(score);
        }
        else {
            throw new IllegalArgumentException("cannot found instance of entity!");
        }
    }
}
