package kr.hs.dgsw.cns.aggregate.secondary.util;

import org.springframework.stereotype.Component;

@Component
public class ExcelServiceConvertor {
    public void setAptitudeInhabit(ExcelGenerator excelGenerator, int rowIdx, int cellIdx, int idx, ExcelScoreTemplate userInfo) {
        int mutableCellIdx = cellIdx;
        excelGenerator.setValue(rowIdx, mutableCellIdx++, Integer.toString(idx), true);
        excelGenerator.setValue(rowIdx, mutableCellIdx++, userInfo.getExamCode(), true);
        excelGenerator.setValue(rowIdx, mutableCellIdx++, userInfo.getUserName());
        excelGenerator.setValue(rowIdx, mutableCellIdx++, userInfo.getSex());
        excelGenerator.setValue(rowIdx, mutableCellIdx++, userInfo.getSchoolName());
        excelGenerator.setValue(rowIdx, mutableCellIdx++, userInfo.getFirstApplyType());
        excelGenerator.setValue(rowIdx, mutableCellIdx++, userInfo.getCityName());
        excelGenerator.setValue(rowIdx, mutableCellIdx, userInfo.getScore());
    }
}
