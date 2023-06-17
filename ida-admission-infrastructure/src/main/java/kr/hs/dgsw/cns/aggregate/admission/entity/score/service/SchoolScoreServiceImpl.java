package kr.hs.dgsw.cns.aggregate.admission.entity.score.service;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint.Grade;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint.Semester;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.AttendancePoint;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.SchoolGrade;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.util.ConvertGradeRecord;
import kr.hs.dgsw.cns.aggregate.admission.spi.service.SchoolScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class SchoolScoreServiceImpl implements SchoolScoreService {

    private final ConvertGradeRecord gradeRecord;

    // TODO: I need to migration ScoreServiceImpl

    @Override
    // calcGradeScore
    public double calculateScore(List<SchoolGrade> scores, List<AttendancePoint> attendancePoints, boolean isGraduated) {
        AtomicReference<Double> gradeScore = new AtomicReference<>(0.0);
        double[] subjectScoreSum = new double[6];
        double[] gradeCount = new double[6];
        Arrays.fill(subjectScoreSum, 0.0);
        Arrays.fill(gradeCount, 0.0);

        AtomicReference<Double> freeScore = new AtomicReference<>(100.0);

        scores.forEach(it -> {
            double mulScore = (it.isDoubled()) ? 2.0 : 1.0;
            double score = gradeRecord.getValue(it.getAchieveLevel()) * mulScore;
            int count; int subjectSum;

            if (it.getGrade() == Grade.FIRST) {
                if (it.getSemester() == Semester.FIRST) {
                    count = 0; subjectSum = 0;
                }
                else {
                    count = 1; subjectSum = 1;
                }
            }
            else if (it.getGrade() == Grade.SECOND) {
                if (it.getSemester() == Semester.FIRST) {
                    count = 2; subjectSum = 2;
                }
                else {
                    count = 3; subjectSum = 3;
                }
            }
            else {
                if (it.getSemester() == Semester.FIRST) {
                    count = 4; subjectSum = 4;
                }
                else {
                    count = 5; subjectSum = 5;
                }
            }

            gradeCount[count] += mulScore;
            subjectScoreSum[subjectSum] += score;
        });

        attendancePoints.forEach(it -> {
            // 1학년 1학기
            if (it.getGrade() == Grade.FIRST && it.getSemester() == Semester.FIRST && gradeCount[0] == 0.0) {
                freeScore.updateAndGet(v -> v - 10);
            }
            else {
                gradeScore.updateAndGet(v -> (v + 2 * subjectScoreSum[0] / gradeCount[0]));
            }

            // 1학년 2학기
            if (it.getGrade() == Grade.FIRST && it.getSemester() == Semester.SECOND && gradeCount[1] == 0.0) {
                freeScore.updateAndGet(v -> v - 10);
            }
            else {
                gradeScore.updateAndGet(v -> (v + 2 * subjectScoreSum[1] / gradeCount[1]));
            }

            // 2학년 1학기
            if (it.getGrade() == Grade.SECOND && it.getSemester() == Semester.FIRST && gradeCount[2] == 0.0) {
                freeScore.updateAndGet(v -> v - 15);
            }
            else {
                gradeScore.updateAndGet(v -> (v + 3 * subjectScoreSum[2] / gradeCount[2]));
            }

            // 2학년 2학기
            if (it.getGrade() == Grade.SECOND && it.getSemester() == Semester.SECOND && gradeCount[3] == 0.0) {
                freeScore.updateAndGet(v -> v - 15);
            }
            else {
                gradeScore.updateAndGet(v -> (v + 3 * subjectScoreSum[3] / gradeCount[3]));
            }

            if (isGraduated) {
                // 3학년 1학기
                if (it.getGrade() == Grade.THIRD && it.getSemester() == Semester.FIRST && gradeCount[4] == 0.0) {
                    freeScore.updateAndGet(v -> v - 25);
                }
                else {
                    gradeScore.updateAndGet(v -> (v + 5 * subjectScoreSum[4] / gradeCount[4]));
                }

                // 3학년 2학기
                if (it.getGrade() == Grade.THIRD && it.getSemester() == Semester.SECOND && gradeCount[5] == 0.0) {
                    freeScore.updateAndGet(v -> v - 25);
                }
                else {
                    gradeScore.updateAndGet(v -> (v + 5 * subjectScoreSum[5] / gradeCount[5]));
                }
            }
            else {
                // 3학년 1학기
                if (it.getGrade() == Grade.THIRD && it.getSemester() == Semester.FIRST && gradeCount[4] == 0.0) {
                    freeScore.updateAndGet(v -> v - 50);
                }
                else {
                    gradeScore.updateAndGet(v -> (v + 10 * subjectScoreSum[4] / gradeCount[4]));
                }
            }

        });

        return (100 / freeScore.get()) * gradeScore.get();
    }
}
