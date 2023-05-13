package kr.hs.dgsw.cns.aggregate.admission.entity.admission.embedd;

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
public class EmbeddedApplicantId implements Serializable {
    @Serial
    private static final long serialVersionUID = 4984355451505530358L;

    private Long applicantId;

}
