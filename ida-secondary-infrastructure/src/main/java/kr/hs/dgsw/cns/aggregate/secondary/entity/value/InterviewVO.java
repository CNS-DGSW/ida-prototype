package kr.hs.dgsw.cns.aggregate.secondary.entity.value;

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
public class InterviewVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4376468113460154080L;

    @Column(name = "study_score")
    private int studyScore;

    @Column(name = "computing_score")
    private int computingScore;

}
