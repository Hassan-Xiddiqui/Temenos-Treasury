package Test.Scripts.FrontOffice.IBG;

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

public class Sec_BaiMuajjalCorporate extends BaseClass {

    @Test(groups = {"IBGInputter"}, dataProvider = "InputterData")
    public void Sec_BaiMuajjalCorporatePurchase(Map<String, String> testData)  {


        PageObject.menu_Dropdown("Bai-Muajjal Corporate");
        PageObject.menu_Link("Bai Muajjal Corporate Purchase ");

        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(4);

        PageObject.img_Button("New Deal");
//
//        driver.findElement(By.xpath("//img[@alt='New Deal']")).click();
//        PageObject.textinput_Locator("fieldName:SECURITY.CODE","000400-845");
        PageObject.textinput_Locator("fieldName:SECURITY.CODE",testData.get("SecCode"));
        PageObject.click_Locator("fieldName:DEPOSITORY");
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",Integer.parseInt(testData.get("dealSettlement")));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",Integer.parseInt(testData.get("dealSettlement")));
        PageObject.radiobutton_Locator("radio:tab1:RP.PRICE.UPDATE",Integer.parseInt(testData.get("Yield")));
        PageObject.textinput_Locator("fieldName:DEAL.METHOD",testData.get("dealMethod"));
        PageObject.textinput_Locator("fieldName:CUST.ACC.NO:1",testData.get("Amount"));
        PageObject.textinput_Locator("fieldName:COUNTER.PARTY",testData.get("Counterparty"));
        PageObject.click_Locator("fieldName:BROKER");
        String formPage= PageObject.switchToChildWindow();
        driver.close();
        PageObject.switchToParentWindow(formPage);

        try {
            PageObject.commitDealIBG("IBG_Sec_BaiMuajjalCorporateTxn");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Test(groups = {"IBGInputter"}, dataProvider = "InputterData")
    public void Sec_BaiMuajjalCorporateSale(Map<String, String> testData)  {


        PageObject.menu_Dropdown("Bai-Muajjal Corporate");
        PageObject.menu_Link("Bai Muajjal Corporate Sale ");

        String mainWindow = PageObject.switchToChildWindow();
//        PageObject.switchFrame(4);

        PageObject.img_Button("New Deal");
//
//        PageObject.textinput_Locator("fieldName:SECURITY.CODE","000400-845");
        PageObject.textinput_Locator("fieldName:PUR.REF",testData.get(""));
        PageObject.textinput_Locator("fieldName:SECURITY.CODE",testData.get("SecCode"));
        PageObject.click_Locator("fieldName:DEPOSITORY");
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",Integer.parseInt(testData.get("dealSettlement")));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",Integer.parseInt(testData.get("dealSettlement")));
        PageObject.radiobutton_Locator("radio:tab1:RP.PRICE.UPDATE",Integer.parseInt(testData.get("Yield")));
        PageObject.textinput_Locator("fieldName:DEAL.METHOD",testData.get("dealMethod"));
        PageObject.textinput_Locator("fieldName:CUST.ACC.NO:1",testData.get("Amount"));
        PageObject.textinput_Locator("fieldName:COUNTER.PARTY",testData.get("Counterparty"));
        PageObject.click_Locator("fieldName:BROKER");
        String formPage= PageObject.switchToChildWindow();
        driver.close();
        PageObject.switchToParentWindow(formPage);


        try {
            PageObject.commitDealIBG("IBG_Sec_BaiMuajjalCorporateTxn");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    @Test (groups = {"IBGAuthorizer"})
    public void Authorization() throws IOException {

        PageObject.menu_Dropdown("BAIMUAJJAL");
//        PageObject.menu_Link("Bai-Muajjal (Manual) ");
//        String homePage = PageObject.switchToChildWindow();
//        PageObject.textinput_Locator("transactionId",testData.get("Transaction Number"));
//        PageObject.img_Button("Perform an action on the contract");
//        PageObject.img_Button("Authorises a deal");
    }

    @DataProvider(name = "InputterData")
    public Object[][] Inputter() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\IBG\\IBG_Sec_BaiMuajjalCorporateSaleData.xlsx";
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
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\IBG\\IBG_Sec_BaiMuajjalCorporateTxn.xlsx";
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
