package kr.hs.dgsw.cns.aggregate.secondary.service;

import kr.hs.dgsw.cns.aggregate.secondary.dto.ExcelData;
import kr.hs.dgsw.cns.aggregate.secondary.spi.service.InterviewScoreService;
import kr.hs.dgsw.cns.global.dto.FileRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class InterviewScoreServiceImpl implements InterviewScoreService {

    @Override
    public void upload(FileRequest request) {
        List<ExcelData> dataList = new ArrayList<>();

        Sheet worksheet = getRows(request);


        for (int i = 2; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);

            ExcelData data = new ExcelData();

            data.setNum((int) row.getCell(0).getNumericCellValue());
            data.setName(row.getCell(1).getStringCellValue());
            data.setEmail(row.getCell(2).getStringCellValue());

            dataList.add(data);
        }
        for(ExcelData it : dataList) {
            log.info(it.getEmail());
            log.info(it.getName());
            log.info(it.getNum().toString());
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
