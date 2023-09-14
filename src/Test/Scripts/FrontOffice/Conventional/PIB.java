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

public class PIB extends BaseClass {

    @Test(groups = {"Inputter"}, dataProvider = "excelDataPIBSale")
    public void PIBSale(Map<String, String> testData) throws IOException, InterruptedException {

        PageObject.menu_Dropdown("Capture Current Bond deals");
        PageObject.menu_Dropdown("Pakistan Investment Bonds ");
        PageObject.menu_Dropdown("Secondary Market Transaction ", 2);
        PageObject.childmenu_Link("Outright Transaction ",3);
        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(0);

//        PageObject.textinput_Locator("fieldName:SECURITY.CODE", "000400-555");
        PageObject.textinput_Locator("fieldName:SECURITY.CODE", testData.get("SecurityCode"));
//        PageObject.textinput_Locator("fieldName:DEPOSITORY", "10000272");
        PageObject.textinput_Locator("fieldName:DEPOSITORY", testData.get("Depository"));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",3);
        PageObject.radiobutton_Locator("radio:tab1:RP.PRICE.UPDATE",2);
//        PageObject.textinput_Locator("fieldName:DEAL.METHOD", testData.get("Deal Method"));
//        PageObject.textinput_Locator("fieldName:DEAL.METHOD", "DR");
        PageObject.textinput_Locator("fieldName:DEAL.METHOD", testData.get("DealMethod"));
//        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", "SSL");
        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", testData.get("Ssale"));
//        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", "666");
        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", testData.get("noNOM"));
//        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", "999999-20");
        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", testData.get("SecAcc"));
//        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", "10000131");
        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", testData.get("CounterParty"));
        PageObject.commitDeal("PIBSale");
        PageObject.switchToChildWindow();
        driver.close();

    }

    @Test(groups = {"Inputter"}, dataProvider = "excelDataPIBPurchase")
    public void PIBPurchase(Map<String, String> testData) throws IOException, InterruptedException {

        PageObject.menu_Dropdown("Capture Current Bond deals");
        PageObject.menu_Dropdown("Pakistan Investment Bonds ");
        PageObject.menu_Dropdown("Secondary Market Transaction ", 2);
        PageObject.childmenu_Link("Outright Transaction ",3);
        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(0);
//        PageObject.textinput_Locator("fieldName:SECURITY.CODE", "000400-555");
//        PageObject.textinput_Locator("fieldName:DEPOSITORY", "10000272");
//        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",3);
//        PageObject.radiobutton_Locator("radio:tab1:RP.PRICE.UPDATE",2);
////        PageObject.textinput_Locator("fieldName:DEAL.METHOD",testData.get("Deal Method"));
//        PageObject.textinput_Locator("fieldName:DEAL.METHOD","DR");
//        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", "SPR");
//        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", "666");
//        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", "999999-20");
//        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", "10000131");
//
        PageObject.textinput_Locator("fieldName:SECURITY.CODE", testData.get("SecurityCode"));
        PageObject.textinput_Locator("fieldName:DEPOSITORY", testData.get("Depository"));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",3);
        PageObject.radiobutton_Locator("radio:tab1:RP.PRICE.UPDATE",2);
        PageObject.textinput_Locator("fieldName:DEAL.METHOD", testData.get("DealMethod"));
        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", testData.get("Spurchase"));
        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", testData.get("noNOM"));
        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", testData.get("SecAcc"));
        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", testData.get("CounterParty"));



        PageObject.commitDeal("PIBPurchase");
        PageObject.switchToChildWindow();
        driver.close();
    }

    @Test(groups = {"BOAuthorizer"}, dataProvider = "excelDataPur")
    public void PIBPurchaseAuth(Map<String, String> testData) throws IOException, InterruptedException {
        PageObject.menu_Dropdown("Bond Outright Menu");
        PageObject.menu_Dropdown("Back Office ", 3);
        PageObject.menu_Dropdown("Pakistan Investment Bonds ", 1);
        PageObject.menu_Dropdown("Authorization Menu ", 3);
        PageObject.menu_Link("Authorization of New/Reversal Outright Trade  ");

        String menu = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();
        PageObject.switchFrame(0);
        PageObject.img_Button("Selection Screen");
        PageObject.textinput_Locator("value:1:1:1", "");
        PageObject.textinput_Locator("value:1:1:1", testData.get("Transaction Number"));
        PageObject.find_Button();
        PageObject.form_Link("Authorize");
        PageObject.parentFrame();
        PageObject.switchFrame(1);
        PageObject.authorizeDeal();
    }

    @DataProvider(name = "excelDataPur")
    public Object[][] readExcelDataPur() throws IOException {
        String FILE_PATH = System.getProperty("user.dir") + "\\Data\\PIBPurchase.xlsx";

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



    @Test(groups = {"BOAuthorizer"}, dataProvider = "excelDataSale")
    public void PIBSaleAuth(Map<String, String> testData) throws IOException, InterruptedException {
        PageObject.menu_Dropdown("Bond Outright Menu");
        PageObject.menu_Dropdown("Back Office ", 3);
        PageObject.menu_Dropdown("Pakistan Investment Bonds ", 1);
        PageObject.menu_Dropdown("Authorization Menu ", 3);
        PageObject.menu_Link("Authorization of New/Reversal Outright Trade  ");

        String menu = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();
        PageObject.switchFrame(0);
        PageObject.img_Button("Selection Screen");
        PageObject.textinput_Locator("value:1:1:1", "");
        PageObject.textinput_Locator("value:1:1:1", testData.get("Transaction Number"));
        PageObject.find_Button();
        PageObject.form_Link("Authorize");
        PageObject.parentFrame();
        PageObject.switchFrame(1);
        PageObject.authorizeDeal();
    }

    @DataProvider(name = "excelDataSale")
    public Object[][] readExcelData() throws IOException {
        String FILE_PATH = System.getProperty("user.dir") + "\\Data\\PIBSale.xlsx";

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

    @DataProvider(name = "excelDataPIBPurchase")
    public Object[][] readExcelData4() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\Sec_FO_PIB.xlsx";

        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(1); // Assuming data is in the first sheet
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


    @DataProvider(name = "excelDataPIBSale")
    public Object[][] readExcelData3() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\Sec_FO_PIB.xlsx";

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