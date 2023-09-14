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

public class Term_Finance_Certificate extends BaseClass {

    @Test(groups = {"Inputter"}, dataProvider = "excelDataTFCSale")
    public void TFCSale(Map<String, String> testData) throws IOException, InterruptedException {
        PageObject.menu_Dropdown("Capture Current Bond deals");
        PageObject.menu_Dropdown("TFC Menu (New)");
        PageObject.menu_Link("Sec Trade TFC ");
        PageObject.parentFrame();
        PageObject.switchFrame(4);
        String pgnameo = driver.getWindowHandle();
        PageObject.img_Button("New Deal");

        PageObject.textinput_Locator("fieldName:SECURITY.CODE", testData.get("SecurityCode"));
        PageObject.click_Locator("fieldName:DEPOSITORY");
        PageObject.textinput_Locator("fieldName:DEPOSITORY", testData.get("Depository"));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",3);
        PageObject.textinput_Locator("fieldName:DEAL.METHOD", testData.get("DealMethod"));
        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", testData.get("Ssale"));

        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", testData.get("SecAcc"));
        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", testData.get("noNOM"));
        PageObject.textinput_Locator("fieldName:CUST.PRICE:1:1", testData.get("CustPrice"));
        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", testData.get("CounterParty"));


//
//        PageObject.textinput_Locator("fieldName:SECURITY.CODE", "000401-055");
//        PageObject.textinput_Locator("fieldName:DEPOSITORY", "10000272");
//        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",3);
//        PageObject.textinput_Locator("fieldName:DEAL.METHOD", "DR");
//        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", "SSL");
//        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", "999999-23");
//        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", "4");
//        PageObject.textinput_Locator("fieldName:CUST.PRICE:1:1", "45");
//        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", "10000131");
//
        PageObject.commitDeal("TFCSale");
    }

    @Test(groups = {"Inputter"}, dataProvider = "excelDataTFCPurchase")
    public void TFCPurchase(Map<String, String> testData) throws IOException, InterruptedException {
        PageObject.menu_Dropdown("Capture Current Bond deals");
        PageObject.menu_Dropdown("TFC Menu (New)");
        PageObject.menu_Link("Sec Trade TFC ");
        PageObject.parentFrame();
        PageObject.switchFrame(4);
        String pgnameo = driver.getWindowHandle();
        PageObject.img_Button("New Deal");

//        PageObject.textinput_Locator("fieldName:SECURITY.CODE", "000401-180");
//        PageObject.click_Locator("fieldName:DEPOSITORY");
//        PageObject.textinput_Locator("fieldName:DEPOSITORY", "10000272");
////        PageObject.radiobutton_Locator("radio:mainTab:DEAL.SETTLEMENT",3);
//        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",3);
//        PageObject.textinput_Locator("fieldName:DEAL.METHOD", "DR");
//        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", "SPR");
//        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", "999999-23");
//        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", "4");
//        PageObject.textinput_Locator("fieldName:CUST.PRICE:1:1", "45");
//        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", "10000131");

        PageObject.textinput_Locator("fieldName:SECURITY.CODE", testData.get("SecurityCode"));
        PageObject.click_Locator("fieldName:DEPOSITORY");
        PageObject.textinput_Locator("fieldName:DEPOSITORY", testData.get("Depository"));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",3);
        PageObject.textinput_Locator("fieldName:DEAL.METHOD", testData.get("DealMethod"));
        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", testData.get("Ssale"));

        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", testData.get("SecAcc"));
        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", testData.get("noNOM"));
        PageObject.textinput_Locator("fieldName:CUST.PRICE:1:1", testData.get("CustPrice"));
        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", testData.get("CounterParty"));

        PageObject.commitDeal("TFCPurchase");
    }

    @Test(groups = {"BOAuthorizer"}, dataProvider = "excelDataPur")
    public void TFCPurchaseAuth(Map<String, String> testData) throws IOException, InterruptedException {
        PageObject.menu_Dropdown("Bond Outright Menu");
        PageObject.menu_Dropdown("Back Office ", 3);
        PageObject.menu_Dropdown("TFC Bonds Menu (New)", 1);
        PageObject.menu_Link("Authorize TFC deals ");

        String menu = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();
        PageObject.textinput_Locator("value:1:1:1", "");
        PageObject.textinput_Locator("value:1:1:1", testData.get("Transaction Number"));
        PageObject.find_Button();
        PageObject.form_Link("Authorise");
        PageObject.authorizeDeal();
     }

    @DataProvider(name = "excelDataPur")
    public Object[][] readExcelDataPur() throws IOException {
        String FILE_PATH = System.getProperty("user.dir") + "\\Data\\TFCPurchase.xlsx";

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


    @Test(groups = {"BOAuthorizer"}, dataProvider = "excelData")
    public void TFCSaleAuth(Map<String, String> testData) throws IOException, InterruptedException {
        PageObject.menu_Dropdown("Bond Outright Menu");
        PageObject.menu_Dropdown("Back Office ", 3);
        PageObject.menu_Dropdown("TFC Bonds Menu (New)", 1);
        PageObject.menu_Link("Authorize TFC deals ");

        String menu = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();
        PageObject.textinput_Locator("value:1:1:1", "");
        PageObject.textinput_Locator("value:1:1:1", testData.get("Transaction Number"));
        PageObject.find_Button();
        PageObject.form_Link("Authorise");
        PageObject.authorizeDeal();
    }

    @DataProvider(name = "excelData")
    public Object[][] readExcelData() throws IOException {
        String FILE_PATH = System.getProperty("user.dir") + "\\Data\\TFCSale.xlsx";

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

    @DataProvider(name = "excelDataTFCSale")
    public Object[][] readExcelData3() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\Sec_FO_PIB.xlsx";

        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("TFC_Sale"); // Assuming data is in the first sheet
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


    @DataProvider(name = "excelDataTFCPurchase")
    public Object[][] readExcelData4() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\Sec_FO_PIB.xlsx";

        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("TFC_Purchase"); // Assuming data is in the first sheet
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
