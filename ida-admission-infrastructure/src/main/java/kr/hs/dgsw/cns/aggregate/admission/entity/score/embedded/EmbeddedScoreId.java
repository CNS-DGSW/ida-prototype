package kr.hs.dgsw.cns.aggregate.admission.entity.score.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmbeddedScoreId implements Serializable {

    @Serial
    private static final long serialVersionUID = -5672680298782705400L;

    @Column(name = "score_id")
    private Long id;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EmbeddedScoreId embeddedScoreId = (EmbeddedScoreId) obj;
        return Objects.equals(id, embeddedScoreId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static EmbeddedScoreId of(long id) {
        return new EmbeddedScoreId(id);
    }
}
