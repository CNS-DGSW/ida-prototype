package kr.hs.dgsw.cns.aggregate.secondary.entity.value;

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
public class AptitudeVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1523035977121371981L;

    private int score;


}
