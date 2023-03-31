package kr.hs.dgsw.cns.aggregate.applicant.dao;

import kr.hs.dgsw.cns.aggregate.applicant.entity.ApplicantEntity;
import kr.hs.dgsw.cns.global.embedd.EmbeddedMemberId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantCommandRepository extends CrudRepository<ApplicantEntity, EmbeddedMemberId> {
}
