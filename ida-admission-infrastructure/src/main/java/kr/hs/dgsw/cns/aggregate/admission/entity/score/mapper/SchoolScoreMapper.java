package kr.hs.dgsw.cns.aggregate.admission.entity.score.mapper;

import kr.hs.dgsw.cns.aggregate.admission.domain.score.SchoolScore;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.ScoreId;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.AttendancePoint;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.SchoolGrade;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.grade.VolunteerPoint;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.SchoolScoreEntity;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.embedded.EmbeddedScoreId;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.value.AttendanceVO;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.value.SchoolGradeVO;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.value.VolunteerVO;
import kr.hs.dgsw.cns.global.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SchoolScoreMapper implements Mapper<SchoolScore, SchoolScoreEntity> {

    private static final SchoolGradeMapper GRADE_MAPPER = new SchoolGradeMapper();
    private static final AttendanceMapper ATTENDANCE_MAPPER = new AttendanceMapper();
    private static final VolunteerMapper VOLUNTEER_MAPPER = new VolunteerMapper();

    @Override
    public SchoolScoreEntity domainToEntity(SchoolScore domain) {
        return new SchoolScoreEntity(
                EmbeddedScoreId.of(domain.getId().getId()),
                GRADE_MAPPER.domainToEntity(domain.getSchoolGrades()),
                ATTENDANCE_MAPPER.domainToEntity(domain.getAttendancePoints()),
                VOLUNTEER_MAPPER.domainToEntity(domain.getVolunteerPoints()),
                domain.getPrize()
        );
    }

    @Override
    public SchoolScore entityToDomain(SchoolScoreEntity entity) {
        return new SchoolScore(
                new ScoreId(entity.getId().getId()),
                GRADE_MAPPER.entityToDomain(entity.getSchoolGrades()),
                ATTENDANCE_MAPPER.entityToDomain(entity.getAttendances()),
                VOLUNTEER_MAPPER.entityToDomain(entity.getVolunteers()),
                entity.getPrize()
        );
    }

    static class SchoolGradeMapper implements Mapper<List<SchoolGrade>, List<SchoolGradeVO>> {
        @Override
        public List<SchoolGradeVO> domainToEntity(List<SchoolGrade> domain) {
            return domain.stream().map(schoolGrade -> new SchoolGradeVO(
                    schoolGrade.getGrade(), schoolGrade.getSemester(),
                    schoolGrade.getSubject(), schoolGrade.isDoubled(),
                    schoolGrade.getAchieveLevel()
            )).collect(Collectors.toList());
        }

        @Override
        public List<SchoolGrade> entityToDomain(List<SchoolGradeVO> entity) {
            return entity.stream().map(vo -> new SchoolGrade(
                    vo.getGrade(), vo.getSemester(), vo.getSubject(),
                    vo.isDoubled(), vo.getPoint()
            )).collect(Collectors.toList());
        }
    }

    static class AttendanceMapper implements Mapper<List<AttendancePoint>, List<AttendanceVO>> {
        @Override
        public List<AttendanceVO> domainToEntity(List<AttendancePoint> domain) {
            return domain.stream().map(attendancePoint -> new AttendanceVO(
                    attendancePoint.getGrade(), attendancePoint.getSemester(),
                    attendancePoint.getAbsence(), attendancePoint.getTardiness(),
                    attendancePoint.getEarlyLeave(), attendancePoint.getSkipped()
            )).collect(Collectors.toList());
        }

        @Override
        public List<AttendancePoint> entityToDomain(List<AttendanceVO> entity) {
            return entity.stream().map(vo -> new AttendancePoint(
                    vo.getGrade(), vo.getSemester(), vo.getAbsence(),
                    vo.getTardiness(), vo.getEarlyLeave(), vo.getSkipped()
            )).collect(Collectors.toList());
        }
    }

    static class VolunteerMapper implements Mapper<List<VolunteerPoint>, List<VolunteerVO>> {
        @Override
        public List<VolunteerVO> domainToEntity(List<VolunteerPoint> domain) {
            return domain.stream().map(volunteerPoint -> new VolunteerVO(
                    volunteerPoint.getGrade(), volunteerPoint.getPoint()
            )).collect(Collectors.toList());
        }

        @Override
        public List<VolunteerPoint> entityToDomain(List<VolunteerVO> entity) {
            return entity.stream().map(vo -> new VolunteerPoint(
                    vo.getGrade(), vo.getPoint()
            )).collect(Collectors.toList());
        }
    }
}
