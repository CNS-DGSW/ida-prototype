package kr.hs.dgsw.cns.aggregate.secondary.util;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.LinkedList;

@Getter @Setter
public class ExcelGenerator {
    private LinkedList<CellData> cellDataList = new LinkedList<>();
    private XSSFWorkbook workbook = new XSSFWorkbook();
    private Sheet sheet;
    private String[] columns;
    private CellStyle globalStyle = workbook.createCellStyle();
    private CellStyle headerStyle = workbook.createCellStyle();
    private int startRowIdx = 1;
    private int startColIdx = 1;

    public ExcelGenerator(String name) {
        sheet = workbook.createSheet(name);

        globalStyle.setBorderLeft(CellStyle.BORDER_THIN);
        globalStyle.setBorderRight(CellStyle.BORDER_THIN);
        globalStyle.setBorderTop(CellStyle.BORDER_THIN);
        globalStyle.setBorderBottom(CellStyle.BORDER_THIN);
        globalStyle.setAlignment(CellStyle.ALIGN_CENTER);
        globalStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        headerStyle.cloneStyleFrom(globalStyle);
        headerStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
        headerStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
        headerStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
        headerStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
    }

    public void setHeader(String[] column) {
        this.columns = column;

        Row headerRow = sheet.createRow(startRowIdx);

        for (int col = 0; col < this.columns.length; col++) {
            Cell cell = headerRow.createCell(col + startColIdx);
            cell.setCellValue(this.columns[col]);
            cell.setCellStyle(headerStyle);
        }
    }

    public void setColumnWidth(int[] list, boolean skip) {
        for (int i = 0; i < list.length; i++) {
            if (skip) {
                sheet.setColumnWidth(startColIdx + i, list[i]);
            } else {
                sheet.setColumnWidth(i, list[i]);
            }
        }
    }

    public void setRowHeight(int rowIdx, int value) {
        Row row = (sheet.getRow(rowIdx) == null) ? sheet.createRow(rowIdx) : sheet.getRow(rowIdx);
        row.setHeight((short) value);
    }

    public void mergedRegion(CellRangeAddress cellRangeAddress) {
        sheet.addMergedRegion(cellRangeAddress);
    }

    public void setValue(int rowIdx, int cellIdx, String data) {
        cellDataList.add(new CellData(rowIdx, cellIdx, data, false, null));
    }

    public void setValue(int rowIdx, int cellIdx, Long data) {
        cellDataList.add(new CellData(rowIdx, cellIdx, String.valueOf(data), true, null));
    }

    public void setValue(int rowIdx, int cellIdx, String data, boolean isNumber) {
        cellDataList.add(new CellData(rowIdx, cellIdx, data, isNumber, null));
    }

    public void setValue(int rowIdx, int cellIdx, double data) {
        cellDataList.add(new CellData(rowIdx, cellIdx, String.valueOf(data), true, null));
    }

    public void setValue(int rowIdx, int cellIdx, String data, CellStyle cellStyle) {
        cellDataList.add(new CellData(rowIdx, cellIdx, data, false, cellStyle));
    }

    public XSSFWorkbook generate() {
        cellDataList.forEach(cellData -> {
            Row row = (sheet.getRow(cellData.rowIdx) == null) ? sheet.createRow(cellData.rowIdx) : sheet.getRow(cellData.rowIdx);
            Cell cell = (row.getCell(cellData.cellIdx) == null) ? row.createCell(cellData.cellIdx) : row.getCell(cellData.cellIdx);
            cell.setCellStyle(globalStyle);

            if (cellData.cellStyle != null) {
                cell.setCellStyle(cellData.cellStyle);
            }

            if (cellData.value == null || cellData.value.isEmpty()) {
                cell.setCellType(Cell.CELL_TYPE_BLANK);
            } else {
                if (cellData.isNumber) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(Double.parseDouble(cellData.value));
                } else {
                    cell.setCellValue(cellData.value);
                }
            }
        });

        return workbook;
    }
    static class CellData {
        int rowIdx;
        int cellIdx;
        String value;
        boolean isNumber;
        CellStyle cellStyle;

        CellData(int rowIdx, int cellIdx, String value, boolean isNumber, CellStyle cellStyle) {
            this.rowIdx = rowIdx;
            this.cellIdx = cellIdx;
            this.value = value;
            this.isNumber = isNumber;
            this.cellStyle = cellStyle;
        }
    }
}
