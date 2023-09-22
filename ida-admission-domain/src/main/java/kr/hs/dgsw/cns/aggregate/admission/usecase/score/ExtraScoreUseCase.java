package kr.hs.dgsw.cns.aggregate.admission.usecase.score;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.Admission;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.SchoolScore;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.Score;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint.Grade;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.constraint.Semester;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.AttendancePoint;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.LeaderShipPoint;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.VolunteerPoint;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.admission.QueryAdmissionSpi;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.score.CommandScoreSpi;
import kr.hs.dgsw.cns.aggregate.school.domain.School;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@UseCase
@RequiredArgsConstructor
public class ExtraScoreUseCase {

    private final QueryAdmissionSpi queryAdmissionSpi;
    private final CommandScoreSpi commandScoreSpi;

    public int getAbsenceNumber(List<AttendancePoint> points) {
        // 무단, 지각, 조퇴, 결과
        int totalOtherCount = points.stream().mapToInt(AttendancePoint::getTardiness).sum()
                + points.stream().mapToInt(AttendancePoint::getEarlyLeave).sum()
                + points.stream().mapToInt(AttendancePoint::getSkipped).sum();
        int absenceCount = points.stream().mapToInt(AttendancePoint::getAbsence).sum();
        return (absenceCount + (totalOtherCount / 3));
    }

    public void setAbsenceNumber(Long id) {
        Admission admission = queryAdmissionSpi.findById(id)
                .orElseThrow();
        Score score = admission.getScore();
        if (!(score instanceof SchoolScore schoolScore)) {
             score.setAbsence(0.0);
             return;
        }

        int absenceCount = getAbsenceNumber(schoolScore.getAttendancePoints());
        if (absenceCount >= 5) {
            score.setAbsence(0.0);
        }
        else {
            score.setAbsence(10.0 - (absenceCount * 2));
        }

        commandScoreSpi.save(score);
    }

    public double calculateVolunteerScore(List<VolunteerPoint> volunteerPoints) {
        AtomicReference<Double> volunteerScoreSum = new AtomicReference<>(0.0);

        volunteerPoints.forEach(it -> {
            //1 학년 또는 2학년
            if (it.getGrade().equals(Grade.FIRST) || it.getGrade().equals(Grade.SECOND)) {
                if (it.getHour() > 5) {
                    volunteerScoreSum.updateAndGet(v ->  (v + 2.4));
                } else if (it.getHour() >= 3) {
                    volunteerScoreSum.updateAndGet(v -> (v + 2.0));
                } else {
                    volunteerScoreSum.updateAndGet(v -> (v + 1.6));
                }
            // 3학년
            } else if (it.getGrade().equals(Grade.THIRD)) {
                if (it.getHour() > 10) {
                    volunteerScoreSum.updateAndGet(v -> (v + 1.2));
                } else if (it.getHour() >= 7) {
                    volunteerScoreSum.updateAndGet(v -> (v + 1.0));
                } else {
                    volunteerScoreSum.updateAndGet(v -> (v + 0.8));
                }
            }
        });

        return volunteerScoreSum.get();
    }

    public void setVolunteerScore(Long id) {
        Admission admission = queryAdmissionSpi.findById(id)
                .orElseThrow();
        Score score = admission.getScore();

        if (!(score instanceof SchoolScore schoolScore)) {
            score.setVolunteer(0.0);
            return;
        }

        score.setVolunteer(
                calculateVolunteerScore(schoolScore.getVolunteerPoints())
        );
    }

    public double calculateAdditionalScore(double leaderShipCount, SchoolScore schoolScore) {
        double leaderShipScore = leaderShipCount * 0.5;
        if (leaderShipScore > 2.0) {
            leaderShipScore = 0.0;
        }

        double prizeScore = schoolScore.getPrize().getValue() * 1.0;
        if (prizeScore > 2.0) {
            prizeScore = 2.0;
        }

        return leaderShipScore + prizeScore;
    }

    public double countLeaderShip(List<LeaderShipPoint> leaderShipPoints) {
        AtomicReference<Double> leaderShipScoreSum = new AtomicReference<>(0.0);

        leaderShipPoints.forEach(it -> {
            if (it.isCheck()) {
                leaderShipScoreSum.updateAndGet(v -> v+0.5);
            }
        });

        return leaderShipScoreSum.get() >= 2.0 ? 2.0 : leaderShipScoreSum.get();
    }


    public void setAdditional(Long id) {
        Admission admission = queryAdmissionSpi.findById(id)
                .orElseThrow();
        Score score = admission.getScore();
        if (!(score instanceof SchoolScore schoolScore)) {
            score.setAdditional(0.0);
            return;
        }

        double leaderShip = countLeaderShip(schoolScore.getLeaderShipPoints());
        schoolScore.setAdditional(calculateAdditionalScore(leaderShip, schoolScore));
        commandScoreSpi.save(schoolScore);
    }

    public void setAdditionalScore(Long id) {
        setAbsenceNumber(id);
        setAdditional(id);
        setVolunteerScore(id);
    }
}
