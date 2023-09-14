package Test.Scripts.FrontOffice.IBG;

import POM.PageObject;
import Test.General.BaseClass;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FX_Promise extends BaseClass {
    static String TC;
    static String Txn;
    @Test(groups = {"IBGInputter"}, dataProvider = "excelsheet")
    public void spotDealInputterIBG(Map<String, String> column) throws IOException, InterruptedException {

        FX_SpotDeal.TC = column.get("TC");

        //Menu Navigation

        PageObject.menu_Dropdown("Treasury Menu"); //Treasury Menu
        PageObject.menu_Dropdown("Front Office");
        PageObject.menu_Dropdown("Forex Trader");
        PageObject.menu_Dropdown("Input Forex Deals");
        PageObject.childmenu_Link("Input Forex Promise ",1);

        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(4);

        //After opening of the new form

        PageObject.textinput_Locator("fieldName:COUNTERPARTY",column.get("COUNTERPARTY"));
        PageObject.click_Locator("fieldName:CURRENCY.BOUGHT");

        String menu = PageObject.switchToChildWindow();
        driver.close();
        PageObject.switchToParentWindow(menu);
        PageObject.switchFrame(4);

        PageObject.textinput_Locator("fieldName:DEAL.DATE",column.get("DEAL_DATE"));
//        PageObject.textinput_Locator("fieldName:DEALER.DESK",column.get("DEALER_DESK"));
        PageObject.textinput_Locator("fieldName:CURRENCY.BOUGHT",column.get("CURRENCY_BOUGHT"));
        PageObject.textinput_Locator("fieldName:AMOUNT.BOUGHT",column.get("AMOUNT_BOUGHT"));
        PageObject.textinput_Locator("fieldName:VALUE.DATE.BUY",column.get("VALUE_DATE_BUY"));
        PageObject.textinput_Locator("fieldName:CURRENCY.SOLD",column.get("CURRENCY_SOLD"));
        PageObject.textinput_Locator("fieldName:AMOUNT.SOLD",column.get("AMOUNT_SOLD"));
        PageObject.textinput_Locator("fieldName:VALUE.DATE.SELL",column.get("VALUE_DATE_SELL"));
        PageObject.textinput_Locator("fieldName:FORWARD.RATE",column.get("FORWARD_RATE"));
        PageObject.textinput_Locator("fieldName:LIMIT.REFERENCE.NO",column.get("LIMIT_REFERENCE_NO"));
        PageObject.radiobutton_Locator("radio:mainTab:YES.NO",1);
        PageObject.textinput_Locator("fieldName:BROKER",column.get("BROKER"));
        PageObject.click_Locator("fieldName:BROKERAGE.AMOUNT");

//        PageObject.textinput_Locator("fieldName:BROKERAGE.AMOUNT",column.get("BROKERAGE_AMOUNT"));

        PageObject.textinput_Locator("fieldName:DEALER.NOTES:1",column.get("DEALER_NOTES"));
        PageObject.textinput_Locator("fieldName:SWIFT.BIC",column.get("SWIFT_BIC"));
        PageObject.select_Locator("fieldName:SETTLED",column.get("SETTLED"));
//        PageObject.textinput_Locator("fieldName:OUR.ACCOUNT.PAY:1",column.get("OUR_ACCOUNT_PAY"));
//        PageObject.textinput_Locator("fieldName:OUR.ACCOUNT.REC:1",column.get("OUR_ACCOUNT_REC"));
//        PageObject.textinput_Locator("fieldName:CPARTY.CORR.NO:1",column.get("CPARTY_CORR_NO"));
//        PageObject.textinput_Locator("fieldName:CPY.CORR.ADD:1:1",column.get("CPY_CORR_ADD"));
//        PageObject.textinput_Locator("fieldName:CPARTY.BANK.ACC:1",column.get("CPARTY_BANK_ACC"));
//        PageObject.radiobutton_Locator("radio:tab1:IS.FXCRS",1);

        PageObject.commitDealIBG("PromiseDeal");
//        commitDeal();
//        txnValidate();
//        saveToDS("UnAuth_Transcations");
    }
    @Test (groups = {"IBGAuthorizer"}, dataProvider = "CP")
    public void Authorization(Map<String, String> column) throws IOException {

        FX_SpotDeal.TC = column.get("TC");
        FX_SpotDeal.Txn = column.get("Transaction Number");
        System.out.println(FX_SpotDeal.TC);
        System.out.println(FX_SpotDeal.Txn);

        //Menu Navigation

        PageObject.menu_Dropdown("Forex Menu");
        PageObject.menu_Dropdown("Back Office");
        PageObject.menu_Dropdown("Forex ");
        PageObject.menu_Dropdown("Auth/Modify/Rev/Del Forex Deals ");
        PageObject.menu_Link("Authorise/Modify/Delete Forex Deals  ");

        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(0);

        //After opening of the new form

        PageObject.img_Button("Selection Screen");

        String query = PageObject.switchToChildWindow();
        PageObject.switchFrame(0);

//        PageObject.textinput_Locator("value:1:1:1","768787");
        PageObject.textinput_Locator("value:1:1:1",FX_SpotDeal.Txn);
        PageObject.find_Button();

        String auth = PageObject.switchToChildWindow();
        PageObject.switchFrame(0);

        PageObject.img_Button("Select Drilldown");

        String auth2 = PageObject.switchToChildWindow();
        PageObject.switchFrame(1);

        PageObject.authorizeDeal();
        saveToDS("REPORT");

    }
    public static void commitDeal () throws IOException {
        driver.findElement(By.xpath("//tr/td/a/img[@alt='Validate a deal']")).click();
        driver.findElement(By.xpath("//tr/td/a/img[@alt='Commit the deal']")).click();
        if (driver.getPageSource().contains("Txn Complete:")){
            txnValidate();
        }else {
            try {
                WebElement acpOverride = driver.findElement(By.xpath("//tr/td/a[text()='Accept Overrides']"));
                acpOverride.click();
                txnValidate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
    public static void txnValidate() throws IOException {
        WebElement Txn = driver.findElement(By.xpath("//table/tbody/tr/td[contains(text(),'Txn Complete:')]"));
        Assert.assertTrue(Txn.isDisplayed(),"Transaction Un-Successful");
        String Transaction = Txn.getText();
        String[] first = Transaction.split(":");
        String[] second = first[1].split(" ");
        FX_SpotDeal.Txn = second[1];
        System.out.println("Transaction Number is: "+ FX_SpotDeal.Txn);
    }
    public static void saveToDS(String testCaseName) throws IOException {
        File file = new File(System.getProperty("user.dir") + "\\Excel Data\\IBG\\PromiseDeal.xlsx");
        XSSFWorkbook workbook;
        Row row;
        Cell cell;
        int rowNum = 0;

        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            rowNum = sheet.getLastRowNum() + 1; // Start writing from the next row
        } else {
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("TC");
            cell = row.createCell(1);
            cell.setCellValue("TRANSACTION_NUMBER");
        }

        Sheet sheet = workbook.getSheetAt(0);
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue(TC);
        cell = row.createCell(1);
        cell.setCellValue(Txn);

        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();

    }

    @DataProvider(name = "excelsheet")
    public Object[][] data() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\IBG\\ForexPromise.xlsx";
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
    @DataProvider(name = "CP")
    public Object[][] Unauth() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\IBG\\PromiseDeal.xlsx";
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

