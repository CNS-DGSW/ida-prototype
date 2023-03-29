package kr.hs.dgsw.cns.aggregate.applicant.entity;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Point;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade.Attendance;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade.SchoolGrade;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade.Volunteer;
import kr.hs.dgsw.cns.aggregate.converter.PointConverter;
import lombok.Getter;

import java.util.List;

/**
 * 검정고시를 제외한 그 외의 전형들이 가지게 되는 전형
 */
@Entity
@Getter
@DiscriminatorValue("S")
public class SchoolScore extends Score {

    @OrderColumn(name = "school_idx")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "school_grade", joinColumns = @JoinColumn(name = "score_id"))
    private List<SchoolGrade> schoolGrades;

    @OrderColumn(name = "attendance_idx")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "attendance", joinColumns = @JoinColumn(name = "score_id"))
    private List<Attendance> attendances;

    @OrderColumn(name = "volunteer_idx")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "volunteer", joinColumns = @JoinColumn(name = "score_id"))
    private List<Volunteer> volunteers;

    @Convert(converter = PointConverter.class)
    private Point prize;

    public SchoolScore(Long id, List<SchoolGrade> schoolGrades, List<Attendance> attendances,
                       List<Volunteer> volunteers, Point prize) {
        super(id);
        this.schoolGrades = schoolGrades;
        this.attendances = attendances;
        this.volunteers = volunteers;
        this.prize = prize;
    }

    public SchoolScore(Long id) {
        super(id);
    }

    public SchoolScore() {
    }
}
