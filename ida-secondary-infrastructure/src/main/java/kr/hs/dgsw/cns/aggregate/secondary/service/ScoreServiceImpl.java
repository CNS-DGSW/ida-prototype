package kr.hs.dgsw.cns.aggregate.secondary.service;

import kr.hs.dgsw.cns.aggregate.applicant.domain.Applicant;
import kr.hs.dgsw.cns.aggregate.applicant.spi.query.QueryApplicantSpi;
import kr.hs.dgsw.cns.aggregate.secondary.constraint.ScoreType;
import kr.hs.dgsw.cns.aggregate.secondary.domain.Secondary;
import kr.hs.dgsw.cns.aggregate.secondary.domain.value.Aptitude;
import kr.hs.dgsw.cns.aggregate.secondary.domain.value.Interview;
import kr.hs.dgsw.cns.aggregate.secondary.dto.ExcelRequestDto;
import kr.hs.dgsw.cns.aggregate.secondary.spi.query.CommandSecondarySpi;
import kr.hs.dgsw.cns.aggregate.secondary.spi.query.QuerySecondarySpi;
import kr.hs.dgsw.cns.aggregate.secondary.spi.service.ScoreService;
import kr.hs.dgsw.cns.aggregate.secondary.util.*;
import kr.hs.dgsw.cns.global.dto.FileRequest;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {
    private final QuerySecondarySpi querySecondarySql;
    private final CommandSecondarySpi commandSecondarySpi;
    private final ExcelSheetExtractor excelSheetExtractor;
    private final QueryApplicantSpi queryApplicantSpi;
    private final ExcelServiceConvertor excelServiceConvertor;

    @Override
    public void uploadScore(FileRequest request, ScoreType scoreType) {
        Sheet worksheet = excelSheetExtractor.getRows(request);

        for (int i = 3; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);

            if (row != null) {
                double doubleValue = row.getCell(1).getNumericCellValue();
                Long examCode = Math.round(doubleValue);
                Secondary secondary = querySecondarySql.findByExamCode(examCode)
                        .orElseThrow();

                int scoreValue = (int) row.getCell(7).getNumericCellValue();
                switch (scoreType) {
                    case COMPUTING -> secondary.uploadInterview(Interview.builder()
                            .computingScore(scoreValue)
                            .build());
                    case STUDY -> secondary.uploadInterview(Interview.builder()
                            .studyScore(scoreValue)
                            .build());
                    case APTITUDE -> secondary.uploadAptitude(Aptitude.builder()
                            .score(scoreValue)
                            .build());
                }

                commandSecondarySpi.save(secondary);
            }
        }
    }

    @Override
    public void getScore(OutputStream outputStream, ExcelRequestDto requestDto, ScoreType scoreType) {
        List<ExcelScoreTemplate> excel = new ArrayList<>();
        List<Secondary> secondaryList = querySecondarySql.findByAll();

        for (Secondary secondary : secondaryList) {
            Applicant applicant = queryApplicantSpi.findById(secondary.getAdmission().getApplicant().getId())
                    .orElseThrow();
            excel.add(new ExcelScoreTemplate(secondary, applicant));
        }

        ExcelGenerator excelGenerator = new ExcelGenerator(requestDto.getFileName());
        Font boldFont = CellStyleManager.boldFont(excelGenerator);
        CellStyle cellStyleTitle = CellStyleManager.cellStyleTitle(excelGenerator);
        CellStyle cellStyleSubtitle = CellStyleManager.cellStyleSubtitle(excelGenerator);

        excelGenerator.getHeaderStyle().setBorderBottom(CellStyle.BORDER_DOUBLE);
        excelGenerator.getHeaderStyle().setFont(boldFont);

        excelGenerator.setStartColIdx(0);
        excelGenerator.setStartRowIdx(2);

        excelGenerator.mergedRegion(new CellRangeAddress(0, 0, 0, 7));
        excelGenerator.mergedRegion(new CellRangeAddress(1, 1, 0, 7));

        excelGenerator.setValue(0, 0, requestDto.getTitle(), cellStyleTitle);
        excelGenerator.setValue(1, 0, requestDto.getBody(), cellStyleSubtitle);
        excelGenerator.setHeader(new String[]{"번호", "수험번호", "이름",
                "성별", "학교명", "1차합격전형", "지역", "총점"});
        excelGenerator.setColumnWidth(new int[]{1500, 2750, 2750, 1750, 4000,
                4000, 3500, 2750}, true);

        excelGenerator.setRowHeight(1, 600);
        excelGenerator.setRowHeight(2, 625);
        excelGenerator.setRowHeight(3, 600);

        int idx = 1;
        int rowIdx = 3;
        List<ExcelScoreTemplate> sortedList = excel.stream()
                .sorted(Comparator.comparing(ExcelScoreTemplate::getExamCode))
                .toList();

        for (ExcelScoreTemplate item : sortedList) {
            int cellIdx = 0;
            excelGenerator.setRowHeight(rowIdx, 625);
            excelServiceConvertor.setInhabit(excelGenerator, rowIdx, cellIdx, idx, item, scoreType);
            rowIdx++;
            idx++;
        }

        try {
            excelGenerator.generate().write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
