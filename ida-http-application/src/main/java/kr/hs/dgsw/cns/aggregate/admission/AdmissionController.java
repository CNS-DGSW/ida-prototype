package kr.hs.dgsw.cns.aggregate.admission;

import kr.hs.dgsw.cns.aggregate.admission.dto.admission.AdmissionTypeDto;
import kr.hs.dgsw.cns.aggregate.admission.dto.document.DocumentResponse;
import kr.hs.dgsw.cns.aggregate.admission.dto.document.UpdateDocumentRequest;
import kr.hs.dgsw.cns.aggregate.admission.usecase.admission.AdmissionUseCase;
import kr.hs.dgsw.cns.aggregate.admission.usecase.admission.DocumentUseCase;
import kr.hs.dgsw.cns.global.auth.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admission")
public class AdmissionController {

    private final DocumentUseCase documentUseCase;
    private final AdmissionUseCase admissionUseCase;

    @PutMapping("/document")
    public void updateDocument(@AuthenticationPrincipal AuthUser authUser,
                               @RequestBody UpdateDocumentRequest request) {
        documentUseCase.updateDocument(authUser.getId().getId(), request);
    }

    @GetMapping("/document")
    public DocumentResponse getDocument(@AuthenticationPrincipal AuthUser authUser) {
        return documentUseCase.findDocument(authUser.getId().getId());
    }

    @PutMapping("/type")
    public void updateType(@AuthenticationPrincipal AuthUser authUser,
                           @RequestBody AdmissionTypeDto dto) {
        admissionUseCase.updateAdmissionType(authUser.getId(), dto);
    }

    @GetMapping("/type")
    public AdmissionTypeDto getType(@AuthenticationPrincipal AuthUser authUser) {
        return admissionUseCase.findAdmissionType(authUser.getId().getId());
    }
}
