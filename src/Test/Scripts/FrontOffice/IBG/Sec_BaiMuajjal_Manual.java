package Test.Scripts.FrontOffice.IBG;

import POM.PageObject;
import Test.General.BaseClass;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.devtools.v85.page.Page;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Sec_BaiMuajjal_Manual extends BaseClass {

    @Test(groups = {"IBGInputter"}, dataProvider = "InputterData")
    public void Sec_BaiMuajjal_ManualInputter(Map<String, String> testData) throws IOException, InterruptedException {

        PageObject.menu_Dropdown("Bai-Muajjal");
        PageObject.menu_Link("Bai-Maujjal (Manual) ");

        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(4);

        PageObject.img_Button("New Deal");

//        PageObject.textinput_Locator("fieldName:CUSTOMER.ID","11845748");
        PageObject.textinput_Locator("fieldName:CUSTOMER.ID",testData.get("Counterparty"));
        PageObject.click_Locator("fieldName:PRINCIPAL");
        String formPage= PageObject.switchToChildWindow();
        driver.close();
        PageObject.switchToParentWindow(formPage);
        PageObject.switchFrame(4);

        PageObject.textinput_Locator("fieldName:PRINCIPAL",testData.get("Amount"));
        //PageObject.textinput_Locator("fieldName:PRINCIPAL","123");
//        PageObject.click_Locator("");
        PageObject.textinput_Locator("fieldName:MATURITY.DATE",testData.get("MaturityDate"));
//        PageObject.textinput_Locator("fieldName:MATURITY.DATE","20230130");
//        PageObject.radiobutton_Locator("radio:mainTab:DEAL.SETTLEMENT",4);
        PageObject.radiobutton_Locator("radio:mainTab:DEAL.SETTLEMENT",Integer.parseInt(testData.get("dealSettlement")));
        PageObject.textinput_Locator("fieldName:DEAL.METHOD",testData.get("dealMethod"));
        PageObject.textinput_Locator("fieldName:INTEREST.RATE",testData.get("InterestRate"));
        PageObject.commitDealIBG("IBG_Sec_BaiMuajjal_ManualTxn");

    }

    @Test (groups = {"IBGAuthorizer"}, dataProvider = "AuthorizationData")
    public void Authorization(Map<String, String> testData) throws IOException {

        PageObject.menu_Dropdown("BAIMUAJJAL");
        PageObject.menu_Link("Bai-Muajjal (Manual) ");
        String homePage = PageObject.switchToChildWindow();
        PageObject.textinput_Locator("transactionId",testData.get("Transaction Number"));
        PageObject.img_Button("Perform an action on the contract");
        PageObject.img_Button("Authorises a deal");
    }


    @DataProvider(name = "InputterData")
    public Object[][] inputterData() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\IBG\\IBG_Sec_BaiMuajjal_ManualData.xlsx";
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


    @DataProvider(name = "AuthorizationData")
    public Object[][] auth() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\IBG\\IBG_Sec_BaiMuajjal_ManualTxn.xlsx";
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
