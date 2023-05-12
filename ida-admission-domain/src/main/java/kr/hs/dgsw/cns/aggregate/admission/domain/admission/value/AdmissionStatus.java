package kr.hs.dgsw.cns.aggregate.admission.domain.admission.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class AdmissionStatus {

    // 제출 여부
    private boolean submission;

    // 우편 도착
    private boolean mailArrival;

    // 검토 여부
    private boolean review;

    // 제출 시각
    private LocalDateTime submissionTime;
    
    // 제출 확장 여부
    private boolean confirmation;

}
