package kr.hs.dgsw.cns.aggregate.secondary.util;

import kr.hs.dgsw.cns.global.dto.FileRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class ExcelSheetExtractor {
    public Sheet getRows(FileRequest request) {
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
