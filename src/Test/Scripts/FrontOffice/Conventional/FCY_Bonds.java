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

public class FCY_Bonds extends BaseClass {

    @Test(groups = {"Inputter"})
    public void FCYBondsSale() throws IOException, InterruptedException {

        PageObject.menu_Dropdown("Capture Current Bond deals");
        PageObject.menu_Dropdown("FCY Bonds");
        PageObject.childmenu_Link("Outright Transaction FCY Bonds ",1);


        PageObject.parentFrame();
        PageObject.switchFrame(4);
        String pgnameo = driver.getWindowHandle();

        PageObject.img_Button("New Deal");
        PageObject.textinput_Locator("fieldName:SECURITY.CODE", "000401-552");
        PageObject.textinput_Locator("fieldName:DEPOSITORY", "10000272");
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",3);
        PageObject.radiobutton_Locator("radio:tab1:RP.PRICE.UPDATE",2);
        PageObject.textinput_Locator("fieldName:DEAL.METHOD", "DR");
        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", "SSL");
        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", "666");
        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", "999999-151");
//        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", "999999-150");
//        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", "999999-152");
        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", "10000131");
        PageObject.commitDeal("FCYBondSaleTxn");
        PageObject.switchToChildWindow();
        driver.close();
    }


    @Test(groups = {"Inputter"})
    public void FCYBondsPurchase() throws IOException, InterruptedException {

        PageObject.menu_Dropdown("Capture Current Bond deals");
        PageObject.menu_Dropdown("FCY Bonds");
        PageObject.childmenu_Link("Outright Transaction FCY Bonds ",1);

        PageObject.parentFrame();
        PageObject.switchFrame(4);
        String pgnameo = driver.getWindowHandle();

        PageObject.img_Button("New Deal");
        PageObject.textinput_Locator("fieldName:SECURITY.CODE", "000401-226");
        PageObject.textinput_Locator("fieldName:DEPOSITORY", "10000272");
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",3);
        PageObject.radiobutton_Locator("radio:tab1:RP.PRICE.UPDATE",2);
        PageObject.textinput_Locator("fieldName:DEAL.METHOD", "DR");
        PageObject.textinput_Locator("fieldName:CUST.TRANS.CODE:1", "SPR");
        PageObject.textinput_Locator("fieldName:CUST.NO.NOM:1:1", "666");
        PageObject.textinput_Locator("fieldName:CUST.SEC.ACC:1", "999999-150");
        PageObject.textinput_Locator("fieldName:COUNTER.PARTY", "10000131");
        PageObject.commitDeal("FCYBondPurchase");
        PageObject.switchToChildWindow();
        driver.close();
    }

    @Test(groups = {"BOAuthorizer"}, dataProvider = "excelData")
    public void fcybondSaleAuth(Map<String, String> testData) throws IOException,InterruptedException{
        PageObject.menu_Dropdown("Bond Outright Menu");
//        PageObject.childmenu_Dropdown("Back Office ", 3);
//        PageObject.childmenu_Dropdown("FCY Bonds Menu", 1);
        PageObject.menu_Link("Authorize FCY Outright Deal ");

        String menu = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();
        PageObject.switchFrame(0);
        PageObject.img_Button("Selection Screen");
        PageObject.textinput_Locator("value:1:1:1","");
        PageObject.textinput_Locator("value:1:1:1",testData.get("Transaction Number"));
        PageObject.find_Button();
        PageObject.form_Link("Authorize");
        PageObject.parentFrame();
        PageObject.switchFrame(1);
        PageObject.authorizeDeal();
    }

    @Test(groups = {"BOAuthorizer"}, dataProvider = "excelDataPur")
    public void fcybondPurchaseAuth(Map<String, String> testData) throws IOException,InterruptedException{
        PageObject.menu_Dropdown("Bond Outright Menu");
//        PageObject.childmenu_Dropdown("Back Office ", 3);
//        PageObject.childmenu_Dropdown("FCY Bonds Menu", 1);
        PageObject.menu_Link("Authorize FCY Outright Deal ");

        String menu = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();
        PageObject.switchFrame(0);
        PageObject.img_Button("Selection Screen");
        PageObject.textinput_Locator("value:1:1:1","");
        PageObject.textinput_Locator("value:1:1:1",testData.get("Transaction Number"));
        PageObject.find_Button();
        PageObject.form_Link("Authorize");
        PageObject.parentFrame();
        PageObject.switchFrame(1);
        PageObject.authorizeDeal();
    }

    @DataProvider(name = "excelDataPur")
    public Object[][] readExcelDataPur() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\FCYBondPurchase.xlsx";

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

    @DataProvider(name = "excelData")
    public Object[][] readExcelData() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\FCYBondSaleTxn.xlsx";

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