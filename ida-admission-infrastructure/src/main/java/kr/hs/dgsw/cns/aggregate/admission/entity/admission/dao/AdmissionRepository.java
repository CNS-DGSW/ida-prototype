package kr.hs.dgsw.cns.aggregate.admission.entity.admission.dao;

import kr.hs.dgsw.cns.aggregate.admission.entity.admission.AdmissionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRepository extends CrudRepository<AdmissionEntity, Long> {
}
