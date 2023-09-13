package kr.hs.dgsw.cns.aggregate.secondary.entity.value;

import jakarta.persistence.Embeddable;
import kr.hs.dgsw.cns.aggregate.admission.entity.admission.AdmissionEntity;
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
public class ExamineeVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2238420653727913619L;

    private AdmissionEntity admission;

    private Long examCode;
}
