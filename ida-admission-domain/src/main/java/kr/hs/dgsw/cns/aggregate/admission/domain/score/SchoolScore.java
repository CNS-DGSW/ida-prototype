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

    private final List<SchoolGrade> schoolGrades;

    private final List<AttendancePoint> attendancePoints;

    private final List<VolunteerPoint> volunteerPoints;

    private final Point prize;

    public SchoolScore(Long id) {
        super(id);
        this.schoolGrades = new LinkedList<>();
        this.attendancePoints = new LinkedList<>();
        this.volunteerPoints = new LinkedList<>();
        this.prize = Point.of((short) 0);
    }

    public SchoolScore(Long id, List<SchoolGrade> schoolGrades,
                       List<AttendancePoint> attendancePoints, List<VolunteerPoint> volunteerPoints,
                       Point prize) {
        super(id);
        this.schoolGrades = schoolGrades;
        this.attendancePoints = attendancePoints;
        this.volunteerPoints = volunteerPoints;
        this.prize = prize;
    }
}
