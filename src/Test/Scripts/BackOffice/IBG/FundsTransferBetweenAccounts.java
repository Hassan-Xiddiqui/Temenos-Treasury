package Test.Scripts.BackOffice.IBG;

import POM.PageObject;
import Test.General.BaseClass;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

public class FundsTransferBetweenAccounts extends BaseClass {

    @Test(groups = {"BOInputterIBG"}, dataProvider = "FundsTransferBetweenAccounts")
    public void FundsTransferBetweenAccounts(Map<String, String> testData) throws IOException, InterruptedException {

        String Currency = testData.get("Currency");
        String DebitAccount = testData.get("DebitAccount");
        String Amount = testData.get("Amount");
        String CreditAccount = testData.get("CreditAccount");

        PageObject.menu_Dropdown("Funds Transfer");
        PageObject.menu_Link("Fund Transfer (General)  ");

        String NewDeal = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();

        PageObject.img_Button("New Deal");

        PageObject.textinput_Locator("fieldName:TRANSACTION.TYPE","AC");
        PageObject.textinput_Locator("fieldName:DEBIT.CURRENCY",Currency);
        PageObject.textinput_Locator("fieldName:DEBIT.ACCT.NO",DebitAccount);
        PageObject.click_Locator("fieldName:DEBIT.AMOUNT");

        String DebitAccountPage = PageObject.switchToChildWindow();
        driver.close();
        PageObject.switchToParentWindow(DebitAccountPage);

        PageObject.textinput_Locator("fieldName:DEBIT.AMOUNT",Amount);
        PageObject.textinput_Locator("fieldName:CREDIT.ACCT.NO",CreditAccount);
        PageObject.click_Locator("fieldName:DEBIT.AMOUNT");

        String CreditAccountPage = PageObject.switchToChildWindow();
        driver.close();
        PageObject.switchToParentWindow(CreditAccountPage);

        PageObject.commitDealIBG("IBG_FundsTransferBetweenAccounts");

    }

    @DataProvider(name = "FundsTransferBetweenAccounts")
    public Object[][] readExcelData_1() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\IBG\\IBG_FundsTransferBetweenAccounts.xlsx";

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

    @Test(groups = {"IBGAuthorizer"}, dataProvider = "FundsTransferBetweenAccounts_Authorization")
    public void FundsTransferBetweenAccounts_Authorization(Map<String, String> testData) throws IOException, InterruptedException {

        String TxnNumber = testData.get("Transaction Number");

//        PageObject.switchFrame(1);

        PageObject.menu_Dropdown("Funds Transfer");
        PageObject.menu_Link("Fund Transfer (General)  ");

        String AuthorizeDeal = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();

        PageObject.textinput_Locator("transactionId",TxnNumber);
        PageObject.img_Button("Perform an action on the contract");

        PageObject.authorizeDeal();

    }
    @DataProvider(name = "FundsTransferBetweenAccounts_Authorization")
    public Object[][] readExcelData_1A() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\IBG\\IBG_FundsTransferBetweenAccounts.xlsx";

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
