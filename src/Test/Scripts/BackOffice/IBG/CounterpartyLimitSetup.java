package Test.Scripts.BackOffice.IBG;

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

public class CounterpartyLimitSetup extends BaseClass {

    @Test(groups = {"IbgBOLimitInput"}, dataProvider = "CounterpartyLimitSetup")
    public void CounterpartyLimitSetup(Map<String, String> testData) throws IOException, InterruptedException {

        String CustomerID = testData.get("Transaction Number");
        /*String ProductID = testData.get("ProductID");
        String SerialNumer = testData.get("SerialNumer");
        String Currency = testData.get("Currency");
        String ProductAllowed = testData.get("ProductAllowed");
        String InternalAmount = testData.get("InternalAmount");
        String MaximumAmount = testData.get("MaximumAmount");
        String AdvisedAmount = testData.get("AdvisedAmount");*/


        PageObject.menu_Dropdown("Limit Setup");
        PageObject.menu_Link("Create Unsecured Limit ");

        String NewDeal = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();

        PageObject.textinput_Locator("transactionId",CustomerID+".0030000.05");
        PageObject.img_Button("Edit a contract");

        //for data from dataset
        /*PageObject.textinput_Locator("fieldName:LIMIT.CURRENCY",Currency);
        PageObject.textinput_Locator("fieldName:PRODUCT.ALLOWED:1",ProductAllowed);
        PageObject.textinput_Locator("fieldName:INTERNAL.AMOUNT",InternalAmount);
        PageObject.textinput_Locator("fieldName:MAXIMUM.TOTAL",MaximumAmount);
        PageObject.textinput_Locator("fieldName:ADVISED.AMOUNT",AdvisedAmount);
        PageObject.radiobutton_Locator("radio:tab1:ALLOW.NETTING",1);
        PageObject.commitDeal("CounterpartyLimitSetup");*/

        PageObject.textinput_Locator("fieldName:LIMIT.CURRENCY","PKR");
        PageObject.textinput_Locator("fieldName:PRODUCT.ALLOWED:1","3200");
        PageObject.textinput_Locator("fieldName:INTERNAL.AMOUNT","1B");
        PageObject.textinput_Locator("fieldName:MAXIMUM.TOTAL","1B");
        PageObject.textinput_Locator("fieldName:ADVISED.AMOUNT","1B");
        PageObject.radiobutton_Locator("radio:tab1:ALLOW.NETTING",1);
        PageObject.commitDeal("IBG_CounterpartyLimitSetup_Global");

    }

    @DataProvider(name = "CounterpartyLimitSetup")
    public Object[][] readExcelData_1() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\IBG\\IBG_CustomerCreation.xlsx";

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

    @Test(groups = {"IbgBOLimitAuth"}, dataProvider = "CounterpartyLimitSetup_Authorization")
    public void CounterpartyLimitSetup_Authorization(Map<String, String> testData) throws IOException, InterruptedException {

        String LimitID = testData.get("Transaction Number");

        PageObject.switchFrame(1);
        PageObject.menu_Dropdown("Limit Setup");
        PageObject.menu_Link("Create Unsecured Limit ");

        String NewDeal = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();

        PageObject.textinput_Locator("transactionId",LimitID);
        PageObject.img_Button("Perform an action on the contract");
        PageObject.authorizeDeal();

    }

    @DataProvider(name = "CounterpartyLimitSetup_Authorization")
    public Object[][] readExcelData_1A() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\IBG_CounterpartyLimitSetup_Global.xlsx";

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


    //________________________________________________________________________________________


    @Test(groups = {"IbgBOLimitInput"}, dataProvider = "CounterpartyLimitSetup")
    public void CounterpartyLimitSetupChild(Map<String, String> testData) throws IOException, InterruptedException {

        String CustomerID = testData.get("Transaction Number");
        /*String ProductID = testData.get("ProductID");
        String SerialNumer = testData.get("SerialNumer");
        String Currency = testData.get("Currency");
        String ProductAllowed = testData.get("ProductAllowed");
        String InternalAmount = testData.get("InternalAmount");
        String MaximumAmount = testData.get("MaximumAmount");
        String AdvisedAmount = testData.get("AdvisedAmount");*/

        PageObject.menu_Dropdown("Limit Setup");
        PageObject.menu_Link("Create Unsecured Limit ");

        String NewDeal = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();

        PageObject.textinput_Locator("transactionId",CustomerID+".0001400.05");
        PageObject.img_Button("Edit a contract");

        //for data from dataset
        /*PageObject.textinput_Locator("fieldName:LIMIT.CURRENCY",Currency);
        PageObject.textinput_Locator("fieldName:PRODUCT.ALLOWED:1",ProductAllowed);
        PageObject.textinput_Locator("fieldName:INTERNAL.AMOUNT",InternalAmount);
        PageObject.textinput_Locator("fieldName:MAXIMUM.TOTAL",MaximumAmount);
        PageObject.textinput_Locator("fieldName:ADVISED.AMOUNT",AdvisedAmount);
        PageObject.radiobutton_Locator("radio:tab1:ALLOW.NETTING",1);
        PageObject.commitDeal("CounterpartyLimitSetup");*/

        PageObject.textinput_Locator("fieldName:LIMIT.CURRENCY","PKR");
//        PageObject.textinput_Locator("fieldName:PRODUCT.ALLOWED:1","3200");
        PageObject.textinput_Locator("fieldName:INTERNAL.AMOUNT","1B");
        PageObject.textinput_Locator("fieldName:MAXIMUM.TOTAL","1B");
        PageObject.textinput_Locator("fieldName:ADVISED.AMOUNT","1B");
        PageObject.radiobutton_Locator("radio:tab1:ALLOW.NETTING",1);
        PageObject.commitDealIBG("IBG_CounterpartyLimitSetup_Child");

    }

    /*@DataProvider(name = "CounterpartyLimitSetup")
    public Object[][] readExcelData_2() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\CounterpartyLimitSetup.xlsx";

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
    }*/

    @Test(groups = {"IbgBOLimitAuth"}, dataProvider = "CounterpartyLimitSetupChild_Authorization")
    public void CounterpartyLimitSetup_Child_Authorization(Map<String, String> testData) throws IOException, InterruptedException {

        String LimitID = testData.get("Transaction Number");

        PageObject.switchFrame(1);
        PageObject.menu_Dropdown("Limit Setup");
        PageObject.menu_Link("Create Unsecured Limit ");

        String NewDeal = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();

        PageObject.textinput_Locator("transactionId",LimitID);
        PageObject.img_Button("Perform an action on the contract");
        PageObject.authorizeDeal();

    }

    @DataProvider(name = "CounterpartyLimitSetupChild_Authorization")
    public Object[][] readExcelData_2A() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\IBG\\IBG_CounterpartyLimitSetup_Child.xlsx";

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
