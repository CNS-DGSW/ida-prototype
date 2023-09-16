package kr.hs.dgsw.cns.aggregate.secondary.usecase;

import kr.hs.dgsw.cns.aggregate.secondary.spi.service.JobAptitudeScoreService;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import kr.hs.dgsw.cns.global.dto.FileRequest;
import lombok.RequiredArgsConstructor;

import java.io.OutputStream;

@UseCase
@RequiredArgsConstructor
public class JobAptitudeScoreUseCase {
    private final JobAptitudeScoreService jobAptitudeScoreService;

    public void uploadJobAptitude(FileRequest request) {
        jobAptitudeScoreService.uploadJobAptitude(request);
    }

    public void getJobAptitude(OutputStream outputStream) {
        jobAptitudeScoreService.getJobAptitude(outputStream);
    }
}
