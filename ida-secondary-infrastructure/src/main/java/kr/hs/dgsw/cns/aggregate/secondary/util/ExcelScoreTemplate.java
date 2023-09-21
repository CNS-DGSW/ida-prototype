package kr.hs.dgsw.cns.aggregate.secondary.util;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.Admission;
import kr.hs.dgsw.cns.aggregate.applicant.domain.Applicant;
import kr.hs.dgsw.cns.aggregate.secondary.domain.Secondary;
import lombok.Getter;
@Getter
public class ExcelScoreTemplate {
    private String examCode;
    private String userName;
    private String sex;
    private String schoolName;
    private String firstApplyType;
    private String cityName;
    private double schoolScore;
    private double aptitudeScore;
    private double studyScore;
    private double computingScore;
    private double totalScore;


    public ExcelScoreTemplate(Secondary secondary, Applicant applicant, Admission admission) {
        this.examCode = String.valueOf(secondary.getExaminee().getExamCode());
        this.userName = applicant.getPrivacy().getName();
        this.sex = String.valueOf(applicant.getPrivacy().getGender());
        this.schoolName = String.valueOf(applicant.getPrivacy().getSchool().getCode());
        this.firstApplyType = String.valueOf(admission.getApplicant().getType());
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