package kr.hs.dgsw.cns.aggregate.secondary.util;

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
    private int aptitudeScore;
    private int studyScore;
    private int computingScore;


    public ExcelScoreTemplate(Secondary secondary, Applicant applicant) {
        this.examCode = String.valueOf(secondary.getExaminee().getExamCode());
        this.userName = applicant.getPrivacy().getName();
        this.sex = String.valueOf(applicant.getPrivacy().getGender());
        this.schoolName = String.valueOf(applicant.getPrivacy().getSchool().getCode());
        this.firstApplyType = null;
        this.cityName = applicant.getPrivacy().getAddress().getStreetAddress();
        this.aptitudeScore = secondary.getAptitude().getScore();
        this.studyScore = secondary.getInterview().getStudyScore();
        this.computingScore = secondary.getInterview().getComputingScore();
    }
}