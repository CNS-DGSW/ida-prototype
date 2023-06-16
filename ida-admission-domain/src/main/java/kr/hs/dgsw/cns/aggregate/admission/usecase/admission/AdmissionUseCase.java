package kr.hs.dgsw.cns.aggregate.admission.usecase.admission;

import kr.hs.dgsw.cns.aggregate.admission.domain.admission.Admission;
import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.AdmissionApplicant;
import kr.hs.dgsw.cns.aggregate.admission.domain.admission.value.constraint.AdmissionType;
import kr.hs.dgsw.cns.aggregate.admission.dto.admission.AdmissionTypeDto;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.admission.CommandAdmissionSpi;
import kr.hs.dgsw.cns.aggregate.admission.spi.query.admission.QueryAdmissionSpi;
import kr.hs.dgsw.cns.domain.MemberId;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AdmissionUseCase {

    private final QueryAdmissionSpi queryAdmissionSpi;
    private final CommandAdmissionSpi commandAdmissionSpi;

    /**
     * 2022년도 기준, 전형 선택을 이후 점수 계산 등을 처리하므로
     * 전형 선택과 동시에 입학원서 엔티티를 등록한다.
     */
    public void updateAdmissionType(MemberId id, AdmissionTypeDto request) {
        Admission admission = queryAdmissionSpi.findById(id.getId())
                .orElse(Admission.builder()
                        .build());
        admission.updateType(new AdmissionApplicant(id, request.getType()));
        commandAdmissionSpi.save(admission);
    }

    public AdmissionTypeDto findAdmissionType(Long id) {
        Admission admission = queryAdmissionSpi.findById(id)
                .orElse(null);
        return new AdmissionTypeDto((admission == null) ? AdmissionType.NONE : admission.getApplicant().getType());
    }
}
