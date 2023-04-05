package kr.hs.dgsw.cns.aggregate.applicant.controller;

import kr.hs.dgsw.cns.aggregate.applicant.dto.parent.ParentResponse;
import kr.hs.dgsw.cns.aggregate.applicant.dto.parent.UpdateParentRequest;
import kr.hs.dgsw.cns.aggregate.applicant.usecase.ApplicantParentInfoUseCase;
import kr.hs.dgsw.cns.global.auth.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applicant/parent")
public class ParentController {

    private final ApplicantParentInfoUseCase parentInfoUseCase;

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateParentInfo(@AuthenticationPrincipal AuthUser authUser,
                                 @RequestBody UpdateParentRequest parentRequest) {
        parentInfoUseCase.updateParentInfo(authUser.getId(), parentRequest);
    }

    @GetMapping
    public ParentResponse findParentInfo(@AuthenticationPrincipal AuthUser authUser) {
        return parentInfoUseCase.findParentInfo(authUser.getId());
    }
}
