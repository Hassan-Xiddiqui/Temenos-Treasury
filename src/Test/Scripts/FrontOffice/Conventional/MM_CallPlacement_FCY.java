package Test.Scripts.FrontOffice.Conventional;

import POM.PageObject;
import Test.General.BaseClass;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.HashMap;

public class MM_CallPlacement_FCY extends BaseClass {


    @Test(groups = {"Inputter"}, dataProvider = "CallPlacementFCYData")
    public static void CallPlacementDealFCYInputter(Map<String, String> testData)  {

        PageObject.menu_Dropdown("Capture Current MM deals");
        PageObject.menu_Dropdown("Input Deal ");
        PageObject.menu_Link("FCY Placements  ");
        //Switching to newly opened form
        String mainPageAfterLogin = PageObject.switchToChildWindow();

        //Form has been opened, switch to the frame
        PageObject.switchFrame(0);

        //inputting the values

        //PageObject.textinput_Locator("fieldName:CUSTOMER.ID","10000131");
        PageObject.textinput_Locator("fieldName:CUSTOMER.ID",testData.get("CounterParty"));

        //Click locator
        PageObject.click_Locator("fieldName:CURRENCY");

        String formPage = PageObject.switchToChildWindow();

        driver.close();
        PageObject.switchToParentWindow(formPage);
        PageObject.switchFrame(0);


        //PageObject.textinput_Locator("fieldName:CATEGORY","21077");
        PageObject.click_Locator("fieldName:PRINCIPAL");
        PageObject.textinput_Locator("fieldName:CATEGORY",testData.get("Category"));
        PageObject.click_Locator("fieldName:PRINCIPAL");
        PageObject.click_Locator("fieldName:LIMIT.REFERENCE");
        PageObject.textinput_Locator("fieldName:LIMIT.REFERENCE","");
        PageObject.textinput_Locator("fieldName:CURRENCY",testData.get("Currency"));
        PageObject.click_Locator("fieldName:DEAL.METHOD");
        PageObject.textinput_Locator("fieldName:DEAL.METHOD",testData.get("dealMethod"));
        PageObject.textinput_Locator("fieldName:PRINCIPAL",testData.get("Amount"));
        PageObject.textinput_Locator("fieldName:INTEREST.RATE",testData.get("InterestRate"));
//        PageObject.textinput_Locator("fieldName:VALUE.DATE",testData.get("ValueDate"));
        PageObject.textinput_Locator("fieldName:MATURITY.DATE",testData.get("MaturityDate"));
        PageObject.click_Locator("fieldName:LIMIT.REFERENCE");

        //Commit

        try {
            PageObject.commitDealFrontOffice("CallPlacementDealFCY",formPage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @DataProvider(name = "CallPlacementFCYData")
    public Object[][] data() throws IOException {

        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\CallPlacementFCY.xlsx";
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

    @Test(groups = {"BOAuthorizer"}, dataProvider = "CallPlacementDealFCYAuthData")
    public static void CallPlacementDealFCYAuth(Map<String, String> testData)  {

        PageObject.menu_Dropdown("MM Clean Lending/Borrowing Menu");
        PageObject.menu_Dropdown("Back Office ",2);
        PageObject.menu_Dropdown("Authorization of Money Market ");
        //Authorization Version - Local
        //Authorization Version - Foreign
        PageObject.menu_Link("Authorization Version - Local  ");
        //Switching to newly opened form
        String mainPageAfterLogin = PageObject.switchToChildWindow();
        PageObject.switchFrame(0);
        PageObject.authorizeByTxn(testData.get("Transaction Number"));
        driver.switchTo().parentFrame();
        PageObject.switchFrame(1);
        PageObject.img_Button("Authorises a deal");
    }


    @DataProvider(name = "CallPlacementDealFCYAuthData")
    public Object[][] dataAuth() throws IOException {

        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\CallPlacementDealFCY.xlsx";
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
