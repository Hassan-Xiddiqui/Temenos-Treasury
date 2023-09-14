package Test.Scripts.BackOffice.Conventional;

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

public class NostroAccountCreation extends BaseClass {

    @Test(groups = {"BOInputter"}, dataProvider = "NostroAccountCreation")
    public void NostroAccountCreation(Map<String, String> testData) throws IOException, InterruptedException {

        String CustomerID = testData.get("Transaction Number");
        /*String Currency = testData.get("Currency");
        String ExternalAccount = testData.get("ExternalAccount");*/
        String Currency = "USD";
        String ExternalAccount = "1003698521"; //dummy

        PageObject.menu_Dropdown("MM Inter Bank");
        PageObject.menu_Dropdown("Back Office ");
        PageObject.menu_Dropdown("Account Menu ");
        PageObject.menu_Link("Open Nostro Account  ");

        String NewDeal = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();

        PageObject.textinput_Locator("fieldName:CUSTOMER",CustomerID);
        PageObject.textinput_Locator("fieldName:CURRENCY",Currency);
        PageObject.textinput_Locator("fieldName:OUR.EXT.ACCT.NO",ExternalAccount);

        PageObject.commitDeal("NostroAccountCreation");

    }

    @DataProvider(name = "NostroAccountCreation")
    public Object[][] readExcelData_1() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\CustomerCreation.xlsx";

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

    @Test(groups = {"BOAuthorizer"}, dataProvider = "NostroAccountCreation_Authorization")
    public void NostroAccountCreation_Authorization(Map<String, String> testData) throws IOException, InterruptedException {

        String AccountNumber = testData.get("Transaction Number");
        /*String Currency = testData.get("Currency");
        String ExternalAccount = testData.get("ExternalAccount");*/

        PageObject.menu_Dropdown("MM Inter Bank");
        PageObject.menu_Dropdown("Back Office ");
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

    @DataProvider(name = "NostroAccountCreation_Authorization")
    public Object[][] readExcelData_1A() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\NostroAccountCreation.xlsx";

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
