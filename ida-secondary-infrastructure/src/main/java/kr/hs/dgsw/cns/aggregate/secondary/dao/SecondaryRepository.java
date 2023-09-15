package kr.hs.dgsw.cns.aggregate.secondary.dao;

import kr.hs.dgsw.cns.aggregate.secondary.entity.SecondaryScoreEntity;
import org.springframework.data.repository.CrudRepository;

public interface SecondaryRepository extends CrudRepository<SecondaryScoreEntity, Long> {
}
