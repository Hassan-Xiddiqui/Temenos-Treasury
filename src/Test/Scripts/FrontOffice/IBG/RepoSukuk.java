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

public class RepoSukuk extends BaseClass {
    static String TC;
    static String Txn;


    @Test(groups = {"IBGInputter"}, dataProvider = "excelsheet")
    public void RepoSukuk(Map<String, String> column) throws IOException, InterruptedException {

        FX_SpotDeal.TC = column.get("TC");

        //Menu Navigation

        PageObject.menu_Dropdown("Repo Menu");
        PageObject.menu_Dropdown("Front Office",4);
        PageObject.menu_Link("Mudaraba Acceptance LCY ");
//        PageObject.menu_Dropdown("Input Forex Deals");
//        PageObject.childmenu_Link("Input Forex Ready ",1);


        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(4);
        PageObject.img_Button("New Deal");

        //After opening of the new form

        PageObject.textinput_Locator("fieldName:COUNTERPARTY", column.get("COUNTERPARTY"));
        PageObject.click_Locator("fieldName:REPO.TYPE");

        String menu = PageObject.switchToChildWindow();
        driver.close();
        PageObject.switchToParentWindow(menu);
        PageObject.switchFrame(4);

        PageObject.textinput_Locator("fieldName:REPO.TYPE", column.get("REPO.TYPE"));
//        PageObject.textinput_Locator("fieldName:DEAL.DATE", column.get("fieldName:TRADE.DATE"));
        PageObject.textinput_Locator("fieldName:MATURITY.DATE", column.get("MATURITY.DATE"));
//        PageObject.textinput_Locator("fieldName:LIMIT.REFERENCE", column.get("LIMIT.REFERENCE"));

        PageObject.radiobutton_Locator("radio:tab1:OMO.DISCOUNT",1);


        PageObject.textinput_Locator("fieldName:NEW.SEC.CODE:1",column.get("NEW.SEC.CODE:1"));
        PageObject.textinput_Locator("fieldName:NEW.NOMINAL:1",column.get("NEW.NOMINAL:1"));
        PageObject.textinput_Locator("fieldName:MARGIN.PORTFOLIO",column.get("MARGIN.PORTFOLIO"));
        PageObject.textinput_Locator("fieldName:REPO.RATE",column.get("REPO.RATE"));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.MODE",3);
        PageObject.textinput_Locator("fieldName:DEAL.METHOD",column.get("DEAL.METHOD"));



//        PageObject.img_Button("Prints the deal slip");
        menu = PageObject.switchToChildWindow();
//        driver.close();
        PageObject.switchToParentWindow(menu);
        PageObject.switchFrame(4);
        PageObject.commitDealIBG("RepoSukuk");
//        commitDeal();
//        txnValidate();
//        saveToDS("UnAuth_Transcations");
    }

    @Test(groups = {"IBGAuthorizer"}, dataProvider = "CP")
    public void Authorization(Map<String, String> column) throws IOException {

//        FX_SpotDeal.TC = column.get("TC");
        String repo_sukuk = column.get("Transaction Number");

        //Menu Navigation

        PageObject.menu_Dropdown("Repo Menu");
        PageObject.menu_Dropdown("Back Office",4);
        PageObject.menu_Link("Repo Mudaraba Authorization ");

        String mainWindow = PageObject.switchToChildWindow();

        PageObject.textinput_Locator("value:1:1:1", repo_sukuk);
        PageObject.find_Button();

        String auth = PageObject.switchToChildWindow();
//        PageObject.switchFrame(0);

        PageObject.select_Locator("drillbox:1_1","Authorise");
        PageObject.img_Button("Select Drilldown");

        String auth2 = PageObject.switchToChildWindow();
//        PageObject.switchFrame(1);

        authorizeDeal();
//        saveToDS("REPORT");

    }

    public static void commitDeal() throws IOException {
        driver.findElement(By.xpath("//tr/td/a/img[@alt='Validate a deal']")).click();
        driver.findElement(By.xpath("//tr/td/a/img[@alt='Commit the deal']")).click();
        if (driver.getPageSource().contains("Txn Complete:")) {
            txnValidate();
        } else {
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
        Assert.assertTrue(Txn.isDisplayed(), "Transaction Un-Successful");
        String Transaction = Txn.getText();
        String[] first = Transaction.split(":");
        String[] second = first[1].split(" ");
        FX_SpotDeal.Txn = second[1];
        System.out.println("Transaction Number is: " + FX_SpotDeal.Txn);
    }

    public static void saveToDS(String testCaseName) throws IOException {
        File file = new File(System.getProperty("user.dir") + "\\Excel Data\\IBG\\" + testCaseName + ".xlsx");
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

    public static void authorizeDeal() throws IOException {

        driver.findElement(By.xpath("//tr/td/a/img[@alt='Authorises a deal']")).click();
        if (driver.getPageSource().contains("Txn Complete:")) {
            txnValidate();
        } else {
            try {
                WebElement acpOverride = driver.findElement(By.xpath("//tr/td/a[text()='Accept Overrides']"));
                acpOverride.click();
                txnValidate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    @DataProvider(name = "excelsheet")
    public Object[][] data() throws IOException {
        String FILE_PATH = System.getProperty("user.dir") + "\\Excel Data\\Repo.xlsx";
        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
        int rowCount = sheet.getPhysicalNumberOfRows();
        rowCount = 2;
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
        String FILE_PATH = System.getProperty("user.dir") + "\\Data\\IBG\\RepoSukuk.xlsx";
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

//    String FILE_PATH = System.getProperty("user.dir") + "\\Data\\RepoSukuk.xlsx";

    @Test(groups = {"IBGInputter"}, dataProvider = "RepoSukukEdit")
    public void RepoSukukEdit(Map<String, String> column) throws IOException, InterruptedException {

        String repo_sukuk = column.get("Transaction Number");

//        FX_SpotDeal.TC = column.get("TC");
        System.out.println(Txn);

        PageObject.menu_Dropdown("Repo Menu");
        PageObject.menu_Dropdown("Front Office",4);
        PageObject.menu_Link("Mudaraba Acceptance LCY ");

        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(4);

        PageObject.textinput_Locator("transactionId",repo_sukuk);
        PageObject.img_Button("Edit a contract");

        String mainWindow1 = PageObject.switchToChildWindow();
        PageObject.switchFrame(4);
        Thread.sleep(3000);
        PageObject.img_Button("Return to application screen");


    }

    @DataProvider(name = "RepoSukukEdit")
    public Object[][] RepoSukukEdit() throws IOException {
        String FILE_PATH = System.getProperty("user.dir") + "\\Data\\IBG\\RepoSukuk.xlsx";

        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
        int rowCount = sheet.getPhysicalNumberOfRows();
//        rowCount-=2;
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

    @Test(groups = {"IBGInputter"}, dataProvider = "RepoSukukDelete")
    public void RepoSukukDelete(Map<String, String> column) throws IOException, InterruptedException {

        String repo_sukuk = column.get("Transaction Number");

//        FX_SpotDeal.TC = column.get("TC");

        PageObject.menu_Dropdown("Repo Menu");
        PageObject.menu_Dropdown("Front Office",4);
        PageObject.menu_Link("Mudaraba Acceptance LCY ");

        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(4);

        PageObject.textinput_Locator("transactionId",repo_sukuk);

        PageObject.img_Button("Perform an action on the contract");
//        PageObject.img_Button("Deletes a Deal");


    }
    @DataProvider(name = "RepoSukukDelete")
    public Object[][] RepoSukukDelete() throws IOException {
        String FILE_PATH = System.getProperty("user.dir") + "\\Data\\IBG\\RepoSukuk.xlsx";

        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
        int rowCount = sheet.getPhysicalNumberOfRows();
//        rowCount-=2;
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