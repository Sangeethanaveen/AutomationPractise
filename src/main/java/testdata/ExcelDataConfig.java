package testdata;

import com.automation.baseclass.AutomationPractiseBaseClass;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExcelDataConfig {
    static XSSFWorkbook wb;
    static XSSFSheet sheet;
    Map<Object, Object> dataValue = new HashMap<>();
    ArrayList<Object> testData = new ArrayList<Object>();


    public ExcelDataConfig() {
        try {
            File src = new File("C:\\Users\\nsang\\IdeaProjects\\AutomationPractise\\src\\main\\java\\testdata\\TestData.xlsx");
            FileInputStream fis = new FileInputStream(src);
            wb = new XSSFWorkbook(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Object,Object> getData(String fileName, String testCaseName) {
        int noOfSheets = wb.getNumberOfSheets();
        for (int i = 0; i < noOfSheets; i++) {
            if (wb.getSheetName(i).equalsIgnoreCase("TestData")) {
                sheet = wb.getSheetAt(i);
            }
        }
        int n = sheet.getLastRowNum();
        Object[][] data = new Object[0][n];
        Iterator<Row> rows = sheet.iterator();
        Row fRow = rows.next();
        Iterator<Cell> cell = fRow.cellIterator();
        int cValue = 0;
        int column = 0;
        while (cell.hasNext()) {
            Cell value = cell.next();
            if (value.getStringCellValue().equalsIgnoreCase(fileName)) {

                column = cValue;
            }
            cValue++;
        }
        while (rows.hasNext()) {
            Row r = rows.next();
            if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
                Iterator<Cell> cellData = r.cellIterator();
                while (cellData.hasNext()) {
                    Cell c = cellData.next();
                    Object nValue = null;
                    if (c.getCellType() == CellType.STRING) {
                        Object value;
                        value = c.getStringCellValue();
                        testData.add(value);
                        //System.out.println(c.getStringCellValue());
                    } else {
                        nValue = c.getNumericCellValue();
                        testData.add(nValue);
                    }
                }
            }
        }
        ArrayList<Object> name =new ArrayList<>();
        name.add("Testcases");
        int sizeOfArray = testData.size();
        for(int i=1;i<n;i++){
            name.add("data"+i);
        }
        for (int i = 1; i < sizeOfArray; i++) {
            Object value = testCaseName;
            dataValue.put(name.get(i), testData.get(i));

        }
        //System.out.println(dataValue);
        return dataValue;
    }
}


