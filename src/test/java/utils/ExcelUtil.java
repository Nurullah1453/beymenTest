package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {
    private Workbook workbook;

    private static final String DEFAULT_FILE_PATH = "src/test/resources/testdata.xlsx";

    public ExcelUtil() {
        this(DEFAULT_FILE_PATH);
    }

    public ExcelUtil(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Excel dosyası açılamadı: " + filePath);
        }
    }

    public String getData(int sheetIndex, int row, int cell) {
        try {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Row r = sheet.getRow(row);
            if (r == null) {
                System.out.println("Satır " + row + " boş");
                return null;
            }
            Cell c = r.getCell(cell);
            if (c == null) {
                System.out.println("Hücre " + cell + " boş");
                return null;
            }
            // Hücre tipine göre alalım
            switch (c.getCellType()) {
                case STRING:
                    return c.getStringCellValue();
                case NUMERIC:
                    return String.valueOf(c.getNumericCellValue());
                case BOOLEAN:
                    return String.valueOf(c.getBooleanCellValue());
                case FORMULA:
                    // Formül sonucu string veya numeric olabilir
                    FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                    CellValue cellValue = evaluator.evaluate(c);
                    switch (cellValue.getCellType()) {
                        case STRING:
                            return cellValue.getStringValue();
                        case NUMERIC:
                            return String.valueOf(cellValue.getNumberValue());
                        case BOOLEAN:
                            return String.valueOf(cellValue.getBooleanValue());
                        default:
                            return null;
                    }
                case BLANK:
                    System.out.println("Hücre " + cell + " boş (BLANK)");
                    return null;
                default:
                    return c.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void closeWorkbook() {
        if (workbook != null) {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
