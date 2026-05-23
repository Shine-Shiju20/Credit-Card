package utils.Loan;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.DataFormatter;

public class ExcelReader {

    private Workbook workbook;
    private Sheet sheet;
    private Map<String, Integer> columnMap = new HashMap<>();

    public ExcelReader(String filepath, String sheetName) throws IOException {

        FileInputStream fs = new FileInputStream(filepath);
        workbook = new XSSFWorkbook(fs);
        sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new RuntimeException("Sheet not found: " + sheetName);
        }

        Row headerRow = sheet.getRow(0);

        if (headerRow == null) {
            throw new RuntimeException("Header row is missing in Excel sheet");
        }


        for (Cell cell : headerRow) {
            String columnName = getCellValue(cell)
                    .trim()
                    .replace("\u00A0", "")
                    .replace(" ", "_");

            columnMap.put(columnName, cell.getColumnIndex());


        }
    }

    public String getCellData(int row, String columnName) {

        columnName = columnName.trim().replace("\u00A0", "").replace(" ", "_");

        if (!columnMap.containsKey(columnName)) {
            throw new RuntimeException(
                    "Column not found: " + columnName + ". Available columns are: " + columnMap.keySet()
            );
        }

        int colIndex = columnMap.get(columnName);

        Row currentRow = sheet.getRow(row);

        if (currentRow == null) {
            return "";
        }

        Cell cell = currentRow.getCell(colIndex);

        if (cell == null) {
            return "";
        }

        return getCellValue(cell).trim();
    }

    private String getCellValue(Cell cell) {

        DataFormatter formatter = new DataFormatter();

        return formatter.formatCellValue(cell);
    }

    public int getRowCount() {

        return sheet.getLastRowNum();
    }

    public String getCellDataByTestCaseID(String testCaseID, String columnName) {

        int rowCount = sheet.getPhysicalNumberOfRows();

        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        int columnIndex = -1;

        for (int i = 0; i < colCount; i++) {

            String header =
                    sheet.getRow(0).getCell(i).getStringCellValue().trim();

            if (header.equalsIgnoreCase(columnName.trim())) {

                columnIndex = i;

                break;
            }
        }

        if (columnIndex == -1) {

            throw new RuntimeException(
                    "Column not found: " + columnName
            );
        }

        for (int i = 1; i < rowCount; i++) {

            String tcId =
                    sheet.getRow(i).getCell(0).getStringCellValue().trim();

            if (tcId.equalsIgnoreCase(testCaseID)) {

                return new DataFormatter().formatCellValue(
                        sheet.getRow(i).getCell(columnIndex)
                );
            }
        }

        throw new RuntimeException(
                "Test Case ID not found: " + testCaseID
        );
    }

    public String getCellDataByTestCaseIDSafe(String testCaseID, String columnName) {

        DataFormatter formatter = new DataFormatter();

        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        int columnIndex = -1;

        for (int i = 0; i < colCount; i++) {

            Cell headerCell = sheet.getRow(0).getCell(i);

            if (headerCell == null) continue;

            String header = formatter.formatCellValue(headerCell).trim();

            if (header.equalsIgnoreCase(columnName.trim())) {
                columnIndex = i;
                break;
            }
        }

        if (columnIndex == -1) {
            throw new RuntimeException("Column not found: " + columnName);
        }

        for (int i = 1; i <= rowCount; i++) {

            Row row = sheet.getRow(i);

            if (row == null) continue;

            Cell tcCell = row.getCell(0);

            if (tcCell == null) continue;

            String tcId = formatter.formatCellValue(tcCell).trim();

            if (tcId.equalsIgnoreCase(testCaseID.trim())) {

                Cell targetCell = row.getCell(columnIndex);

                if (targetCell == null) return "";

                return formatter.formatCellValue(targetCell).trim();
            }
        }

        throw new RuntimeException("Test Case ID not found in Excel: " + testCaseID);
    }
}