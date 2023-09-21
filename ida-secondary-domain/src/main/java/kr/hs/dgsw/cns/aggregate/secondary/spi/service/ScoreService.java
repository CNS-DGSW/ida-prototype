package kr.hs.dgsw.cns.aggregate.secondary.spi.service;

import kr.hs.dgsw.cns.aggregate.secondary.constraint.ScoreType;
import kr.hs.dgsw.cns.aggregate.secondary.dto.ExcelRequestDto;
import kr.hs.dgsw.cns.global.dto.FileRequest;

import java.io.OutputStream;

public interface ScoreService {
    void uploadScore(FileRequest request, ScoreType scoreType);

    void getScore(OutputStream outputStream, ExcelRequestDto excelRequestDto, ScoreType scoreType);
}
