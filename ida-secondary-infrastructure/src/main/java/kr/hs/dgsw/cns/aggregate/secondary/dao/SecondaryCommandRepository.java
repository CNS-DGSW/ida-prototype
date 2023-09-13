package kr.hs.dgsw.cns.aggregate.secondary.dao;

import kr.hs.dgsw.cns.aggregate.secondary.domain.Secondary;
import kr.hs.dgsw.cns.aggregate.secondary.entity.SecondaryScoreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SecondaryCommandRepository extends CrudRepository<Secondary, Long> {
    Optional<SecondaryScoreEntity> findByExaminee_ExamCode(Long ExamCOde);
}
