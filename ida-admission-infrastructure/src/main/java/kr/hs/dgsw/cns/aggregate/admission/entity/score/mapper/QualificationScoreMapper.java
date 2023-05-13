package kr.hs.dgsw.cns.aggregate.admission.entity.score.mapper;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.GedScore;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.QualificationScoreEntity;
import kr.hs.dgsw.cns.global.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class QualificationScoreMapper implements Mapper<GedScore, QualificationScoreEntity> {

    @Override
    public QualificationScoreEntity domainToEntity(GedScore domain) {
        return null;
    }

    @Override
    public GedScore entityToDomain(QualificationScoreEntity entity) {
        return null;
    }
}
