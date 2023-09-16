package kr.hs.dgsw.cns.aggregate.secondary.util;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

public class CellStyleManager {

    public static Font defaultFont(ExcelGenerator excelGenerator) {
        Font font = excelGenerator.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontName("맑은 고딕");
        font.setFontHeight((short) 360);

        return font;
    }

    public static Font boldFont(ExcelGenerator excelGenerator) {
        Font font = excelGenerator.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontName("맑은 고딕");

        return font;
    }

    public static CellStyle cellStyleTitle(ExcelGenerator excelGenerator) {
        CellStyle cellStyleTitle = excelGenerator.getWorkbook().createCellStyle();
        cellStyleTitle.cloneStyleFrom(excelGenerator.getGlobalStyle());
        cellStyleTitle.setBorderBottom(CellStyle.BORDER_NONE);
        cellStyleTitle.setBorderTop(CellStyle.BORDER_NONE);
        cellStyleTitle.setBorderLeft(CellStyle.BORDER_NONE);
        cellStyleTitle.setBorderRight(CellStyle.BORDER_NONE);
        cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleTitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyleTitle.setFont(defaultFont(excelGenerator));

        return cellStyleTitle;
    }

    public static CellStyle cellStyleSubtitle(ExcelGenerator excelGenerator) {
        CellStyle cellStyleSubtitle = excelGenerator.getWorkbook().createCellStyle();
        cellStyleSubtitle.setFont(boldFont(excelGenerator));
        cellStyleSubtitle.setAlignment(CellStyle.ALIGN_RIGHT);
        cellStyleSubtitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        return cellStyleSubtitle;
    }

    public static CellStyle cellStyleDouble(ExcelGenerator excelGenerator) {
        CellStyle cellStyleDouble = excelGenerator.getWorkbook().createCellStyle();
        cellStyleDouble.cloneStyleFrom(excelGenerator.getGlobalStyle());
        cellStyleDouble.setDataFormat(excelGenerator.getWorkbook().createDataFormat().getFormat("#,##0.000"));

        return cellStyleDouble;
    }

    public static CellStyle cellStyleBoldDouble(ExcelGenerator excelGenerator) {
        CellStyle cellStyleBoldDouble = excelGenerator.getWorkbook().createCellStyle();
        cellStyleBoldDouble.cloneStyleFrom(excelGenerator.getGlobalStyle());
        cellStyleBoldDouble.setDataFormat(excelGenerator.getWorkbook().createDataFormat().getFormat("#,##0.000"));
        cellStyleBoldDouble.setFont(boldFont(excelGenerator));

        return cellStyleBoldDouble;
    }
}
