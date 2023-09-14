package Test.Scripts.BackOffice.Conventional;

import POM.PageObject;
import Test.General.BaseClass;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FundsTransfer extends BaseClass {

    @Test(groups = {"BOInputter"}, dataProvider = "inputterData")
    public void FundsTransferBetweenAccounts(Map<String, String> testData) throws IOException, InterruptedException {

        String Currency = testData.get("Currency");
        String DebitAccount = testData.get("DebitAccount");
        String Amount = testData.get("Amount");
        String CreditAccount = testData.get("CreditAccount");
        String CustomerTypeString = testData.get("CustomerType");

        PageObject.menu_Dropdown("Funds Transfer");
        PageObject.menu_Link("Account to Account Transfer ");

        String HomePage = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();

        PageObject.img_Button("New Deal");

        PageObject.textinput_Locator("fieldName:DEBIT.ACCT.NO",testData.get("debit"));
        PageObject.click_Locator("fieldName:DEBIT.AMOUNT");
        String dealPage = PageObject.switchToChildWindow();
        driver.close();
        PageObject.switchToParentWindow(dealPage);
        PageObject.textinput_Locator("fieldName:DEBIT.AMOUNT",testData.get("amount"));
        PageObject.textinput_Locator("fieldName:CREDIT.ACCT.NO",testData.get("credit"));
        PageObject.click_Locator("fieldName:DEBIT.AMOUNT");
        PageObject.switchToChildWindow();
        driver.close();
        PageObject.switchToParentWindow(dealPage);
        if (CustomerTypeString=="1")
            PageObject.radiobutton_Locator("radio:tab1:AML.TYP.CUST",1);

        else{
            PageObject.radiobutton_Locator("radio:tab1:AML.TYP.CUST",2);
            PageObject.textarea_Locator("fieldName:NAME.COND.TXN",testData.get("Name"));
            PageObject.textinput_Locator("fieldName:ID.TYPE",testData.get("ID type"));
            PageObject.textinput_Locator("fieldName:ID.NUMBER",testData.get("ID Num"));
            PageObject.textinput_Locator("fieldName:ID.VAL.DT",testData.get("Exp Date"));

        }

        PageObject.radiobutton_Locator("radio:tab1:COMMISSION.CODE",Integer.parseInt(testData.get("CommisionCode")));

        PageObject.textinput_Locator("fieldName:CHEQUE.NUMBER",testData.get("chequeNum"));
        PageObject.textinput_Locator("fieldName:DEBIT.VALUE.DATE",testData.get("date"));
        PageObject.textinput_Locator("fieldName:CREDIT.VALUE.DATE",testData.get("date"));
        PageObject.textinput_Locator("fieldName:DEBIT.THEIR.REF",testData.get("Narrative"));
        PageObject.textinput_Locator("fieldName:CREDIT.THEIR.REF",testData.get("CdNarrative"));
        PageObject.textinput_Locator("fieldName:PAYMENT.DETAILS:1",testData.get("Details"));


        PageObject.commitDeal("FundsTransferBetweenAccountsTxn");

    }

    @Test(groups = {"BOAuthorizer"},dataProvider = "AuthData")
    public void authfT(Map<String, String> testData) throws IOException  {

        PageObject.menu_Dropdown("Funds Transfer");
        PageObject.menu_Link("Account to Account Transfer ");

        String HomePage = driver.getWindowHandle();
        PageObject.switchToChildWindow();

        //Got the value from DataProvider file
        PageObject.textinput_Locator("transactionId",testData.get("Transaction Number"));
        PageObject.img_Button("Perform an action on the contract");
        PageObject.img_Button("Authorises a deal");
        PageObject.assertAuthorization();
    }


    @DataProvider(name = "AuthData")
    public Object[][] readExcelData4() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\FundsTransferBetweenAccountsTxn.xlsx";
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

    @DataProvider(name = "inputterData")
    public Object[][] readExcelData_1() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\Ft.xlsx";

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
