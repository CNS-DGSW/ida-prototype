package kr.hs.dgsw.cns.aggregate.applicant.controller;

import kr.hs.dgsw.cns.aggregate.applicant.dto.InformationResponse;
import kr.hs.dgsw.cns.aggregate.applicant.dto.UpdateInformationRequest;
import kr.hs.dgsw.cns.aggregate.applicant.usecase.InformationUseCase;
import kr.hs.dgsw.cns.global.auth.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applicant/information")
public class InformationController {

    private final InformationUseCase informationUseCase;

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInfo(@AuthenticationPrincipal AuthUser member,
                           @RequestBody UpdateInformationRequest informationDto) {
        informationUseCase.updateInformation(member.getId(), informationDto);
    }

    @GetMapping
    public InformationResponse findInfo(@AuthenticationPrincipal AuthUser member) {
        return informationUseCase.findInformation(member.getId());
    }
}
