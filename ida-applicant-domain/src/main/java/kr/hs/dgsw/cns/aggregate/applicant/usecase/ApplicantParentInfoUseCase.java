package kr.hs.dgsw.cns.aggregate.applicant.usecase;

import kr.hs.dgsw.cns.aggregate.applicant.domain.Applicant;
import kr.hs.dgsw.cns.aggregate.applicant.domain.value.Privacy;
import kr.hs.dgsw.cns.aggregate.applicant.dto.parent.ParentResponse;
import kr.hs.dgsw.cns.aggregate.applicant.dto.parent.UpdateParentRequest;
import kr.hs.dgsw.cns.aggregate.applicant.spi.query.CommandApplicantSpi;
import kr.hs.dgsw.cns.aggregate.applicant.spi.query.QueryApplicantSpi;
import kr.hs.dgsw.cns.domain.MemberId;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ApplicantParentInfoUseCase {

    private final QueryApplicantSpi queryApplicantSpi;
    private final CommandApplicantSpi commandApplicantSpi;

    public void updateParentInfo(MemberId memberId, UpdateParentRequest parentRequest) {
        Applicant applicant = queryApplicantSpi.findById(memberId)
                .orElseThrow();
        applicant.updatePrivacy(updateParentAndAddress(applicant.getPrivacy(),
                parentRequest));
        commandApplicantSpi.save(applicant);
    }

    public ParentResponse findParentInfo(MemberId memberId) {
        Applicant applicant = queryApplicantSpi.findById(memberId)
                .orElseThrow();
        return new ParentResponse(applicant.getPrivacy().getParentInfo(),
                applicant.getPrivacy().getAddress());
    }

    private Privacy updateParentAndAddress(Privacy privacy, UpdateParentRequest request) {
        privacy.updateAddress(request.toAddress());
        privacy.updateParentInfo(request.toParent());
        return privacy;
    }
}
