package kr.hs.dgsw.cns.aggregate.secondary.usecase;

import kr.hs.dgsw.cns.aggregate.secondary.constraint.ScoreType;
import kr.hs.dgsw.cns.aggregate.secondary.dto.ExcelRequestDto;
import kr.hs.dgsw.cns.aggregate.secondary.spi.service.ScoreService;
import kr.hs.dgsw.cns.global.annotations.UseCase;
import lombok.RequiredArgsConstructor;

import java.io.OutputStream;

@UseCase
@RequiredArgsConstructor
public class FinalScoreUseCase {
    private final ScoreService scoreService;

    public void getFinalScore(OutputStream outputStream) {
        ExcelRequestDto requestDto = ExcelRequestDto.builder()
                .fileName("최종결과_서식")
                .title("2023학년도 신입생 최종결과 점수 일람표")
                .body("       초검                    (인)   재검                    (인)   삼검                    (인)")
                .build();

        scoreService.getScore(outputStream, requestDto, ScoreType.FINAL);
    }
}
