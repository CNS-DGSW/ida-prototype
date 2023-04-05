package kr.hs.dgsw.cns.aggregate.applicant.dao;

import kr.hs.dgsw.cns.aggregate.applicant.domain.Applicant;
import kr.hs.dgsw.cns.aggregate.applicant.entity.ApplicantEntity;
import kr.hs.dgsw.cns.aggregate.applicant.mapper.ApplicantMapper;
import kr.hs.dgsw.cns.aggregate.applicant.spi.query.CommandApplicantSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ApplicantRepository implements CommandApplicantSpi {

    private final ApplicantCommandRepository commandRepository;
    private final ApplicantMapper mapper;

    @Override
    public Applicant save(Applicant domain) {
        ApplicantEntity entity = mapper.domainToEntity(domain);
        return mapper.entityToDomain(commandRepository.save(entity));
    }
}
