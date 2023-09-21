package kr.hs.dgsw.cns.aggregate.secondary.mapper;

import kr.hs.dgsw.cns.aggregate.admission.entity.admission.mapper.AdmissionMapper;
import kr.hs.dgsw.cns.aggregate.secondary.domain.Secondary;
import kr.hs.dgsw.cns.aggregate.secondary.entity.SecondaryScoreEntity;
import kr.hs.dgsw.cns.global.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SecondaryScoreListMapper implements Mapper<List<Secondary>, List<SecondaryScoreEntity>> {

    private static final SecondaryScoreMapper.AptitudeMapper APTITUDE_MAPPER = new SecondaryScoreMapper.AptitudeMapper();

    private static final SecondaryScoreMapper.ExamineeMapper EXAMINEE_MAPPER = new SecondaryScoreMapper.ExamineeMapper();

    private static final SecondaryScoreMapper.InterviewMapper INTERVIEW_MAPPER = new SecondaryScoreMapper.InterviewMapper();

    private final AdmissionMapper admissionMapper;

    @Override
    public List<SecondaryScoreEntity> domainToEntity(List<Secondary> domain) {
        return domain.stream().map(secondary -> new SecondaryScoreEntity(
                secondary.getId(),
                EXAMINEE_MAPPER.domainToEntity(secondary.getExaminee()),
                APTITUDE_MAPPER.domainToEntity(secondary.getAptitude()),
                INTERVIEW_MAPPER.domainToEntity(secondary.getInterview()),
                admissionMapper.domainToEntity(secondary.getAdmission())
        )).collect(Collectors.toList());
    }

    @Override
    public List<Secondary> entityToDomain(List<SecondaryScoreEntity> entity) {
        return entity.stream().map(secondaryScoreEntity -> new Secondary(
                secondaryScoreEntity.getId(),
                EXAMINEE_MAPPER.entityToDomain(secondaryScoreEntity.getExaminee()),
                APTITUDE_MAPPER.entityToDomain(secondaryScoreEntity.getAptitude()),
                INTERVIEW_MAPPER.entityToDomain(secondaryScoreEntity.getInterview()),
                admissionMapper.entityToDomain(secondaryScoreEntity.getAdmission())
        )).collect(Collectors.toList());
    }
}
