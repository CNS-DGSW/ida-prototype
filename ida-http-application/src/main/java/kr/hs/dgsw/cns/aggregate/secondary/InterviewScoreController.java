package kr.hs.dgsw.cns.aggregate.secondary;

import kr.hs.dgsw.cns.aggregate.secondary.usecase.InterviewScoreUseCase;
import kr.hs.dgsw.cns.global.dto.FileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interviews")
@RequiredArgsConstructor
public class InterviewScoreController {

    private final InterviewScoreUseCase interviewScoreUseCase;

    @PostMapping("/upload")
    public void upload(@PathVariable FileRequest request) {
        interviewScoreUseCase.upload(request);
    }
}
