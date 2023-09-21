package kr.hs.dgsw.cns.aggregate.secondary.usecase;

import kr.hs.dgsw.cns.aggregate.secondary.constraint.ScoreType;
import kr.hs.dgsw.cns.aggregate.secondary.dto.ExcelRequestDto;
import kr.hs.dgsw.cns.aggregate.secondary.spi.service.ScoreService;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import kr.hs.dgsw.cns.global.dto.FileRequest;
import lombok.RequiredArgsConstructor;

import java.io.OutputStream;

@UseCase
@RequiredArgsConstructor
public class AptitudeScoreUseCase {
    private final ScoreService scoreService;

    public void uploadJobAptitude(FileRequest request) {
        scoreService.uploadScore(request, ScoreType.APTITUDE);
    }

    public void getJobAptitude(OutputStream outputStream) {
        ExcelRequestDto requestDto = ExcelRequestDto.builder()
                .fileName("직무적성_서식")
                .title("2023학년도 신입생 2차 전형 직무적성 점수 일람표")
                .body("1교시   초검                    (인)   재검                    (인)   삼검                    (인)")
                .build();

        scoreService.getScore(outputStream, requestDto, ScoreType.APTITUDE);
    }
}
