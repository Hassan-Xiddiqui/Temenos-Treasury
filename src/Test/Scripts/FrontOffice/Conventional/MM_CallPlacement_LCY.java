package Test.Scripts.FrontOffice.Conventional;

import POM.PageObject;
import Test.General.BaseClass;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.devtools.v85.page.Page;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.print.attribute.standard.PagesPerMinute;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MM_CallPlacement_LCY extends BaseClass {

    @Test(groups = {"Inputter"}, dataProvider = "CallPlacementLCYData")
    public static void CallPlacementLCYDealInputter(Map<String, String> testData)  {

        PageObject.menu_Dropdown("Capture Current MM deals");
        PageObject.menu_Dropdown("Input Deal ");
        PageObject.menu_Link("LCY Call Placements  ");

        //Switching to newly opened form
        String mainPageAfterLogin = PageObject.switchToChildWindow();

        //Form has been opened, switch to the frame
        PageObject.switchFrame(0);

        //inputting the values

        //PageObject.textinput_Locator("fieldName:CUSTOMER.ID","10000055");
        PageObject.textinput_Locator("fieldName:CUSTOMER.ID",testData.get("CounterParty"));
        //Click locator
        PageObject.click_Locator("fieldName:CATEGORY");

        String formPage = PageObject.switchToChildWindow();

        driver.close();
        PageObject.switchToParentWindow(formPage);
        PageObject.switchFrame(0);


//        PageObject.textinput_Locator("fieldName:CATEGORY","21077");
        PageObject.click_Locator("fieldName:PRINCIPAL");
        PageObject.textinput_Locator("fieldName:CATEGORY",testData.get("Category"));
        PageObject.textinput_Locator("fieldName:PRINCIPAL",testData.get("Amount"));
        PageObject.textinput_Locator("fieldName:INTEREST.RATE",testData.get("InterestRate"));
//        PageObject.textinput_Locator("fieldName:VALUE.DATE",testData.get("ValueDate"));
        PageObject.textinput_Locator("fieldName:MATURITY.DATE",testData.get("MaturityDate"));
        PageObject.click_Locator("fieldName:LIMIT.REFERENCE");
        PageObject.textinput_Locator("fieldName:LIMIT.REFERENCE","");
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",Integer.parseInt(testData.get("dealSettlement")));
        PageObject.textinput_Locator("fieldName:DEAL.METHOD",testData.get("dealMethod"));

        //Commit
        try {
            PageObject.commitDealFrontOffice("CallPlacementLCYDeal",formPage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @DataProvider(name = "CallPlacementLCYData")
    public Object[][] data() throws IOException {

        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\CallPlacementLCY.xlsx";
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

    @Test(groups = {"BOAuthorizer"}, dataProvider = "CallPlacementLCYDealAuthData")
    public static void CallPlacementLCYDealAuth(Map<String, String> testData)  {

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


    @DataProvider(name = "CallPlacementLCYDealAuthData")
    public Object[][] dataAuth() throws IOException {

        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\CallPlacementLCYDeal.xlsx";
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
