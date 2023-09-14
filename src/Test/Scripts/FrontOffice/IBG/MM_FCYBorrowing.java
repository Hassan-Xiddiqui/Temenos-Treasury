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

public class MM_FCYBorrowing extends BaseClass {


    @Test(groups = {"IBGInputter"}, dataProvider = "MMFCYBInputterIBGData")
    public void MM_FCYBorrowingInputter(Map<String, String> testData) throws IOException, InterruptedException
    {

        PageObject.menu_Dropdown("Money Market Menu");
        PageObject.menu_Dropdown("Front Office", 2);
        PageObject.menu_Dropdown("Input Deal");
        PageObject.menu_Link("Wakala FCY Borrowing ");

        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(4);

        PageObject.img_Button("New Deal");
        PageObject.select_Locator("fieldName:TR.IBD.DEALTYPE", testData.get("DealType"));
        //PageObject.select_Locator("fieldName:TR.IBD.DEALTYPE", testData.get("DealType"));
        PageObject.click_Locator("fieldName:CUSTOMER.ID");
//        PageObject.textinput_Locator("fieldName:CUSTOMER.ID", testData.get("CustID"));
        driver.findElement(By.xpath("//input[@id='fieldName:CUSTOMER.ID']")).sendKeys(testData.get("CounterParty"));

        PageObject.click_Locator("fieldName:FTD.TYPE");

        String formWindow = PageObject.switchToChildWindow();
        driver.close();

        PageObject.switchToParentWindow(formWindow);
        PageObject.switchFrame(4);


        driver.findElement(By.xpath("//input[@id='fieldName:FTD.TYPE']")).sendKeys(testData.get("FtdType"));
        driver.findElement(By.xpath("//input[@id='fieldName:DEALER.DESK']")).sendKeys("00");
        driver.findElement(By.xpath("//input[@id='fieldName:CATEGORY']")).sendKeys(testData.get("Category"));
        driver.findElement(By.xpath("//input[@id='fieldName:CURRENCY']")).sendKeys(testData.get("Currency"));
        driver.findElement(By.xpath("//input[@id='fieldName:PRINCIPAL']")).sendKeys(testData.get("Amount"));
        driver.findElement(By.xpath("//input[@id='fieldName:INTEREST.SPREAD.1']")).sendKeys(testData.get("InterestRate"));
        driver.findElement(By.xpath("//input[@id='fieldName:VALUE.DATE']")).sendKeys(testData.get("ValueDate"));
        driver.findElement(By.xpath("//input[@id='fieldName:MATURITY.DATE']")).sendKeys(testData.get("MaturityDate"));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT", Integer.parseInt(testData.get("dealSettlement")));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT", Integer.parseInt(testData.get("dealSettlement")));
        driver.findElement(By.xpath("//input[@id='fieldName:DEAL.METHOD']")).sendKeys(testData.get("dealMethod"));
        PageObject.form_Tab("Payment Details");
        PageObject.textinput_Locator("fieldName:PRIN.BEN.BANK.1",testData.get("CounterParty"));
        PageObject.commitDealIBG("IBG_MM_FCYBorrowing");

    }

    @Test (groups = {"IBGAuthorizer"}, dataProvider = "MMFCYBAuthIBGData")
    public void MMFCYAuthIBG(Map<String, String> testData) throws IOException {

//        PageObject.switchFrame(1);
        PageObject.menu_Dropdown_MM_BackOffice("Money Market Menu");
        PageObject.menu_Dropdown_MM_BackOffice("Back Office",2);
        PageObject.menu_Dropdown_MM_BackOffice("Authorization of Money Market ");
        PageObject.menu_Link_MM_BackOffice("Authorization Version - Foreign  ");
        //driver.findElement(By.xpath("//a[text()='Authorization Version - Local  ']")).click();

        String homePage = PageObject.switchToChildWindow();
        PageObject.switchFrame(0);

        PageObject.authorizeByTxn(testData.get("Transaction Number"),"Authorize Foriegn Deal");
//        driver.findElement(By.xpath("//td[text()='"+testData.get("Transaction Number")+"']/following-sibling::td//a[text()='Authorize Foriegn Deal']")).click();

        driver.switchTo().parentFrame();
        PageObject.switchFrame(1);
        PageObject.img_Button("Authorises a deal");

    }



    @DataProvider(name = "MMFCYBAuthIBGData")
    public Object[][] data2() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\IBG\\IBG_MM_FCYBorrowing.xlsx";
        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
        //colCount -=1 ;
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


    @DataProvider(name = "MMFCYBInputterIBGData")
    public Object[][] data() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\IBG\\IBG_CallBorrowingFCY.xlsx";
        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
        int rowCount = sheet.getPhysicalNumberOfRows();
        rowCount =2 ;
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
        colCount -=1 ;
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
