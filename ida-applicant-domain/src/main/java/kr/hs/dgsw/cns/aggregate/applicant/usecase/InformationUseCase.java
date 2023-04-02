package kr.hs.dgsw.cns.aggregate.applicant.usecase;

import kr.hs.dgsw.cns.aggregate.applicant.domain.Applicant;
import kr.hs.dgsw.cns.aggregate.applicant.dto.UpdateInformationDto;
import kr.hs.dgsw.cns.aggregate.applicant.spi.query.CommandApplicantSpi;
import kr.hs.dgsw.cns.aggregate.applicant.spi.query.QueryApplicantSpi;
import kr.hs.dgsw.cns.domain.MemberId;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class InformationUseCase {

    private final QueryApplicantSpi queryApplicantSpi;
    private final CommandApplicantSpi commandApplicantSpi;

    public void updateInformation(MemberId memberId, UpdateInformationDto informationRequest) {
        Applicant applicant = queryApplicantSpi.findById(memberId)
                .orElseThrow();
        applicant.updatePrivacy(informationRequest.toDomainVO());
        commandApplicantSpi.save(applicant);
    }

    public UpdateInformationDto findInformation(MemberId memberId) {
        return new UpdateInformationDto(queryApplicantSpi.findById(memberId)
                .orElseThrow().getPrivacy());
    }
}
