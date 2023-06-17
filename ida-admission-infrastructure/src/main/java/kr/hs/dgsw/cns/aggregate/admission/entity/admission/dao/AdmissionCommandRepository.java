package kr.hs.dgsw.cns.aggregate.admission.entity.admission.dao;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.Admission;
import kr.hs.dgsw.cns.aggregate.admission.entity.admission.AdmissionEntity;
import kr.hs.dgsw.cns.aggregate.admission.entity.admission.mapper.AdmissionMapper;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.admission.CommandAdmissionSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdmissionCommandRepository implements CommandAdmissionSpi {

    private final AdmissionMapper mapper;
    private final AdmissionRepository repository;

    @Override
    public void save(Admission admission) {
        AdmissionEntity entity = mapper.domainToEntity(admission);
        repository.save(entity);
    }
}
