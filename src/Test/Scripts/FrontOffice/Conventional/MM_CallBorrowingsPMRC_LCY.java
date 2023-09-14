package Test.Scripts.FrontOffice.Conventional;

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


public class MM_CallBorrowingsPMRC_LCY extends BaseClass {

    @Test(groups = {"Inputter"}, dataProvider = "CallBorrowing_PMRCLCYData")
    public static void CallBorrowingDealLCYPMRC(Map<String, String> testData)  {

        PageObject.menu_Dropdown("Capture Current MM deals");
        PageObject.menu_Dropdown("Input Deal ");
        PageObject.menu_Link("LCY Call Borrowings PMRC ");

        //Switching to newly opened form
        String mainPageAfterLogin = PageObject.switchToChildWindow();

        //Form has been opened, switch to the frame
        PageObject.switchFrame(0);

        //inputting the values

       // PageObject.textinput_Locator("fieldName:CUSTOMER.ID","10000186");
        PageObject.textinput_Locator("fieldName:CUSTOMER.ID",testData.get("CounterParty"));


        //Click locator
        PageObject.click_Locator("fieldName:CATEGORY");

        String formPage = PageObject.switchToChildWindow();

        driver.close();
        PageObject.switchToParentWindow(formPage);
        PageObject.switchFrame(0);
        PageObject.click_Locator("fieldName:PRINCIPAL");

        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",Integer.parseInt(testData.get("dealSettlement")));
        PageObject.textinput_Locator("fieldName:DEAL.METHOD",testData.get("dealMethod"));
        PageObject.textinput_Locator("fieldName:PRINCIPAL",testData.get("Amount"));
        PageObject.textinput_Locator("fieldName:INTEREST.RATE",testData.get("InterestRate"));
//        PageObject.textinput_Locator("fieldName:VALUE.DATE",testData.get("ValueDate"));
        PageObject.textinput_Locator("fieldName:MATURITY.DATE",testData.get("MaturityDate"));
        PageObject.click_Locator("fieldName:LIMIT.REFERENCE");


        //Commit
        try {
            PageObject.commitDeal("MM_CallBorrowingLCYPMRC");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test(groups = {"BOAuthorizer"}, dataProvider = "CallBorrowing_PMRCLCYAuth")
    public static void CallPlacementLCYDealPMRCAuth(Map<String, String> testData)  {

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


    @DataProvider(name = "CallBorrowing_PMRCLCYAuth")
    public Object[][] dataAuth() throws IOException {

        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\MM_CallBorrowingLCYPMRC.xlsx";
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



    @DataProvider(name = "CallBorrowing_PMRCLCYData")
    public Object[][] data() throws IOException {

        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\CallBorrowingLCY_PMRC.xlsx";
        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
        colCount-=1;
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
