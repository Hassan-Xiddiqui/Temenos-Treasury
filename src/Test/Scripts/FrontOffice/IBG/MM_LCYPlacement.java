package Test.Scripts.FrontOffice.IBG;

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

public class MM_LCYPlacement extends BaseClass {

    @Test(groups = {"IBGInputter"}, dataProvider = "MMWakalaLCYInputterIBGData")
    public void MMWakalaLCYInputterIBG(Map<String, String> testData) throws IOException, InterruptedException {

        PageObject.menu_Dropdown("Money Market Menu");
        PageObject.menu_Dropdown("Front Office",2);
        PageObject.menu_Dropdown("Input Deal");
        PageObject.menu_Link("Wakala / Mudaraba / Musharaka LCY Placement  ");

        String mainWindow = PageObject.switchToChildWindow();
        PageObject.switchFrame(4);

        PageObject.img_Button("New Deal");
        PageObject.select_Locator("fieldName:TR.IBD.DEALTYPE",testData.get("DealType"));
        PageObject.textinput_Locator("fieldName:CUSTOMER.ID",testData.get("CustID"));
        //PageObject.textinput_Locator("fieldName:CUSTOMER.ID","11845748");

        PageObject.click_Locator("fieldName:FTD.TYPE");

        String formWindow = PageObject.switchToChildWindow();
        driver.close();

        PageObject.switchToParentWindow(formWindow);
        PageObject.switchFrame(4);


        //PageObject.textinput_Locator("fieldName:FTD.TYPE","WAKPKRONPLC");
        PageObject.textinput_Locator("fieldName:FTD.TYPE",testData.get("FtdType"));
        PageObject.textinput_Locator("fieldName:CATEGORY",testData.get("Category"));
        PageObject.textinput_Locator("fieldName:PRINCIPAL",testData.get("Amount"));
        PageObject.textinput_Locator("fieldName:INTEREST.SPREAD.1",testData.get("Rate"));
        PageObject.textinput_Locator("fieldName:VALUE.DATE",testData.get("VDate"));
        PageObject.textinput_Locator("fieldName:MATURITY.DATE",testData.get("MDate"));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",Integer.parseInt(testData.get("dealSettlement")));
        PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",Integer.parseInt(testData.get("dealSettlement")));
        //PageObject.radiobutton_Locator("radio:tab1:DEAL.SETTLEMENT",4);

        if( (testData.get("dealSettlement")).equalsIgnoreCase("2") )
        PageObject.textinput_Locator("fieldName:BROKER.CODE","11854065");
//        PageObject.textinput_Locator("fieldName:BROKER.CODE",testData.get("BROKER.CODE"));
        PageObject.textinput_Locator("fieldName:DEAL.METHOD",testData.get("dealMethod"));//


        PageObject.commitDealIBG("IBG_MM_LCYPlacement");
    }


    @Test (groups = {"IBGAuthorizer"}, dataProvider = "MMWakalaLCYInputterAuthData")
    public void MMWakalaLCYInputterAuth(Map<String, String> testData) throws IOException {

//        PageObject.switchFrame(1);
        PageObject.menu_Dropdown_MM_BackOffice("Money Market Menu");
        PageObject.menu_Dropdown_MM_BackOffice("Back Office",2);
        PageObject.menu_Dropdown_MM_BackOffice("Authorization of Money Market ");
        PageObject.menu_Link_MM_BackOffice("Authorization Version - Local  ");
        //driver.findElement(By.xpath("//a[text()='Authorization Version - Local  ']")).click();

        String homePage = PageObject.switchToChildWindow();
        PageObject.switchFrame(0);
        PageObject.authorizeByTxn(testData.get("Transaction Number"));
        driver.switchTo().parentFrame();
        PageObject.switchFrame(1);
        PageObject.img_Button("Authorises a deal");

    }


    @DataProvider(name = "MMWakalaLCYInputterAuthData")
    public Object[][] data2() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\IBG\\IBG_MM_LCYPlacement.xlsx";
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

    @DataProvider(name = "MMWakalaLCYInputterIBGData")
    public Object[][] dataLCYDeal() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\IBG\\MM_WakalaLCYPlacement.xlsx";
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
