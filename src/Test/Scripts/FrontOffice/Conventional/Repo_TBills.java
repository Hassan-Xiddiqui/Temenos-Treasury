package Test.Scripts.FrontOffice.Conventional;

import POM.PageObject;
import Test.General.BaseClass;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Repo_TBills extends BaseClass {
    String FILE_PATH = System.getProperty("user.dir") + "\\Excel Data\\Repo_TBills.xlsx";

    @Test(groups = {"Inputter"}, dataProvider = "excelDataRepo_TBills")
    public static void Repo_TBills(Map<String, String> testData) throws IOException, InterruptedException {

        PageObject.menu_Dropdown("Capture Current Repo deals"); //000401-224
        PageObject.menu_Dropdown("Deals (PIBS/TBILLS) ");
        PageObject.menu_Link("Repo TBills ");

        String mainWindow = PageObject.switchToChildWindow();

        PageObject.switchFrame(0);

        PageObject.textinput_Locator("fieldName:COUNTERPARTY", testData.get("COUNTERPARTY"));

        PageObject.click_Locator("fieldName:REPO.TYPE");

        String menu = PageObject.switchToChildWindow();

        driver.close();
        PageObject.switchToParentWindow(menu);
        PageObject.switchFrame(0);
//      1st Leg:
        PageObject.textinput_Locator("fieldName:REPO.TYPE", testData.get("REPO.TYPE"));
//        PageObject.textinput_Locator("fieldName:MATURITY.DATE", testData.get("MATURITY.DATE"));
        PageObject.textinput_Locator("fieldName:MATURITY.DATE",testData.get("MATURITY.DATE"));
        PageObject.textinput_Locator("fieldName:NEW.SEC.CODE:1", testData.get("NEW.SEC.CODE:1"));
        PageObject.click_Locator("fieldName:NEW.NOMINAL:1");

        PageObject.textinput_Locator("fieldName:NEW.NOMINAL:1", testData.get("NEW.NOMINAL:1"));
        PageObject.textinput_Locator("fieldName:RP.HFT.NOMINAL:1",testData.get("RP.HFT.NOMINAL:1"));
        PageObject.textinput_Locator("fieldName:NEW.DEPO:1",testData.get("NEW.DEPO:1"));

        PageObject.textinput_Locator("fieldName:RP.RATE", testData.get("RP.RATE"));

        PageObject.radiobutton_Locator("radio:mainTab:DEAL.MODE", Integer.parseInt(testData.get("DEAL.MODE")));
        PageObject.radiobutton_Locator("radio:mainTab:DEAL.MODE", Integer.parseInt(testData.get("DEAL.MODE")));
        PageObject.textinput_Locator("fieldName:DEAL.METHOD",testData.get("DEAL.METHOD"));

        // menu = PageObject.switchToChildWindow();
        PageObject.commitDeal("Repo_TBills");

    }
    @DataProvider(name = "excelDataRepo_TBills")
    public Object[][] readExcelData1() throws IOException {

        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
        int rowCount = sheet.getPhysicalNumberOfRows();
//        rowCount-=2;
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

    String FILE_PATH2 = System.getProperty("user.dir") + "\\Data\\Repo_TBills.xlsx";


    @Test (groups = {"BOAuthorizer"}, dataProvider = "DataRepo_TBills")
    public static void Repo_TBillsAuth(Map<String, String> testData) throws IOException {

        // Authorizer ...
        PageObject.menu_Dropdown("REPO Menu");
        PageObject.menu_Dropdown("Back Office ",4);
        PageObject.menu_Dropdown("Authorise ");
//        PageObject.menu_Dropdown("Forex ");
//        PageObject.menu_Dropdown("Auth/Modify/Rev/Del Forex Deals ");
        PageObject.menu_Link("Repo Treasury Bills  ");

        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(0);

        PageObject.img_Button("Selection Screen");
        PageObject.textinput_Locator("value:1:1:1",testData.get("Transaction Number"));
        PageObject.find_Button();

        driver.switchTo().parentFrame();
        PageObject.switchFrame(0);
//        PageObject.img_Button("Select Drilldown");
        driver.findElement(By.xpath("//tr/td/a[text()='Authorise']")).click();


        driver.switchTo().parentFrame();
        PageObject.switchFrame(1);

        PageObject.img_Button("Authorises a deal");



    }
    @DataProvider(name = "DataRepo_TBills")
    public Object[][] readExcelData2() throws IOException {

        FileInputStream fis = new FileInputStream(FILE_PATH2);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
        int rowCount = sheet.getPhysicalNumberOfRows();
//        rowCount-=2;
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
