package kr.hs.dgsw.cns.aggregate.secondary.service;

import kr.hs.dgsw.cns.aggregate.secondary.domain.Secondary;
import kr.hs.dgsw.cns.aggregate.secondary.domain.value.Aptitude;
import kr.hs.dgsw.cns.aggregate.secondary.spi.query.CommandSecondarySpi;
import kr.hs.dgsw.cns.aggregate.secondary.spi.query.QuerySecondarySql;
import kr.hs.dgsw.cns.aggregate.secondary.spi.service.JobAptitudeScoreService;
import kr.hs.dgsw.cns.aggregate.secondary.util.CellStyleManager;
import kr.hs.dgsw.cns.aggregate.secondary.util.ExcelGenerator;
import kr.hs.dgsw.cns.global.dto.FileRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class JobAptitudeScoreServiceImpl implements JobAptitudeScoreService {

    private final QuerySecondarySql querySecondarySql;
    private final CommandSecondarySpi commandSecondarySpi;

    @Override
    public void uploadJobAptitude(FileRequest request) {
        Sheet worksheet = getRows(request);

        for (int i = 3; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);

            if (row != null) {
                double doubleValue = row.getCell(1).getNumericCellValue();
                Long examCode = Math.round(doubleValue);
                Secondary secondary = querySecondarySql.findByExamCode(examCode)
                        .orElseThrow();

                int score = (int) row.getCell(7).getNumericCellValue();

                secondary.uploadAptitude(Aptitude.builder()
                        .score(score)
                        .build());

                commandSecondarySpi.save(secondary);
            }
        }
    }

    @Override
    public void getJobAptitude(OutputStream outputStream) {
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

        try {
            excelGenerator.generate().write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Sheet getRows(FileRequest request) {
        String extension = FilenameUtils.getExtension(request.getFilename());

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new RuntimeException("엑셀파일만 업로드 해주세요.");
        }

        Workbook workbook;
        try{
            if (extension.equals("xlsx")) {
                workbook = new XSSFWorkbook(request.getInputStream());
            } else {
                workbook = new HSSFWorkbook(request.getInputStream());
            }

            return workbook.getSheetAt(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
