package Test.Scripts.BackOffice.Conventional;
import POM.PageObject;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Test.General.BaseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SecurityAccountMaster extends BaseClass {

    @Test (groups={"BOInputter"},dataProvider = "SAM_Data")
    public void securityAccountMaster(Map <String, String>testData){

        PageObject.menu_Dropdown("Bond Outright Menu");
        PageObject.menu_Dropdown("Back Office ",3);
        PageObject.menu_Dropdown("Admin Menu ",2);
        PageObject.menu_Dropdown("Portfolio ");
        PageObject.menu_Dropdown("Input Portfolio ");
        PageObject.menu_Link("Create Bond Portfolio  ");
        String homePage = PageObject.switchToChildWindow();
        PageObject.textinput_Locator("transactionId",testData.get("transactionId"));
        PageObject.img_Button("Edit a contract");


        PageObject.textinput_Locator("fieldName:REFERENCE.CURRENCY","PKR");
        PageObject.textinput_Locator("fieldName:ACCOUNT.NAME",testData.get("NAME"));
        PageObject.textinput_Locator("fieldName:INVESTMENT.PROGRAM",testData.get("PROGRAM"));
        PageObject.textinput_Locator("fieldName:MANAGED.ACCOUNT",testData.get("ACCOUNT"));
        PageObject.select_Locator("fieldName:PORTFOLIO.TYPE",testData.get("INVESTMENT.TYPE"));
        PageObject.textinput_Locator("fieldName:PRICE.TYPE",testData.get("TYPE"));
//        PageObject.select_Locator("fieldName:INVESTMENT.TYPE",testData.get("INVESTMENT.TYPE"));
        PageObject.select_Locator("fieldName:INVESTMENT.TYPE","HFT");
        PageObject.textinput_Locator("fieldName:DEALER.BOOK",testData.get("DEALER.BOOK"));
        PageObject.radiobutton_Locator("radio:tab1:CLEAN.BOOK.COST",Integer.parseInt(testData.get("CLEAN.BOOK.COST")));
        PageObject.radiobutton_Locator("radio:tab1:POST.DISC.UPFRONT",Integer.parseInt(testData.get("DISC.UPFRONT")));
        PageObject.radiobutton_Locator("radio:tab1:ACCRUE.DISCOUNT",Integer.parseInt(testData.get("ACCRUE.DISCOUNT")));
        PageObject.radiobutton_Locator("radio:tab1:DISCSOLD.TO.REALPL",Integer.parseInt(testData.get("DISCSOLD.TO.REALPL")));
        PageObject.radiobutton_Locator("radio:tab1:CHECK.LIMIT",Integer.parseInt(testData.get("CHECK.LIMIT")));
        PageObject.form_Tab("Revaluation");
        PageObject.radiobutton_Locator("radio:tab2:REVALUATION",Integer.parseInt(testData.get("REVALUATION")));
        PageObject.select_Locator("fieldName:POST.UNREAL.PL","NONE");

        driver.findElement(By.xpath("//input[@id='fieldName:UNREAL.PL.CAT.PRT']")).clear();
        driver.findElement(By.xpath("//input[@id='fieldName:UNREAL.PL.CAT.LOSS']")).clear();

        try {
            PageObject.commitDeal("SecurityAccountMaster");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Test(groups = {"BOAuthorizer"},dataProvider = "SAM_Data_auth")
    public void securityAccountMaster_Authorization(Map <String,String> testData) throws IOException, InterruptedException {

        PageObject.menu_Dropdown("Bond Outright Menu");
        PageObject.menu_Dropdown("Back Office ",3);
        PageObject.menu_Dropdown("Admin Menu ",2);
        PageObject.menu_Dropdown("Portfolio ");
        PageObject.menu_Dropdown("Input Portfolio ");
        PageObject.menu_Link("Create Bond Portfolio  ");
        String homePage = PageObject.switchToChildWindow();
//        PageObject.textinput_Locator("transactionId","999999-77");
        PageObject.textinput_Locator("transactionId",testData.get("Transaction Number"));
        PageObject.img_Button("Perform an action on the contract");
        PageObject.img_Button("Authorises a deal");

    }


    @DataProvider(name = "SAM_Data")
    public Object[][] readExcelData_1() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\SecurityAcMaster.xlsx";

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

    @DataProvider(name = "SAM_Data_auth")
    public Object[][] readExcelData_2() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\SecurityAccountMaster.xlsx";

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
