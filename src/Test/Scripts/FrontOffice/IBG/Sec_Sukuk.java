package Test.Scripts.FrontOffice.IBG;
import java.awt.AWTException;
import java.awt.Robot;

import POM.PageObject;
import Test.General.BaseClass;
import io.cucumber.java.sl.In;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.v85.page.Page;
import org.testng.annotations.Test;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Map;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.util.HashMap;

public class Sec_Sukuk extends BaseClass {


    @Test(groups = {"IBGInputter"}, dataProvider = "InputterData2")
    public void Sec_SukukInputterWithoutPartialRedemption(Map<String, String> testData) throws IOException, InterruptedException {



        PageObject.menu_Dropdown("Bond Outright Menu");
        PageObject.menu_Dropdown("Front Office", 3);
        PageObject.menu_Dropdown("Sukuk without Partial Redemption");
        PageObject.menu_Dropdown("Secondary Market Transaction");
        PageObject.menu_Link("Sukuk without Partial Redemption Transaction ");

        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(4);

        PageObject.img_Button("New Deal");
        PageObject.zoomOutPage(4);
        PageObject.textinput_Locator("fieldName:SECURITY.CODE",testData.get("SecCode"));
//        PageObject.textinput_Locator("fieldName:SECURITY.CODE","000400-845");
        PageObject.click_Locator("fieldName:DEPOSITORY");
        PageObject.textinput_Locator("fieldName:DEPOSITORY",testData.get("Depository"));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT", Integer.parseInt(testData.get("dealSettlement")));
        PageObject.textinput_Locator("fieldName:DEAL.METHOD",testData.get("dealMethod"));
//        PageObject.textinput_Locator("fieldName:KDUMMY","2");





        PageObject.click_Locator("fieldName:CUST.SEC.ACC:1");
        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1",testData.get("Portfolio"));
        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1",testData.get("SaleOrPurchase"));
        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1",testData.get("FaceValue"));

        PageObject.textinput_Locator("fieldName:COUNTER.PARTY",testData.get("Counterparty"));
        PageObject.click_Locator("fieldName:CUST.SEC.ACC:1");

        String formWindow = PageObject.switchToChildWindow();
        driver.close();

        PageObject.switchToParentWindow(formWindow);
        PageObject.switchFrame(4);


        PageObject.commitDealIBG("Sec_SukukWithoutPartialRedemptionTxn");
//

    }

    @Test (groups = {"IBGAuthorizer"}, dataProvider = "AuthorizationData2")
    public void Authorization2(Map<String, String> testData) throws IOException {

        PageObject.menu_Dropdown("Bond Outright Menu");
        PageObject.menu_Dropdown("Back Office",3);
        PageObject.menu_Dropdown("Sukuk with / without Redemption Accrual");
        PageObject.menu_Dropdown("Authorization Menu ");
        PageObject.menu_Link("Authorization of New/Reversal Outright Trade  ");
        String homePage = PageObject.switchToChildWindow();
        PageObject.switchFrame(0);
        PageObject.img_Button("Selection Screen");
        PageObject.textinput_Locator("value:1:1:1",testData.get("Transaction Number"));
        PageObject.find_Button();
        PageObject.img_Button("Select Drilldown");
        driver.switchTo().parentFrame();
        PageObject.switchFrame(1);
        PageObject.img_Button("Authorises a deal");
//        PageObject.textinput_Locator("transactionId",testData.get("Transaction Number"));
//        PageObject.img_Button("Perform an action on the contract");
//        PageObject.img_Button("Authorises a deal");
    }

    @Test (groups = {"IBGAuthorizer"}, dataProvider = "AuthorizationData")
    public void Authorization(Map<String, String> testData) throws IOException {

        PageObject.menu_Dropdown("Bond Outright Menu");
        PageObject.menu_Dropdown("Back Office");
        PageObject.menu_Dropdown("Sukuk with Partial Redemtion");
        PageObject.menu_Dropdown("Authorization Menu");
        PageObject.menu_Link("Authorization of New/Reversal Outright Trade ");
        String homePage = PageObject.switchToChildWindow();
//        PageObject.textinput_Locator("transactionId",testData.get("Transaction Number"));
//        PageObject.img_Button("Perform an action on the contract");
//        PageObject.img_Button("Authorises a deal");
    }

    @DataProvider(name = "InputterData2")
    public Object[][] Sec_SukukInputterData() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\IBG\\IBG_Sec_FOffice_Sukuk.xlsx";
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

    @DataProvider(name = "AuthorizationData2")
    public Object[][] authData2() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\IBG\\Sec_SukukWithoutPartialRedemptionTxn.xlsx";
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

    @DataProvider(name = "InputterData")
    public Object[][] Sec_SukukInputterDataWithoutRedemption() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\IBG\\IBG_Sec_FOffice_Sukuk.xlsx";
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
    public Object[][] authData() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\IBG\\IBG_Sec_SukukTxn.xlsx";
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
