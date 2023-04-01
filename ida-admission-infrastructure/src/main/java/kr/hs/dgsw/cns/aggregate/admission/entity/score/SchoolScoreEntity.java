package kr.hs.dgsw.cns.aggregate.admission.entity.score;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.admission.converter.PointConverter;
import kr.hs.dgsw.cns.aggregate.admission.domain.score.value.Point;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.value.AttendanceVO;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.value.SchoolGradeVO;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.value.VolunteerVO;
import lombok.Getter;

import java.util.List;

/**
 * 검정고시를 제외한 그 외의 전형들이 가지게 되는 전형
 */
@Entity
@Getter
@DiscriminatorValue("S")
public class SchoolScoreEntity extends AbstractScore {

    @OrderColumn(name = "school_idx")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "school_grade", joinColumns = @JoinColumn(name = "score_id"))
    private List<SchoolGradeVO> schoolGrades;

    @OrderColumn(name = "attendance_idx")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "attendance", joinColumns = @JoinColumn(name = "score_id"))
    private List<AttendanceVO> attendances;

    @OrderColumn(name = "volunteer_idx")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "volunteer", joinColumns = @JoinColumn(name = "score_id"))
    private List<VolunteerVO> volunteers;

    @Convert(converter = PointConverter.class)
    private Point prize;

    public SchoolScoreEntity(Long id, List<SchoolGradeVO> schoolGradeVOS, List<AttendanceVO> attendanceVOS,
                             List<VolunteerVO> volunteerVOS, Point prize) {
        super(id);
        this.schoolGrades = schoolGradeVOS;
        this.attendances = attendanceVOS;
        this.volunteers = volunteerVOS;
        this.prize = prize;
    }

    public SchoolScoreEntity(Long id) {
        super(id);
    }

    public SchoolScoreEntity() {
    }
}
