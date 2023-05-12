package kr.hs.dgsw.cns.aggregate.admission.entity.score;

import jakarta.persistence.*;
import kr.hs.dgsw.cns.aggregate.admission.entity.score.embedded.EmbeddedScoreId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DiscriminatorColumn
@Table(name = "score")
@Access(AccessType.FIELD)
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractScore {

    @EmbeddedId
    private EmbeddedScoreId id;

}
