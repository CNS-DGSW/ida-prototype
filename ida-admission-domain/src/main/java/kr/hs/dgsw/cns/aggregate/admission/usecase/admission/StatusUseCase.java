package kr.hs.dgsw.cns.aggregate.admission.usecase.admission;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.Admission;
import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.AdmissionStatus;
import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.constraint.Progress;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.admission.CommandAdmissionSpi;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.admission.QueryAdmissionSpi;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import lombok.RequiredArgsConstructor;

/**
 * 제출 상황과 상태를 변경하고 조회하는 유즈케이스
 * This usecase will be used on admin!
 */
@UseCase
@RequiredArgsConstructor
public class StatusUseCase {

    private final QueryAdmissionSpi queryAdmissionSpi;
    private final CommandAdmissionSpi commandAdmissionSpi;

    /**
     * 원서 제출 현황 수정
     * @param id 원서 id
     * @param status 수정될 제출 현황
     * @see AdmissionStatus
     */
    public void updateStatus(Long id, AdmissionStatus status) {
        Admission admission = queryAdmissionSpi.findById(id)
                .orElseThrow();
        admission.updateStatus(status);
        commandAdmissionSpi.save(admission);
    }

    /**
     * 지원생 현황 수정
     * @param id 원서 id
     * @param progress 수정될 현황
     */
    public void updateProgress(Long id, Progress progress) {
        Admission admission = queryAdmissionSpi.findById(id)
                .orElseThrow();
        admission.updateProgress(progress);
        commandAdmissionSpi.save(admission);
    }
}
