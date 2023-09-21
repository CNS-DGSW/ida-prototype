package kr.hs.dgsw.cns.aggregate.secondary.util;

import kr.hs.dgsw.cns.aggregate.secondary.constraint.ScoreType;
import org.springframework.stereotype.Component;

@Component
public class ExcelServiceConvertor {
    public void setInhabit(ExcelGenerator excelGenerator, int rowIdx, int cellIdx, int idx, ExcelScoreTemplate userInfo, ScoreType scoreType) {
        int mutableCellIdx = cellIdx;
        excelGenerator.setValue(rowIdx, mutableCellIdx++, Integer.toString(idx), true);
        excelGenerator.setValue(rowIdx, mutableCellIdx++, userInfo.getExamCode(), true);
        excelGenerator.setValue(rowIdx, mutableCellIdx++, userInfo.getUserName());
        excelGenerator.setValue(rowIdx, mutableCellIdx++, userInfo.getSex());
        excelGenerator.setValue(rowIdx, mutableCellIdx++, userInfo.getSchoolName());
        excelGenerator.setValue(rowIdx, mutableCellIdx++, userInfo.getFirstApplyType());
        excelGenerator.setValue(rowIdx, mutableCellIdx++, userInfo.getCityName());
        switch (scoreType) {
            case COMPUTING -> excelGenerator.setValue(rowIdx, mutableCellIdx, userInfo.getComputingScore());
            case STUDY -> excelGenerator.setValue(rowIdx, mutableCellIdx, userInfo.getStudyScore());
            case APTITUDE -> excelGenerator.setValue(rowIdx, mutableCellIdx, userInfo.getAptitudeScore());
        }
    }
}
