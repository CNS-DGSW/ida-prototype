package kr.hs.dgsw.cns.aggregate.admission.entity.score;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.embedded.EmbeddedScoreId;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.value.GedGradeVO;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

/**
 * 점수를 최소로 가지고 있는 (검정고시) 전형 점수 엔티티
 */
@Entity
@Getter
@DiscriminatorValue("Q")
@Table(name = "qualification_score")
public class QualificationScoreEntity extends AbstractScore {

    @OrderColumn(name = "ged_idx")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "ged_grade", joinColumns = @JoinColumn(name = "score_id"))
    private List<GedGradeVO> gedGrades;

    public QualificationScoreEntity(EmbeddedScoreId id, List<GedGradeVO> gedGrades) {
        super(id);
        this.gedGrades = gedGrades;
    }

    public QualificationScoreEntity(EmbeddedScoreId id) {
        super(id);
        this.gedGrades = new LinkedList<>();
    }

    protected QualificationScoreEntity() {

    }
}
