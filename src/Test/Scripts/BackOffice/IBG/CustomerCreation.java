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

public class CustomerCreation extends BaseClass {
    @Test(groups = {"BOInputterIBG"}, dataProvider = "CustomerCreation")
    public void CustomerCreation(Map<String, String> testData) throws IOException, InterruptedException {

        String FullName = testData.get("FullName");
        String ShortName = testData.get("ShortName");
        String Mnemonic = testData.get("Mnemonic")+PageObject.idNumber(100,999);
        String AccountOfficer = testData.get("AccountOfficer");
        String Industry = testData.get("Industry");
        String Target = testData.get("Target");
        String CustomerStatus = testData.get("CustomerStatus");
        String Nationality = testData.get("Nationality");
        String Residence = testData.get("Residence");
        String Address = testData.get("Address");

        PageObject.menu_Dropdown("Money Market Menu");
        PageObject.menu_Dropdown("Back Office",2);
        PageObject.menu_Dropdown("Cutomer Menu ",2);
        PageObject.menu_Link("Customer Opening  ",2);

        String NewDeal = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();

        PageObject.img_Button("New Deal");

        PageObject.textinput_Locator("fieldName:NAME.1:1",FullName);
        PageObject.textinput_Locator("fieldName:SHORT.NAME:1",ShortName);
        PageObject.textinput_Locator("fieldName:MNEMONIC",Mnemonic);
        PageObject.textinput_Locator("fieldName:ACCOUNT.OFFICER",AccountOfficer);
        PageObject.textinput_Locator("fieldName:INDUSTRY",Industry);
        PageObject.textinput_Locator("fieldName:TARGET",Target);
        PageObject.textinput_Locator("fieldName:CUSTOMER.STATUS",CustomerStatus);
        PageObject.textinput_Locator("fieldName:NATIONALITY",Nationality);
        PageObject.textinput_Locator("fieldName:RESIDENCE",Residence);
        PageObject.textinput_Locator("fieldName:STREET:1",Address);


           /*
        PageObject.textinput_Locator("fieldName:ID.TYPE:1",testData.get("ID_TYPE"));
        PageObject.textinput_Locator("fieldName:ID.NUMBER:1",testData.get("ID_NUMBER"));
        PageObject.textinput_Locator("fieldName:SECTOR",testData.get("SECTOR"));
        PageObject.textinput_Locator("fieldName:INDUSTRY",testData.get("INDUSTRY"));
        PageObject.textinput_Locator("fieldName:CUSTOMER.STATUS",testData.get("CUSTOMER_STATUS"));
        PageObject.textinput_Locator("fieldName:LANGUAGE",testData.get("LANGUAGE"));
        PageObject.textinput_Locator("fieldName:EXT.CR.RATING",testData.get("EXT_CR_RATING"));
        PageObject.textinput_Locator("fieldName:CR.RATE.AGENCY",testData.get("CR_RATE_AGENCY"));
        PageObject.textinput_Locator("fieldName:CUS.TYPE.LC",testData.get("CUS_TYPE_LC"));
        PageObject.textinput_Locator("fieldName:STREET:1",testData.get("STREET"));
        PageObject.textinput_Locator("fieldName:TOWN.COUNTRY:1",testData.get("TOWN_COUNTRY"));
        PageObject.textinput_Locator("fieldName:POST.CODE:1",testData.get("POST_CODE"));
        PageObject.textinput_Locator("fieldName:COUNTRY:1",testData.get("COUNTRY"));
        PageObject.form_Tab("Contact Details");

        PageObject.textinput_Locator("fieldName:INTRODUCER",testData.get("INTRODUCER"));
        PageObject.textinput_Locator("fieldName:CUST.OFF.PHONE:1",testData.get("CUST_OFF_PHONE"));
        PageObject.textinput_Locator("fieldName:CP.FAX.NO:1",testData.get("CP_FAX_NO"));
        PageObject.textinput_Locator("fieldName:CUST.EMAIL.ID:1",testData.get("CUST_EMAIL_ID"));

        PageObject.form_Tab("Relation");

        PageObject.textinput_Locator("fieldName:RELATION.CODE:1",testData.get("RELATION_CODE"));
        PageObject.textinput_Locator("fieldName:REL.CUSTOMER:1",testData.get("REL_CUSTOMER"));
        PageObject.textinput_Locator("fieldName:CUSTOMER.LIABILITY",testData.get("LIABILITY"));

        * */

        PageObject.commitDealIBG("IBG_CustomerCreation");

    }

    @DataProvider(name = "CustomerCreation")
    public Object[][] readExcelData_1() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\IBG\\IBG_CustomerCreation.xlsx";

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

    @Test(groups = {"IBGAuthorizer"}, dataProvider = "CustomerCreation_Authorization")
    public void CustomerCreation_Authorization(Map<String, String> testData) throws IOException, InterruptedException {

        String TxnNumber = testData.get("Transaction Number");

//        PageObject.switchFrame(1);
        PageObject.menu_Dropdown("Money Market Menu");
        PageObject.menu_Dropdown("Back Office",2);
        PageObject.menu_Dropdown("Cutomer Menu ",2);
        PageObject.menu_Link("Customer Opening  ",2);

        String AuthorizeDeal = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();

        PageObject.textinput_Locator("transactionId",TxnNumber);
        PageObject.img_Button("Perform an action on the contract");

        PageObject.authorizeDeal();

    }
    @DataProvider(name = "CustomerCreation_Authorization")
    public Object[][] readExcelData_1A() throws IOException {
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
}
