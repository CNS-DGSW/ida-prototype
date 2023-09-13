package kr.hs.dgsw.cns.aggregate.secondary.usecase;

import kr.hs.dgsw.cns.aggregate.secondary.spi.service.InterviewScoreService;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import kr.hs.dgsw.cns.global.dto.FileRequest;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class InterviewScoreUseCase {
    private final InterviewScoreService interviewScoreService;

    public void upload(FileRequest request) {
        interviewScoreService.upload(request);
    }
}
