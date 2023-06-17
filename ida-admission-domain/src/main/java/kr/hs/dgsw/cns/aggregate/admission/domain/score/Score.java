package kr.hs.dgsw.cns.aggregate.admission.domain.score;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.AdmissionApplicant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.constraint.AdmissionType.GENERAL;

@Getter
@RequiredArgsConstructor
public abstract class Score {

    private final ScoreId id;

    private double generalScore;

    private double specialScore;

    private double absence;

    private double volunteer;

    private double additional;

    public void setGeneralScore(double generalScore) {
        this.generalScore = generalScore;
    }

    public void setSpecialScore(double specialScore) {
        this.specialScore = specialScore;
    }

    public void setAbsence(double absence) {
        this.absence = absence;
    }

    public void setVolunteer(double volunteer) {
        this.volunteer = volunteer;
    }

    public void setAdditional(double additional) {
        this.additional = additional;
    }

    public double getTotal(AdmissionApplicant applicant) {
        double totalScore = 0.0;
        if (!(this instanceof GedScore)) {
            totalScore += this.getAbsence() + this.getVolunteer() + this.additional;
        }

        totalScore += (applicant.getType() == GENERAL) ? generalScore : specialScore;
        return totalScore;
    }

    public double getScore(AdmissionApplicant applicant) {
        return (applicant.getType() == GENERAL) ? generalScore : specialScore;
    }
}
