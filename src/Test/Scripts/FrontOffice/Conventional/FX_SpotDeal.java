package Test.Scripts.FrontOffice.Conventional;
import POM.PageObject;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.devtools.v85.page.Page;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Test.General.BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FX_SpotDeal extends BaseClass {

    String txn;
    String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\spotDealInputter.xlsx";

    @Test (groups = {"Inputter"}, dataProvider = "excelDataspotDealInputter")
    public static void spotDealInputter(Map<String, String> testData) throws IOException {

        PageObject.menu_Dropdown("Capture Current FX deals");
        PageObject.menu_Link("Input Forex Ready ");


        String mainWindow = PageObject.switchToChildWindow();

        PageObject.switchFrame(0);
        PageObject.textinput_Locator("fieldName:COUNTERPARTY",testData.get("COUNTERPARTY"));

        PageObject.click_Locator("fieldName:CURRENCY.BOUGHT");

        String menu = PageObject.switchToChildWindow();

        driver.close();
        PageObject.switchToParentWindow(menu);
        PageObject.switchFrame(0);

        PageObject.textinput_Locator("fieldName:CURRENCY.BOUGHT",testData.get("CURRENCY.BOUGHT"));
        PageObject.textinput_Locator("fieldName:AMOUNT.BOUGHT",testData.get("AMOUNT.BOUGHT"));
        PageObject.textinput_Locator("fieldName:CURRENCY.SOLD",testData.get("CURRENCY.SOLD"));

        PageObject.textinput_Locator("fieldName:SPOT.RATE",testData.get("SPOT.RATE"));
        PageObject.textinput_Locator("fieldName:BROKER",testData.get("BROKER"));


       // menu = PageObject.switchToChildWindow();
        PageObject.commitDeal("spotDealInputter");



//        txn = PageObject.getTxn();
//        System.out.println(txn);

    }

    @Test (groups = {"Authorizer"}, dataProvider = "excelDataspotDealAuth")
    public static void spotDealAuth(Map<String, String> testData) throws IOException {

        // Authorizer ...
        PageObject.menu_Dropdown("FX INTER BANK");
        PageObject.menu_Dropdown("Forex Inter Bank ");
        PageObject.menu_Dropdown("Back Office ");
        PageObject.menu_Dropdown("Forex ");
        PageObject.menu_Dropdown("Auth/Modify/Rev/Del Forex Deals ");
        PageObject.menu_Link("Authorise/Modify/Delete Forex Deals  ");

        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(0);

        PageObject.img_Button("Selection Screen");
        PageObject.textinput_Locator("value:1:1:1",testData.get("Transaction Number"));
        PageObject.find_Button();

        PageObject.img_Button("Select Drilldown");

        driver.switchTo().parentFrame();
        PageObject.switchFrame(1);

        PageObject.img_Button("Authorises a deal");

    }


    @DataProvider(name = "excelDataspotDealInputter")
    public Object[][] readExcelData1() throws IOException {

        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
        int rowCount = sheet.getPhysicalNumberOfRows();
        //rowCount-=2;
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

    @DataProvider(name = "excelDataspotDealAuth")
    public Object[][] readExcelData2() throws IOException {

        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\spotDealInputter.xlsx";

        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
        int rowCount = sheet.getPhysicalNumberOfRows();
        //rowCount-=2;
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