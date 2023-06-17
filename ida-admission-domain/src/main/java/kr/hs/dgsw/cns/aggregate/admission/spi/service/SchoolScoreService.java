package kr.hs.dgsw.cns.aggregate.admission.spi.service;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.AttendancePoint;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.SchoolGrade;

import java.util.List;

public interface SchoolScoreService {

    double calculateScore(List<SchoolGrade> scores, List<AttendancePoint> attendancePoints,
                          boolean isGraduated);

}
