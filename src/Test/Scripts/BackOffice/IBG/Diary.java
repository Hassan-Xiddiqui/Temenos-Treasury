package Test.Scripts.BackOffice.IBG;

import POM.PageObject;
import Test.General.BaseClass;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Diary extends BaseClass {

    @Test(groups = {"BOInputterIBG"}, dataProvider = "InputterData")
    public void Diary(Map<String, String> testData) throws IOException, InterruptedException {

        String dividend=testData.get("dividendType");
        String Sec=testData.get("security");
        String rate=testData.get("rate");

        PageObject.menu_Dropdown("Bond Outright Menu");
        PageObject.menu_Dropdown("Back Office",3);
        PageObject.menu_Dropdown("Corporate Action (New)");
        PageObject.menu_Dropdown("Diary Events ");
        PageObject.menu_Link("Coupon Diary  ");

        String NewDeal = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();

        PageObject.img_Button("New Deal");

        PageObject.textinput_Locator("fieldName:EVENT.TYPE",dividend);
        PageObject.textinput_Locator("fieldName:SECURITY.NO",Sec);
        PageObject.click_Locator("fieldName:RATE:1");
        PageObject.textinput_Locator("fieldName:RATE:1",rate);


        PageObject.commitDealIBG("IBG_BackOffice_DiaryTxn");

    }

    @DataProvider(name = "InputterData")
    public Object[][] readExcelData_1() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\IBG\\IBG_BackOffice_Diary.xlsx";

        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
        Object[][] data = new Object[rowCount - 1][1]; // One column to store the HashMap

        for (int i = 1; i < rowCount; i++) { // Start from row 1 to exclude header row
            Row row = sheet.getRow(i);
            Map<String, String> map = new HashMap<String, String>();
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                DataFormatter formatter = new DataFormatter();
                String value = formatter.formatCellValue(cell);
                map.put(sheet.getRow(0).getCell(j).toString(), value); // Assuming the first row contains column names
            }
            data[i - 1][0] = map;
        }

        workbook.close();
        fis.close();
        return data;
    }

}
