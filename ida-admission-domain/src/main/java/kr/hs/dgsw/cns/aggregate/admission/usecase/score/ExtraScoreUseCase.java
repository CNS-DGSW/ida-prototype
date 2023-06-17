package kr.hs.dgsw.cns.aggregate.admission.usecase.score;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.Admission;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.SchoolScore;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.Score;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.AttendancePoint;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.admission.QueryAdmissionSpi;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.score.CommandScoreSpi;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
        // TODO: ged
        Admission admission = queryAdmissionSpi.findById(id)
                .orElseThrow();
        Score score = admission.getScore();
        if (!(score instanceof SchoolScore schoolScore)) {
            throw new RuntimeException();
        }

        int absenceCount = getAbsenceNumber(schoolScore.getAttendancePoints());
        if (absenceCount >= 5) {
            score.setAbsence(0.0);
        }
        else {
            score.setAbsence(10.0 - (absenceCount * 2));
        }

        // check is ged
//        if (isGed) score.setAbsence(0.0);
        commandScoreSpi.save(score);
    }

//    public double calculateVolunteerScore(List<AttendancePoint> attendancePoints, School school) {
//          // there no such as school domain, you should make school
//    }

//    public void setVolunteerScore(Long id) {
//          // there no such as school domain, you should make school
//    }

    public double calculateAdditionalScore(int leaderShipCount, SchoolScore schoolScore) {
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

//    public int countLeaderShip(List<AttendancePoint> attendancePoints, School school) {
//        // there no such as school domain, you should make school
//    }

    public void setAdditional(Long id) {
        Admission admission = queryAdmissionSpi.findById(id)
                .orElseThrow();
        Score score = admission.getScore();
        if (!(score instanceof SchoolScore schoolScore)) {
            throw new RuntimeException();
        }

//        int leaderShip = countLeaderShip();
//        schoolScore.setAdditional(calculateAdditionalScore(leaderShip, schoolScore.getAttendancePoints()));
        commandScoreSpi.save(schoolScore);
    }

    public void setAdditionalScore(Long id) {
        setAbsenceNumber(id);
        setAdditional(id);
//        setVolunteerScore(id);
    }
}
