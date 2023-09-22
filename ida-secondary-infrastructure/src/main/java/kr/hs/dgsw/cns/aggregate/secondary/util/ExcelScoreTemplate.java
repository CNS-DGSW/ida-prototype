package kr.hs.dgsw.cns.aggregate.secondary.util;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.Admission;
import kr.hs.dgsw.cns.aggregate.applicant.domain.Applicant;
import kr.hs.dgsw.cns.aggregate.secondary.domain.Secondary;
import lombok.Getter;
@Getter
public class ExcelScoreTemplate {
    private Long examCode;

    private String userName;

    private String gender;

    private String schoolName;

    private String firstApplyType;

    private String cityName;

    private double schoolScore;

    private double aptitudeScore;

    private double studyScore;

    private double computingScore;

    private double totalScore;

    public ExcelScoreTemplate(Secondary secondary, Applicant applicant, Admission admission) {
        this.examCode = secondary.getExaminee().getExamCode();
        this.userName = applicant.getPrivacy().getName();
        this.gender = switch (applicant.getPrivacy().getGender()) {
            case MALE -> "남";
            case FEMALE -> "여";
        };
        this.schoolName = String.valueOf(applicant.getPrivacy().getSchool().getCode());
        this.firstApplyType = switch (admission.getApplicant().getType()) {
            case NONE -> "없음";
            case GENERAL -> "일반전형";
            case MEISTER -> "마이스터인재전형";
            case REGIONAL_PRIORITY -> "지역우선전형";
            case SOCIAL_INTEGRATION -> "사회통합전형";
            case SPECIAL_ADMISSION -> "특별전형";
            case VETERANS -> "국가보훈";
        };
        this.cityName = applicant.getPrivacy().getAddress().getStreetAddress();
        this.schoolScore = admission.getScore().getTotal(admission.getApplicant());
        this.aptitudeScore = secondary.getAptitude().getScore();
        this.studyScore = secondary.getInterview().getStudyScore();
        this.computingScore = secondary.getInterview().getComputingScore();
        this.totalScore = (schoolScore * 0.2) +
                (aptitudeScore * 0.2) +
                (studyScore * 0.3) +
                (computingScore * 0.3);
    }
}