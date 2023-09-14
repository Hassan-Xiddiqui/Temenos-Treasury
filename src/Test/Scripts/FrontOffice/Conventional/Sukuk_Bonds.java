package Test.Scripts.FrontOffice.Conventional;

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

public class Sukuk_Bonds extends BaseClass {

    @Test(groups = {"Inputter"}, dataProvider = "excelDataSukukSale")
    public void sukukbondSale(Map<String, String> testData) throws IOException,InterruptedException{
        PageObject.menu_Dropdown("Capture Current Bond deals");
        PageObject.menu_Dropdown("Sukuk Bonds ");
        PageObject.menu_Dropdown("Secondary Market Transaction ", 3);
        PageObject.childmenu_Link("Outright Transaction ",6);

        PageObject.parentFrame();
        PageObject.switchFrame(4);
        String pgnameo = driver.getWindowHandle();

        PageObject.img_Button("New Deal");
        //        PageObject.textinput_Locator("fieldName:SECURITY.CODE", "000400-741");
//            PageObject.textinput_Locator("fieldName:DEPOSITORY", testData.get("Depository"));
//        PageObject.textinput_Locator("fieldName:DEPOSITORY", "10000272");

//        PageObject.textinput_Locator("fieldName:DEAL.METHOD", testData.get("Deal Method"));

//        PageObject.textinput_Locator("fieldName:DEAL.METHOD", "DR");
        //        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", "SSL");
//        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", "999999-45");
//
//        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", "666");
//
//        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", "10000186");
//        PageObject.click_Locator("fieldName:CUST.NO.NOM:1:1");
        PageObject.textinput_Locator("fieldName:SECURITY.CODE", testData.get("SecurityCode"));
        PageObject.textinput_Locator("fieldName:DEPOSITORY", testData.get("Depository"));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",3);
        PageObject.radiobutton_Locator("radio:tab1:RP.PRICE.UPDATE",2);

        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", testData.get("noNOM"));
        PageObject.textinput_Locator("fieldName:DEAL.METHOD", testData.get("DealMethod"));
        PageObject.textinput_Locator("fieldName:DEAL.METHOD", "DR");
        Thread.sleep(5000);
        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", testData.get("Ssale"));
        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", testData.get("SecAcc"));
        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", testData.get("CounterParty"));

        PageObject.click_Locator("fieldName:CUST.NO.NOM:1:1");
        String form = PageObject.switchToChildWindow();
        driver.close();
        PageObject.switchToParentWindow(pgnameo);
        PageObject.switchFrame(4);
        PageObject.commitDeal("SukukBondSale");

    }

    @DataProvider(name = "excelDataSukukSale")
    public Object[][] readExcelData3() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\Sec_FO_PIB.xlsx";

        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("sukuk"); // Assuming data is in the first sheet
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

    @Test(groups = {"Inputter"}, dataProvider = "excelDataSukukPurchase")
    public void sukukbondPurchase(Map<String, String> testData) throws IOException,InterruptedException{
        PageObject.menu_Dropdown("Capture Current Bond deals");
        PageObject.menu_Dropdown("Sukuk Bonds ");
        PageObject.menu_Dropdown("Secondary Market Transaction ", 3);
        PageObject.childmenu_Link("Outright Transaction ",6);

        PageObject.parentFrame();
        PageObject.switchFrame(4);
        String pgnameo = driver.getWindowHandle();

        PageObject.img_Button("New Deal");

        PageObject.textinput_Locator("fieldName:SECURITY.CODE", testData.get("SecurityCode"));
        PageObject.textinput_Locator("fieldName:DEPOSITORY", testData.get("Depository"));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",3);
        PageObject.radiobutton_Locator("radio:tab1:RP.PRICE.UPDATE",2);

        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", testData.get("noNOM"));
        PageObject.textinput_Locator("fieldName:DEAL.METHOD", testData.get("DealMethod"));
        PageObject.textinput_Locator("fieldName:DEAL.METHOD", "DR");
        Thread.sleep(5000);
        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", testData.get("Ssale"));
        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", testData.get("SecAcc"));
        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", testData.get("CounterParty"));


//        PageObject.textinput_Locator("fieldName:SECURITY.CODE", "000400-741");
//        PageObject.textinput_Locator("fieldName:DEPOSITORY", "10000272");
//        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",3);
//        PageObject.radiobutton_Locator("radio:tab1:RP.PRICE.UPDATE",2);
////        PageObject.textinput_Locator("fieldName:DEAL.METHOD", testData.get("Deal Method"));
//        PageObject.textinput_Locator("fieldName:DEAL.METHOD", "DR");
//        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", "SPR");
//        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", "999999-45");
//        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", "666");
//        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", "10000186");




        PageObject.click_Locator("fieldName:CUST.NO.NOM:1:1");
        PageObject.switchToChildWindow();
        driver.close();
        PageObject.switchToParentWindow(pgnameo);
        PageObject.switchFrame(4);
        PageObject.commitDeal("SukukBondPurchase");

    }

    @DataProvider(name = "excelDataSukukPurchase")
    public Object[][] readExcelData5() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\Sec_FO_PIB.xlsx";

        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("sukuk_Purchase"); // Assuming data is in the first sheet
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

    @Test(groups = {"BOAuthorizer"}, dataProvider = "excelData")
    public void sukukbondSaleAuth(Map<String, String> testData) throws IOException,InterruptedException{
        PageObject.menu_Dropdown("Bond Outright Menu");
        PageObject.menu_Dropdown("Back Office ", 3);
        PageObject.menu_Dropdown("Sukuk Bonds ", 1);
        PageObject.menu_Dropdown("Authorization Menu ", 4);
        PageObject.childmenu_Link("Authorization of New/Reversal Outright Trade ", 4);

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
    public void sukukbondPurchaseAuth(Map<String, String> testData) throws IOException,InterruptedException{
       PageObject.menu_Dropdown("Bond Outright Menu");
       PageObject.menu_Dropdown("Back Office ", 3);
       PageObject.menu_Dropdown("Sukuk Bonds ", 1);
        PageObject.menu_Dropdown("Authorization Menu ", 4);
        PageObject.childmenu_Link("Authorization of New/Reversal Outright Trade ", 4);

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
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\SukukBondSale.xlsx";

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
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\SukukBondPurchase.xlsx";

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


    @DataProvider(name = "excelDataDealMethod")
    public Object[][] readExcelDataDealMethod() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\DealMethod_BrokerCode.xlsx";

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
