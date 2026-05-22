package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UpdateExcel {
    public static void main(String[] args) {
        String filepath = "src/test/resources/Data/Credit_Card_TestData_Final.xlsx";
        try (FileInputStream fs = new FileInputStream(filepath);
             Workbook workbook = new XSSFWorkbook(fs)) {
             
            Sheet sheet = workbook.getSheet("Test_Data");
            Row headerRow = sheet.getRow(0);
            Map<String, Integer> colMap = new HashMap<>();
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                Cell cell = headerRow.getCell(i);
                if (cell != null) {
                    colMap.put(cell.toString().trim(), i);
                }
            }

            // Define updates
            updateRow(sheet, colMap, "CC_TC_048", Map.of(
                "Execution_Mode", "RUNTIME",
                "Card_Status", "active",
                "Source_Account_Balance", "100000.0",
                "Requested_Limit", "25000.0",
                "Card_Tier", "entry",
                "Annual_Income", "400000.0"
            ));

            updateRow(sheet, colMap, "CC_TC_049", Map.of(
                "Execution_Mode", "RUNTIME",
                "Card_Status", "blocked",
                "Source_Account_Balance", "100000.0",
                "Requested_Limit", "25000.0",
                "Card_Tier", "entry",
                "Annual_Income", "400000.0"
            ));

            updateRow(sheet, colMap, "CC_TC_050", Map.of(
                "Execution_Mode", "RUNTIME",
                "Card_Status", "blocked",
                "Source_Account_Balance", "100000.0",
                "Requested_Limit", "25000.0",
                "Card_Tier", "entry",
                "Annual_Income", "400000.0",
                "Purchase_Amount", "500.0",
                "Merchant", "Test Merchant",
                "Category", "Shopping"
            ));

            try (FileOutputStream os = new FileOutputStream(filepath)) {
                workbook.write(os);
            }
            System.out.println("Excel file successfully updated with required testcase fields!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateRow(Sheet sheet, Map<String, Integer> colMap, String testcaseId, Map<String, String> values) {
        int testcaseCol = colMap.get("Testcase_ID");
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;
            Cell tcCell = row.getCell(testcaseCol);
            if (tcCell != null && testcaseId.equals(tcCell.toString().trim())) {
                for (Map.Entry<String, String> entry : values.entrySet()) {
                    Integer colIdx = colMap.get(entry.getKey());
                    if (colIdx != null) {
                        Cell cell = row.getCell(colIdx);
                        if (cell == null) {
                            cell = row.createCell(colIdx);
                        }
                        cell.setCellValue(entry.getValue());
                    }
                }
                break;
            }
        }
    }
}
