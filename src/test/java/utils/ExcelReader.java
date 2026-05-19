package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

public class ExcelReader {
    private static final Logger logger = LoggerUtility.getLogger(ExcelReader.class);

    private Workbook workbook;
    private Sheet sheet;

    public ExcelReader(String filepath, String sheetName) throws IOException {

        FileInputStream fs = new FileInputStream(filepath);
        this.workbook = new XSSFWorkbook(fs);
        this.sheet = workbook.getSheet(sheetName);
        logger.info("Loaded Excel file: {} | Sheet: {}", filepath, sheetName);
    }

    public String GetCellData(int row, int column) {

        Cell cell = this.sheet.getRow(row).getCell(column);
        return cell.toString();
    }

    public int GetNumberOfRows() {

        return sheet.getPhysicalNumberOfRows();
    }

    public Map<String, String> getData(String testcaseId) {

        logger.info("Fetching Excel data for testcaseId: {}", testcaseId);
        Map<String, String> dataMap = new HashMap<>();

        Row headerRow = sheet.getRow(0);

        for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {

            Row currentRow = sheet.getRow(rowIndex);

            if (currentRow == null) {
                continue;
            }

            Cell testcaseCell = currentRow.getCell(2);

            if (testcaseCell == null) {
                continue;
            }

            String currentTestcaseId = testcaseCell.toString().trim();

            if (currentTestcaseId.equalsIgnoreCase(testcaseId.trim())) {

                for (int columnIndex = 0;
                     columnIndex < headerRow.getPhysicalNumberOfCells();
                     columnIndex++) {

                    Cell headerCell = headerRow.getCell(columnIndex);

                    if (headerCell == null) {
                        continue;
                    }

                    String columnName = headerCell.toString().trim();

                    Cell valueCell = currentRow.getCell(columnIndex);

                    String cellValue = "";

                    if (valueCell != null) {
                        cellValue = valueCell.toString().trim();
                    }

                    dataMap.put(columnName, cellValue);
                }

                return dataMap;
            }
        }

        logger.warn("TestcaseId {} not found in the Excel sheet", testcaseId);
        return dataMap;
    }
}
