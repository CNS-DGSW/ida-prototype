package kr.hs.dgsw.cns.aggregate.admission.domain.score;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.Point;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.AttendancePoint;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.SchoolGrade;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.VolunteerPoint;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class SchoolScore extends Score {

    private List<SchoolGrade> schoolGrades;

    private List<AttendancePoint> attendancePoints;

    private List<VolunteerPoint> volunteerPoints;

    private Point prize;

    public void setSchoolGrades(List<SchoolGrade> schoolGrades) {
        this.schoolGrades = schoolGrades;
    }

    public void setAttendancePoints(List<AttendancePoint> attendancePoints) {
        this.attendancePoints = attendancePoints;
    }

    public void setVolunteerPoints(List<VolunteerPoint> volunteerPoints) {
        this.volunteerPoints = volunteerPoints;
    }

    public void setPrize(Point prize) {
        this.prize = prize;
    }

    public SchoolScore(ScoreId id) {
        super(id);
        this.schoolGrades = new LinkedList<>();
        this.attendancePoints = new LinkedList<>();
        this.volunteerPoints = new LinkedList<>();
        this.prize = Point.of((short) 0);
    }

    public SchoolScore(ScoreId id, List<SchoolGrade> schoolGrades,
                       List<AttendancePoint> attendancePoints, List<VolunteerPoint> volunteerPoints,
                       Point prize) {
        super(id);
        this.schoolGrades = schoolGrades;
        this.attendancePoints = attendancePoints;
        this.volunteerPoints = volunteerPoints;
        this.prize = prize;
    }
}
