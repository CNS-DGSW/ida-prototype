package kr.hs.dgsw.cns.aggregate.applicant.controller;

import kr.hs.dgsw.cns.aggregate.applicant.dto.photo.PhotoResponse;
import kr.hs.dgsw.cns.aggregate.applicant.usecase.ApplicantPhotoUseCase;
import kr.hs.dgsw.cns.global.auth.AuthUser;
import kr.hs.dgsw.cns.global.dto.FileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applicant/photo")
public class PhotoController {

    private final ApplicantPhotoUseCase photoUseCase;

    /**
     * 단 하나의 사진만을 받을 것이다.
     * 해당 사진의 용량은 2MB이며, 이를 저장할 수 있는 방법은
     * 여러 방법이 있다.
     * 하지만 방법마다 정의를 한다면, 강한 결합이 되므로
     * 이를 고수준 모듈과 저수준 모듈을 적절히 섞어서 해결해야한다.
     * <p>
     * P.S. 파일의 용량 제한을 하고 싶다면 application.yml 혹은
     * 빈 등록으로 설정할 수 있다.
     * application.yml 설정 방식은 다음과 같다.
     * <pre>
     *     spring.servlet.multipart.maxFileSize=50MB
     *     spring.servlet.multipart.maxRequestSize=50MB
     * </pre>
     * @see org.springframework.web.multipart.MultipartResolver
     * @param authUser 사용자
     * @param photo 증명 사진
     */
    @PutMapping
    public void updatePhoto(@AuthenticationPrincipal AuthUser authUser,
                            @RequestParam("photo") MultipartFile photo) {
        if (photo.isEmpty()) {
            return;
        }

        try {
            photoUseCase.updatePhoto(authUser.getId(), new FileRequest(
                    photo.getContentType(), photo.getOriginalFilename(),
                    photo.getInputStream()
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public String findPhoto(@AuthenticationPrincipal AuthUser authUser) {
        PhotoResponse response = photoUseCase.findIdPhoto(authUser.getId());
        if (response.getFilename() != null) {
            return String.format("<img src='%s'>", response.getFilename());
        }

        // TODO: if there is no photo, how can we handle this?
        return "";
    }
}
