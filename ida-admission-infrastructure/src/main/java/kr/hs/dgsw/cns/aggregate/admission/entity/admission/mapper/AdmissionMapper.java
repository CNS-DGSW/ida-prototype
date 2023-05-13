package kr.hs.dgsw.cns.aggregate.admission.entity.admission.mapper;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.Admission;
import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.AdmissionApplicant;
import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.AdmissionStatus;
import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.Document;
import kr.hs.dgsw.cns.aggregate.admission.entity.admission.AdmissionEntity;
import kr.hs.dgsw.cns.aggregate.admission.entity.admission.value.AdmissionApplicantVO;
import kr.hs.dgsw.cns.aggregate.admission.entity.admission.value.AdmissionStatusVO;
import kr.hs.dgsw.cns.aggregate.admission.entity.admission.value.DocumentVO;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.mapper.ScoreMapper;
import kr.hs.dgsw.cns.domain.MemberId;
import kr.hs.dgsw.cns.global.embedd.EmbeddedMemberId;
import kr.hs.dgsw.cns.global.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdmissionMapper implements Mapper<Admission, AdmissionEntity> {

    private static final AdmissionApplicantMapper APPLICANT_MAPPER = new AdmissionApplicantMapper();
    private static final DocumentMapper DOCUMENT_MAPPER = new DocumentMapper();
    private static final AdmissionStatusMapper STATUS_MAPPER = new AdmissionStatusMapper();
    private final ScoreMapper scoreMapper;

    @Override
    public AdmissionEntity domainToEntity(Admission domain) {
        return AdmissionEntity.builder()
                .applicant(APPLICANT_MAPPER.domainToEntity(domain.getApplicant()))
                .document(DOCUMENT_MAPPER.domainToEntity(domain.getDocument()))
                .admissionStatus(STATUS_MAPPER.domainToEntity(domain.getStatus()))
                .progress(domain.getProgress())
                .score(scoreMapper.domainToEntity(domain.getScore()))
                .build();
    }

    @Override
    public Admission entityToDomain(AdmissionEntity entity) {
        return Admission.builder()
                .applicant(APPLICANT_MAPPER.entityToDomain(entity.getApplicant()))
                .document(DOCUMENT_MAPPER.entityToDomain(entity.getDocument()))
                .status(STATUS_MAPPER.entityToDomain(entity.getAdmissionStatus()))
                .progress(entity.getProgress())
                .score(scoreMapper.entityToDomain(entity.getScore()))
                .build();
    }

    static class AdmissionApplicantMapper implements Mapper<AdmissionApplicant, AdmissionApplicantVO> {
        @Override
        public AdmissionApplicantVO domainToEntity(AdmissionApplicant domain) {
            return AdmissionApplicantVO.builder()
                    .applicantId(EmbeddedMemberId.of(domain.getId().getId()))
                    .admissionType(domain.getType())
                    .build();
        }

        @Override
        public AdmissionApplicant entityToDomain(AdmissionApplicantVO entity) {
            return AdmissionApplicant.builder()
                    .id(new MemberId(entity.getApplicantId().getId()))
                    .type(entity.getAdmissionType())
                    .build();
        }
    }

    static class DocumentMapper implements Mapper<Document, DocumentVO> {
        @Override
        public DocumentVO domainToEntity(Document domain) {
            return DocumentVO.builder()
                    .introduction(domain.getIntroduction())
                    .studyPlan(domain.getStudyPlan())
                    .build();
        }

        @Override
        public Document entityToDomain(DocumentVO entity) {
            return Document.builder()
                    .introduction(entity.getIntroduction())
                    .studyPlan(entity.getStudyPlan())
                    .build();
        }
    }

    static class AdmissionStatusMapper implements Mapper<AdmissionStatus, AdmissionStatusVO> {
        @Override
        public AdmissionStatusVO domainToEntity(AdmissionStatus domain) {
            return AdmissionStatusVO.builder()
                    .submission(domain.isSubmission())
                    .mailArrival(domain.isMailArrival())
                    .review(domain.isReview())
                    .submissionTime(domain.getSubmissionTime())
                    .confirmation(domain.isConfirmation())
                    .build();
        }

        @Override
        public AdmissionStatus entityToDomain(AdmissionStatusVO entity) {
            return AdmissionStatus.builder()
                    .submission(entity.isSubmission())
                    .mailArrival(entity.isMailArrival())
                    .review(entity.isReview())
                    .submissionTime(entity.getSubmissionTime())
                    .confirmation(entity.isConfirmation())
                    .build();
        }
    }
}
