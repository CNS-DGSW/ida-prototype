package kr.hs.dgsw.cns.aggregate.secondary.service;

import kr.hs.dgsw.cns.aggregate.applicant.domain.Applicant;
import kr.hs.dgsw.cns.aggregate.applicant.spi.query.QueryApplicantSpi;
import kr.hs.dgsw.cns.aggregate.secondary.domain.Secondary;
import kr.hs.dgsw.cns.aggregate.secondary.domain.value.Aptitude;
import kr.hs.dgsw.cns.aggregate.secondary.spi.query.CommandSecondarySpi;
import kr.hs.dgsw.cns.aggregate.secondary.spi.query.QuerySecondarySpi;
import kr.hs.dgsw.cns.aggregate.secondary.spi.service.JobAptitudeScoreService;
import kr.hs.dgsw.cns.aggregate.secondary.util.*;
import kr.hs.dgsw.cns.global.dto.FileRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class JobAptitudeScoreServiceImpl implements JobAptitudeScoreService {

    private final QuerySecondarySpi querySecondarySpi;
    private final CommandSecondarySpi commandSecondarySpi;
    private final QueryApplicantSpi queryApplicantSpi;
    private final ExcelSheetExtractor excelSheetExtractor;
    private final ExcelServiceConvertor excelServiceConvertor;

    @Override
    public void uploadJobAptitude(FileRequest request) {
        Sheet worksheet = excelSheetExtractor.getRows(request);

        for (int i = 3; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);

            if (row != null) {
                double doubleValue = row.getCell(1).getNumericCellValue();
                Long examCode = Math.round(doubleValue);
                Secondary secondary = querySecondarySpi.findByExamCode(examCode)
                        .orElseThrow();

                int score = (int) row.getCell(7).getNumericCellValue();

                Aptitude aptitude = Aptitude.builder()
                        .score(score)
                        .build();

                secondary.uploadAptitude(aptitude);

                commandSecondarySpi.save(secondary);
            }
        }
    }

    @Override
    public void getJobAptitude(OutputStream outputStream) {
        List<ExcelScoreTemplate> excel = new ArrayList<>();
        List<Secondary> secondaryList = querySecondarySpi.findByAll();

        for (Secondary secondary : secondaryList) {
            Applicant applicant = queryApplicantSpi.findById(secondary.getAdmission().getApplicant().getId())
                    .orElseThrow();
            excel.add(new ExcelScoreTemplate(secondary, applicant));
        }
        ExcelGenerator excelGenerator = new ExcelGenerator("직무적성_서식");
        Font boldFont = CellStyleManager.boldFont(excelGenerator);
        CellStyle cellStyleTitle = CellStyleManager.cellStyleTitle(excelGenerator);
        CellStyle cellStyleSubtitle = CellStyleManager.cellStyleSubtitle(excelGenerator);

        excelGenerator.getHeaderStyle().setBorderBottom(CellStyle.BORDER_DOUBLE);
        excelGenerator.getHeaderStyle().setFont(boldFont);

        excelGenerator.setStartColIdx(0);
        excelGenerator.setStartRowIdx(2);

        excelGenerator.mergedRegion(new CellRangeAddress(0, 0, 0, 7));
        excelGenerator.mergedRegion(new CellRangeAddress(1, 1, 0, 7));

        excelGenerator.setValue(0, 0, "2023학년도 신입생 2차 전형 직업기초능력(직무적성) 점수 일람표", cellStyleTitle);
        excelGenerator.setValue(1, 0, "3교시   초검                    (인)   재검                    (인)   삼검                    (인)", cellStyleSubtitle);
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
            excelServiceConvertor.setJobAptitudeInhabit(excelGenerator, rowIdx, cellIdx, idx, item);
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
