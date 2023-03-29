package kr.hs.dgsw.cns.aggregate.applicant.entity;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.applicant.entity.value.grade.GedGrade;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

/**
 * 점수를 최소로 가지고 있는 (검정고시) 전형 점수 엔티티
 */
@Entity
@Getter
@DiscriminatorValue("Q")
public class QualificationScore extends Score {

    @OrderColumn(name = "ged_idx")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "ged_grade", joinColumns = @JoinColumn(name = "score_id"))
    private List<GedGrade> gedGrades;

    public QualificationScore(Long id, List<GedGrade> gedGrades) {
        super(id);
        this.gedGrades = gedGrades;
    }

    public QualificationScore(Long id) {
        super(id);
        this.gedGrades = new LinkedList<>();
    }

    protected QualificationScore() {

    }
}
