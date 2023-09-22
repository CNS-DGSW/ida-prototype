package kr.hs.dgsw.cns.aggregate.admission.usecase.score;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.Admission;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.GedScore;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.SchoolScore;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.Score;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.GedGrade;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.SchoolGrade;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.admission.QueryAdmissionSpi;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.score.CommandScoreSpi;
import kr.hs.dgsw.cns.aggregate.admission.spi.service.SchoolScoreService;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ScoreCalculateUseCase {

    private final QueryAdmissionSpi queryAdmissionSpi;
    private final CommandScoreSpi commandScoreSpi;
    private final SchoolScoreService scoreService;

    public void calculateUpdateScore(Long id) {
        Admission admission = queryAdmissionSpi.findById(id)
                .orElseThrow();
        Score score = admission.getScore();
        if (score instanceof GedScore gedScore) {
            int totalScore = 0;
            for (GedGrade gedGrade : gedScore.getGedScores()) {
                totalScore += convertGedPoint(gedGrade.getPoint().getValue());
            }

            score.setGeneralScore(Math.round((20 * ((float) totalScore / 6)) * 1000) / 1000.0);
            score.setSpecialScore(Math.round((14 * ((float) totalScore / 6)) * 1000) / 1000.0);
        }
        else {
            // safe under cast
            SchoolScore schoolScore = (SchoolScore) score;
            List<SchoolGrade> grades = schoolScore.getSchoolGrades();
            if (grades.isEmpty()) {
                score.setGeneralScore(0.0);
                score.setSpecialScore(0.0);
            }
            else {
                double gradeScore = scoreService.calculateScore(grades, schoolScore.getAttendancePoints(), true);
                score.setGeneralScore(Math.round((0.8 * gradeScore) * 1000) / 1000.0);
                score.setSpecialScore(Math.round((0.5 * gradeScore) * 1000) / 1000.0);
            }
        }
        commandScoreSpi.save(score);
    }

    private int convertGedPoint(double score) {
        if (score <= 100 && score >= 98) {
            return 5;
        }
        else if (score < 98 && score >= 94) {
            return 4;
        }
        else if (score < 94 && score >= 90) {
            return 3;
        }
        else if (score < 90 && score >= 86) {
            return 2;
        }
        else if (score < 86 && score >= 1) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
