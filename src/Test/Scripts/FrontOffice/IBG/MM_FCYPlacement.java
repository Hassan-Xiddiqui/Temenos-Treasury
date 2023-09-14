package Test.Scripts.FrontOffice.IBG;

import java.awt.*;
import POM.PageObject;
import Test.General.BaseClass;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MM_FCYPlacement extends BaseClass {

    @Test(groups = {"IBGInputter"}, dataProvider = "MMFCYInputterIBGData")
    public void MMFCYInputterIBG(Map<String, String> testData) throws IOException, InterruptedException {

        PageObject.menu_Dropdown("Money Market Menu");
        PageObject.menu_Dropdown("Front Office", 2);
        PageObject.menu_Dropdown("Input Deal");
        PageObject.menu_Link("Wakala FCY Placement  ");

        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(4);

        PageObject.img_Button("New Deal");
        PageObject.select_Locator("fieldName:TR.IBD.DEALTYPE", testData.get("DealType"));
        PageObject.select_Locator("fieldName:TR.IBD.DEALTYPE", testData.get("DealType"));
        PageObject.textinput_Locator("fieldName:CUSTOMER.ID", testData.get("CustID"));
        //PageObject.textinput_Locator("fieldName:CUSTOMER.ID","11845748");

        PageObject.click_Locator("fieldName:FTD.TYPE");

        String formWindow = PageObject.switchToChildWindow();
        driver.close();

        PageObject.switchToParentWindow(formWindow);
        PageObject.switchFrame(4);


        //PageObject.textinput_Locator("fieldName:FTD.TYPE","WAKPKRONPLC");
        PageObject.textinput_Locator("fieldName:FTD.TYPE", testData.get("FtdType"));
        driver.findElement(By.xpath("//input[@id='fieldName:DEALER.DESK']")).sendKeys("00");
        PageObject.textinput_Locator("fieldName:CATEGORY", testData.get("Category"));
        PageObject.textinput_Locator("fieldName:CURRENCY", testData.get("Currency"));
        PageObject.textinput_Locator("fieldName:PRINCIPAL", testData.get("Amount"));
        PageObject.textinput_Locator("fieldName:INTEREST.SPREAD.1", testData.get("Rate"));
        PageObject.textinput_Locator("fieldName:VALUE.DATE", testData.get("VDate"));
        PageObject.textinput_Locator("fieldName:MATURITY.DATE", testData.get("MDate"));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT", Integer.parseInt(testData.get("dealSettlement")));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT", Integer.parseInt(testData.get("dealSettlement")));
        //PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",4);
        PageObject.textinput_Locator("fieldName:DEAL.METHOD", testData.get("dealMethod"));
        PageObject.textinput_Locator("fieldName:LIMIT.REFERENCE", "");

        PageObject.commitDealIBG("IBG_MM_FCYPlacement");
    }


    @Test (groups = {"IBGAuthorizer"},dataProvider = "MMFCYAuthIBGData")
    public void MMFCYAuthIBG(Map <String , String> testData) throws IOException {

//        PageObject.switchFrame(1);
        PageObject.menu_Dropdown_MM_BackOffice("Money Market Menu");
        PageObject.menu_Dropdown_MM_BackOffice("Back Office",2);
        PageObject.menu_Dropdown_MM_BackOffice("Authorization of Money Market ");
        PageObject.menu_Link_MM_BackOffice("Authorization Version - Foreign  ");
        //driver.findElement(By.xpath("//a[text()='Authorization Version - Local  ']")).click();

        String homePage = PageObject.switchToChildWindow();
        PageObject.switchFrame(0);

//        String authWindow = driver.getWindowHandle();
//
//        Robot robot = null;
//        try {
//            robot = new Robot();
//        } catch (AWTException e) {
//
//            e.printStackTrace();
//        }
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_F);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        robot.keyRelease(KeyEvent.VK_F);
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        StringSelection stringSelection = new StringSelection("MM2300306420");
//        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//        clipboard.setContents(stringSelection, null);
//
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_V);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        robot.keyRelease(KeyEvent.VK_V);
//
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        PageObject.switchToParentWindow(authWindow);

        PageObject.authorizeByTxn(testData.get("Transaction Number"),"Authorize Foriegn Deal");
//        driver.findElement(By.xpath("//td[text()='"+testData.get("Transaction Number")+"']/following-sibling::td//a[text()='Authorize Foriegn Deal']")).click();
        driver.switchTo().parentFrame();
        PageObject.switchFrame(1);
        PageObject.img_Button("Authorises a deal");

    }


    @DataProvider(name = "MMFCYAuthIBGData")
    public Object[][] data2() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\IBG\\IBG_MM_FCYPlacement.xlsx";
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


    @DataProvider(name = "MMFCYInputterIBGData")
    public Object[][] data() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\IBG\\IBG_MM_FCYPlacement.xlsx";
        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
        int rowCount = sheet.getPhysicalNumberOfRows();
//        rowCount=2;
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

