package kr.hs.dgsw.cns.aggregate.admission.entity.score.dao;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.ScoreId;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.AbstractScore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends CrudRepository<AbstractScore, ScoreId> {

}
