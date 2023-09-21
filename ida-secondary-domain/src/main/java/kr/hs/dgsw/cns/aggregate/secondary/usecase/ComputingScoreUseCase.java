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
public class ComputingScoreUseCase {
    private final ScoreService scoreService;

    public void uploadComputingScore(FileRequest request) {
        scoreService.uploadScore(request, ScoreType.COMPUTING);
    }

    public void getComputingScore(OutputStream outputStream) {
        ExcelRequestDto requestDto = ExcelRequestDto.builder()
                .fileName("심층면접2_서식")
                .title("2023학년도 신입생 2차 전형 심층면접2 점수 일람표")
                .body("3교시   초검                    (인)   재검                    (인)   삼검                    (인)")
                .build();

        scoreService.getScore(outputStream, requestDto, ScoreType.COMPUTING);
    }
}