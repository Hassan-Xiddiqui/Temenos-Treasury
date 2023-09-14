package Test.Scripts.FrontOffice.Conventional;

import POM.PageObject;
import Test.General.BaseClass;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.devtools.v85.page.Page;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExchangePositionOfBranches extends BaseClass {

    @Test (groups={"BOInputter"},dataProvider = "data")
    public static void exchangePosition(Map<String,String> testData) throws IOException {

        String CustomerTypeString =testData.get("CustomerType");
        System.out.println("CustomerType:  "+CustomerTypeString);

        PageObject.menu_Dropdown("Funds Transfer");
        PageObject.menu_Link("Account to Account Transfer ");

        //Switching to newly opened form
        String mainPageAfterLogin = PageObject.switchToChildWindow();

        //Form has been opened, switch to the frame
//        PageObject.switchFrame(0);
        PageObject.img_Button("New Deal");


        PageObject.textinput_Locator("fieldName:DEBIT.ACCT.NO",testData.get("DebitAccNo"));//
        PageObject.click_Locator("fieldName:DEBIT.AMOUNT");
        String formPage = PageObject.switchToChildWindow();
        driver.close();

        PageObject.switchToParentWindow(formPage);

        PageObject.textinput_Locator("fieldName:DEBIT.AMOUNT",testData.get("Amount"));


        PageObject.textinput_Locator("fieldName:CHEQUE.NUMBER",testData.get("ChecqueNum"));
        PageObject.textinput_Locator("fieldName:DEBIT.VALUE.DATE",testData.get("D_Vdate"));
        PageObject.textinput_Locator("fieldName:CREDIT.VALUE.DATE",testData.get("C_Vdate"));
        PageObject.radiobutton_Locator("radio:tab1:COMMISSION.CODE",3);
        PageObject.textinput_Locator("fieldName:CREDIT.ACCT.NO",testData.get("CreditAccNo"));
        PageObject.click_Locator("fieldName:DEBIT.AMOUNT");
        PageObject.switchToChildWindow();
        driver.close();
        PageObject.switchToParentWindow(formPage);

        if (CustomerTypeString.equalsIgnoreCase("1")) {
            PageObject.radiobutton_Locator("radio:tab1:AML.TYP.CUST",1);
            System.out.println("in if");
        }

        else{
            System.out.println("in else");

            PageObject.radiobutton_Locator("radio:tab1:AML.TYP.CUST",2);
            PageObject.textarea_Locator("fieldName:NAME.COND.TXN","");
            PageObject.textinput_Locator("fieldName:ID.TYPE","");
            PageObject.textinput_Locator("fieldName:ID.NUMBER","");
            PageObject.textinput_Locator("fieldName:ID.VAL.DT","");

        }

        PageObject.commitDeal("ExchangePositionOfBranchesTxn");
//        PageObject.textinput_Locator("fieldName:DEBIT.THEIR.REF",testData.get(""));
//        PageObject.textinput_Locator("fieldName:CREDIT.THEIR.REF",testData.get(""));
//        PageObject.textinput_Locator("fieldName:PAYMENT.DETAILS:1",testData.get(""));

    }


    @Test (groups={"BOAuthorizer"},dataProvider = "data2")
    public static void exchangePositionAuth(Map<String,String> testData) throws IOException {

        PageObject.menu_Dropdown("Funds Transfer");
        PageObject.menu_Link("Account to Account Transfer ");

        //Switching to newly opened form
        String mainPageAfterLogin = PageObject.switchToChildWindow();

        //Form has been opened, switch to the frame
//        PageObject.switchFrame(0);
        PageObject.textinput_Locator("transactionId",testData.get("Transaction Number"));
        PageObject.img_Button("Perform an action on the contract");
        PageObject.img_Button("Authorises a deal");
    }




    @DataProvider(name = "data")
    public Object[][] dataMethod() throws IOException {

        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\PositionExchange.xlsx";
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


    @DataProvider(name = "data2")
    public Object[][] dataMethod2() throws IOException {

        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\ExchangePositionOfBranchesTxn.xlsx";
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
