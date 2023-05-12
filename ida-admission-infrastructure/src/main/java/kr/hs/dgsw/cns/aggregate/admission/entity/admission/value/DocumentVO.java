package kr.hs.dgsw.cns.aggregate.admission.entity.admission.value;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
@Embeddable
@AllArgsConstructor @NoArgsConstructor
public class DocumentVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7910862738942223097L;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Column(name = "study_plan", columnDefinition = "TEXT")
    private String studyPlan;

}
