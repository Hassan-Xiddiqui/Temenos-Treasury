package Test.Scripts.FrontOffice.Conventional;

import POM.PageObject;
import Test.General.BaseClass;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Treasury_Bill extends BaseClass {

    @Test(groups = {"Inputter"}, dataProvider = "excelDataTBillSale")
        public void TreasuryBillSale(Map<String, String> testData) throws IOException,InterruptedException{
        PageObject.menu_Dropdown("Capture Current Bond deals");
        PageObject.menu_Dropdown("Treasury Bills ");
        PageObject.menu_Dropdown("Secondary Market Transaction ", 1);
        PageObject.childmenu_Link("Outright Transaction ",2);
        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(0);


//        PageObject.textinput_Locator("fieldName:SECURITY.CODE", "000401-390");
//        PageObject.textinput_Locator("fieldName:DEPOSITORY", "10000272");
//        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",3);
//        PageObject.radiobutton_Locator("radio:tab1:RP.PRICE.UPDATE",2);
////        PageObject.textinput_Locator("fieldName:DEAL.METHOD", testData.get("Deal Method"));
//        PageObject.textinput_Locator("fieldName:DEAL.METHOD", "DR");
//        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", "SSL");
//        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", "999999-18");
//        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", "123");
//        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", "10000186");

        PageObject.textinput_Locator("fieldName:SECURITY.CODE", testData.get("SecurityCode"));
        PageObject.textinput_Locator("fieldName:DEPOSITORY", testData.get("Depository"));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",3);
        PageObject.radiobutton_Locator("radio:tab1:RP.PRICE.UPDATE",2);

        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", testData.get("noNOM"));
        PageObject.textinput_Locator("fieldName:DEAL.METHOD", testData.get("DealMethod"));
        PageObject.textinput_Locator("fieldName:DEAL.METHOD", "DR");
        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", testData.get("Ssale"));
        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", testData.get("SecAcc"));
        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", testData.get("CounterParty"));


        PageObject.click_Locator("fieldName:CUST.NO.NOM:1:1");
        String form = PageObject.switchToChildWindow();
        driver.close();
        PageObject.switchToParentWindow(form);
        PageObject.switchFrame(0);
        PageObject.commitDeal("TreasuryBillSale");
        PageObject.switchToChildWindow();
        driver.close();
    }

    @Test(groups = {"Inputter"}, dataProvider = "excelDataTBillPurchase")
     public void TreasuryBillPurchase(Map<String, String> testData) throws IOException,InterruptedException{
        PageObject.menu_Dropdown("Capture Current Bond deals");
        PageObject.menu_Dropdown("Treasury Bills ");
        PageObject.menu_Dropdown("Secondary Market Transaction ", 1);
        PageObject.childmenu_Link("Outright Transaction ",2);
        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(0);
//        PageObject.textinput_Locator("fieldName:SECURITY.CODE", "000401-218");

//        PageObject.textinput_Locator("fieldName:SECURITY.CODE", "000401-460");
//        PageObject.textinput_Locator("fieldName:DEPOSITORY", "10000272");
//        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",3);
//        PageObject.radiobutton_Locator("radio:tab1:RP.PRICE.UPDATE",2);
//        PageObject.textinput_Locator("fieldName:DEAL.METHOD", "DR");
//        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", "SPR");
//        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", "999999-19");
//        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", "666");
//        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", "10000131");

        PageObject.textinput_Locator("fieldName:SECURITY.CODE", testData.get("SecurityCode"));
        PageObject.textinput_Locator("fieldName:DEPOSITORY", testData.get("Depository"));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",3);
        PageObject.radiobutton_Locator("radio:tab1:RP.PRICE.UPDATE",2);
        PageObject.textinput_Locator("fieldName:DEAL.METHOD", testData.get("DealMethod"));
        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", testData.get("Ssale"));

        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", testData.get("SecAcc"));
        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", testData.get("noNOM"));
        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", testData.get("CounterParty"));

        PageObject.commitDeal("TreasuryBillPurchase");
        PageObject.switchToChildWindow();
        driver.close();
    }

    @Test(groups = {"Inputter"}, dataProvider = "excelData")
    public void TreasuryBillEdit(Map<String, String> testData) throws IOException,InterruptedException {
        PageObject.menu_Dropdown("Capture Current Bond deals");
        PageObject.menu_Dropdown("Treasury Bills ");
        PageObject.menu_Dropdown("Secondary Market Transaction ", 1);
        PageObject.childmenu_Link("Outright Transaction ", 2);
        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(0);
        driver.findElement(By.xpath("//img[@alt='Return to application screen']")).click();
        PageObject.textinput_Locator("transactionId", testData.get("Transaction Number"));
        PageObject.img_Button("Edit a contract");
     }

    @Test(groups = {"Inputter"}, dataProvider = "excelData")
    public void TreasuryBillDelete(Map<String, String> testData) throws IOException,InterruptedException {
        PageObject.menu_Dropdown("Capture Current Bond deals");
        PageObject.menu_Dropdown("Treasury Bills ");
        PageObject.menu_Dropdown("Secondary Market Transaction ", 1);
        PageObject.childmenu_Link("Outright Transaction ", 2);
        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(0);
        driver.findElement(By.xpath("//img[@alt='Return to application screen']")).click();
        PageObject.textinput_Locator("transactionId", testData.get("Transaction Number"));
        driver.findElement(By.xpath("//img[@alt='Perform an action on the contract']")).click();
        driver.findElement(By.xpath("//img[@alt='Deletes a Deal']")).click();
        driver.switchTo().alert().accept();
   }

    @Test(groups = {"BOAuthorizer"}, dataProvider = "excelData")
    public void TreasuryBillSaleAuth(Map<String, String> testData) throws IOException,InterruptedException{
        PageObject.menu_Dropdown("Bond Outright Menu");
        PageObject.menu_Dropdown("Back Office ", 3);
        PageObject.menu_Dropdown("Treasury Bills ", 1);
        PageObject.menu_Dropdown("Authorization Menu ", 2);
        PageObject.childmenu_Link("Authorization of New/Reversal Outright Trade ", 2);

        String menu = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();
        PageObject.switchFrame(0);
        PageObject.img_Button("Selection Screen");
        PageObject.textinput_Locator("value:1:1:1","");
        PageObject.textinput_Locator("value:1:1:1",testData.get("Transaction Number"));
        PageObject.find_Button();
        PageObject.img_Button("Select Drilldown");
        PageObject.parentFrame();
        PageObject.switchFrame(1);
        PageObject.authorizeDeal();
    }

    @Test(groups = {"BOAuthorizer"}, dataProvider = "excelDataPur")
    public void TreasuryBillPurchaseAuth(Map<String, String> testData) throws IOException,InterruptedException{
     PageObject.menu_Dropdown("Bond Outright Menu");
     PageObject.menu_Dropdown("Back Office ", 3);
     PageObject.menu_Dropdown("Treasury Bills ", 1);
     PageObject.menu_Dropdown("Authorization Menu ", 2);
     PageObject.childmenu_Link("Authorization of New/Reversal Outright Trade ", 2);

     String menu = PageObject.switchToChildWindow();
     PageObject.maximizeWindow();
     PageObject.switchFrame(0);
     PageObject.img_Button("Selection Screen");
     PageObject.textinput_Locator("value:1:1:1","");
     PageObject.textinput_Locator("value:1:1:1",testData.get("Transaction Number"));
     PageObject.find_Button();
     PageObject.img_Button("Select Drilldown");
     PageObject.parentFrame();
     PageObject.switchFrame(1);
     PageObject.authorizeDeal();
 }

    @DataProvider(name = "excelData")
    public Object[][] readExcelData() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\TreasuryBillSale.xlsx";

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

    @DataProvider(name = "excelDataPur")
    public Object[][] readExcelDataPur() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\TreasuryBillPurchase.xlsx";

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

    @DataProvider(name = "excelDataTBillSale")
    public Object[][] readExcelDataDealMethod() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\Sec_FO_PIB.xlsx";

        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("TBill_Sale"); // Assuming data is in the first sheet
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

    @DataProvider(name = "excelDataTBillPurchase")
    public Object[][] excelDataMethod() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\Sec_FO_PIB.xlsx";

        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("TBill_Purchase"); // Assuming data is in the first sheet
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
