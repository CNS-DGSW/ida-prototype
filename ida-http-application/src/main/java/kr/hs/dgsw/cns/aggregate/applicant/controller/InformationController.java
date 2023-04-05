package kr.hs.dgsw.cns.aggregate.applicant.controller;

import kr.hs.dgsw.cns.aggregate.applicant.dto.info.InformationResponse;
import kr.hs.dgsw.cns.aggregate.applicant.dto.info.UpdateInformationRequest;
import kr.hs.dgsw.cns.aggregate.applicant.usecase.ApplicantInfoUseCase;
import kr.hs.dgsw.cns.global.auth.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applicant/information")
public class InformationController {

    private final ApplicantInfoUseCase applicantInfoUseCase;

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInfo(@AuthenticationPrincipal AuthUser member,
                           @RequestBody UpdateInformationRequest informationDto) {
        applicantInfoUseCase.updateInformation(member.getId(), informationDto);
    }

    @GetMapping
    public InformationResponse findInfo(@AuthenticationPrincipal AuthUser member) {
        return applicantInfoUseCase.findInformation(member.getId());
    }
}
