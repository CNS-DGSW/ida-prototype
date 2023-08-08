package kr.hs.dgsw.cns.aggregate.secondary.mapper;

import kr.hs.dgsw.cns.aggregate.admission.entity.admission.mapper.AdmissionMapper;
import kr.hs.dgsw.cns.aggregate.secondary.SecondaryScoreEntity;
import kr.hs.dgsw.cns.aggregate.secondary.domain.domain.Secondary;
import kr.hs.dgsw.cns.aggregate.secondary.domain.domain.value.Aptitude;
import kr.hs.dgsw.cns.aggregate.secondary.domain.domain.value.Examinee;
import kr.hs.dgsw.cns.aggregate.secondary.domain.domain.value.Interview;
import kr.hs.dgsw.cns.aggregate.secondary.value.AptitudeVO;
import kr.hs.dgsw.cns.aggregate.secondary.value.ExamineeVO;
import kr.hs.dgsw.cns.aggregate.secondary.value.InterviewVO;
import kr.hs.dgsw.cns.global.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecondaryScoreMapper implements Mapper<Secondary, SecondaryScoreEntity> {
    private static final AptitudeMapper APTITUDE_MAPPER = new AptitudeMapper();

    private static final ExamineeMapper EXAMINEE_MAPPER = new ExamineeMapper();

    private static final InterviewMapper INTERVIEW_MAPPER = new InterviewMapper();


    @Override
    public SecondaryScoreEntity domainToEntity(Secondary domain) {
        return SecondaryScoreEntity.builder()
                .aptitude(APTITUDE_MAPPER.domainToEntity(domain.getAptitude()))
                .examinee(EXAMINEE_MAPPER.domainToEntity(domain.getExaminee()))
                .interview(INTERVIEW_MAPPER.domainToEntity(domain.getInterview()))
                .build();
    }

    @Override
    public Secondary entityToDomain(SecondaryScoreEntity entity) {
        return Secondary.builder()
                .aptitude(APTITUDE_MAPPER.entityToDomain(entity.getAptitude()))
                .examinee(EXAMINEE_MAPPER.entityToDomain(entity.getExaminee()))
                .interview(INTERVIEW_MAPPER.entityToDomain(entity.getInterview()))
                .build();
    }


    static class AptitudeMapper implements Mapper<Aptitude, AptitudeVO> {

        @Override
        public AptitudeVO domainToEntity(Aptitude domain) {
            return AptitudeVO.builder()
                    .score(domain.getScore())
                    .build();
        }

        @Override
        public Aptitude entityToDomain(AptitudeVO entity) {
            return Aptitude.builder()
                    .score(entity.getScore())
                    .build();
        }
    }

    @RequiredArgsConstructor
    static class ExamineeMapper implements Mapper<Examinee, ExamineeVO> {

        private AdmissionMapper admissionMapper;

        @Override
        public ExamineeVO domainToEntity(Examinee domain) {
            return ExamineeVO.builder()
                    .admission(admissionMapper.domainToEntity(domain.getAdmission()))
                    .examCode(domain.getExamCode())
                    .build();
        }

        @Override
        public Examinee entityToDomain(ExamineeVO entity) {
            return Examinee.builder()
                    .admission(admissionMapper.entityToDomain(entity.getAdmission()))
                    .examCode(entity.getExamCode())
                    .build();
        }
    }

    static class InterviewMapper implements Mapper<Interview, InterviewVO> {

        @Override
        public InterviewVO domainToEntity(Interview domain) {
            return InterviewVO.builder()
                    .computingScore(domain.getComputingScore())
                    .studyScore(domain.getStudyScore())
                    .build();
        }

        @Override
        public Interview entityToDomain(InterviewVO entity) {
            return Interview.builder()
                    .computingScore(entity.getComputingScore())
                    .studyScore(entity.getStudyScore())
                    .build();
        }
    }
}
