package kr.hs.dgsw.cns.aggregate.admission.entity.admission.value;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
@Embeddable
@AllArgsConstructor @NoArgsConstructor
public class AdmissionStatusVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 5234503036341603917L;

    private boolean submission;

    private boolean mailArrival;

    private boolean review;

    private LocalDateTime submissionTime;

    private boolean confirmation;

}
