package Test.Scripts.BackOffice.IBG;

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

public class NonCheckingAccountCreation extends BaseClass {


    @Test(groups = {"BOInputterIBG"}, dataProvider = "InputterData")
    public void NonCheckingAccountCreation(Map<String, String> testData) throws IOException, InterruptedException {

        String CustomerID = testData.get("Transaction Number");

        PageObject.menu_Dropdown("Forex Menu");
        PageObject.menu_Dropdown("Back Office",1);
        PageObject.menu_Dropdown("Account Menu ");
        PageObject.menu_Link("Non-Checking Account ");

        String homePage = PageObject.switchToChildWindow();
        PageObject.img_Button("New Deal");

        PageObject.textinput_Locator("fieldName:CUSTOMER",CustomerID);
        PageObject.commitDealIBG("IBG_NonCheckingAccountCreationTxn");

    }

    @DataProvider(name = "InputterData")
    public Object[][] readExcelData_1() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\IBG\\IBG_CustomerCreation.xlsx";

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


    @Test(groups = {"IBGAuthorizer"}, dataProvider = "AuthorizerData")
    public void NonCheckingAccountCreation_Authorization(Map<String, String> testData) throws IOException, InterruptedException {

        String AccountNumber = testData.get("Transaction Number");
        /*String Currency = testData.get("Currency");
        String ExternalAccount = testData.get("ExternalAccount");*/

//        PageObject.switchFrame(1);
        PageObject.menu_Dropdown("Forex Menu");
        PageObject.menu_Dropdown("Back Office",1);
        PageObject.menu_Dropdown("Account Menu ");
        PageObject.menu_Link("Authorise/Delete Account  ");

        String NewDeal = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();

        PageObject.switchFrame(0);
        PageObject.img_Button("Selection Screen");
        PageObject.textinput_Locator("value:1:1:1", AccountNumber);
        PageObject.find_Button();

        PageObject.switchToChildWindow();
        PageObject.switchFrame(0);
        driver.findElement(By.xpath("//td[text()='"+ AccountNumber +"']/following-sibling::td//a[text()='Authorise Account']")).click();

        driver.switchTo().parentFrame();
        PageObject.switchFrame(1);
        PageObject.authorizeDeal();

    }

    @DataProvider(name = "AuthorizerData")
    public Object[][] readExcelData_1A() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\IBG\\IBG_NonCheckingAccountCreationTxn.xlsx";

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
