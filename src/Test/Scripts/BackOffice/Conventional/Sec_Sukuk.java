package Test.Scripts.BackOffice.Conventional;

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

public class Sec_Sukuk extends BaseClass {

    @Test(groups = {"BOInputter"}, dataProvider = "SukukData")
    public void Sukuk(Map<String, String> testData) throws IOException, InterruptedException {

        String CompanyName  = testData.get("CompanyName");
        String Description  = testData.get("Description");
        String ShortName  = testData.get("ShortName");
        String Mnemonic  = testData.get("Mnemonic")+String.valueOf(PageObject.idNumber(100,999));
        String SubAssetType  = testData.get("SubAssetType");
        String IssueDate = testData.get("IssueDate");
        String MaturityDate  = testData.get("MaturityDate");
        String intDate  = testData.get("date");

        PageObject.menu_Dropdown("Bond Outright Menu");
        PageObject.menu_Dropdown("Back Office ",3);
        PageObject.menu_Dropdown("Admin Menu ",2);
        PageObject.menu_Dropdown("Security Master ");
        PageObject.menu_Link("Sukuk Bond (Ijara) Maintenance  ");

        String NewDeal = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();

        PageObject.img_Button("New Deal");

        PageObject.textinput_Locator("fieldName:COMPANY.NAME:1",CompanyName);
        PageObject.textinput_Locator("fieldName:DESCRIPT:1:1",Description);
        PageObject.textinput_Locator("fieldName:SHORT.NAME:1", ShortName);
        PageObject.textinput_Locator("fieldName:MNEMONIC", Mnemonic);
        PageObject.textinput_Locator("fieldName:SUB.ASSET.TYPE", SubAssetType);
        PageObject.textinput_Locator("fieldName:ISSUE.DATE", IssueDate);
        PageObject.textinput_Locator("fieldName:MATURITY.DATE", MaturityDate);

        PageObject.form_Tab("Cpn and Tax");
        PageObject.textinput_Locator("fieldName:INTEREST.RATE:1","6.00");
        PageObject.textinput_Locator("fieldName:INT.PAYMENT.DATE",intDate);



        PageObject.commitDeal("Sec_BackOffice_SukukTxnNumber");

    }

    @DataProvider(name = "SukukData")
    public Object[][] readExcelData_1() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\Sec_BackOffice_Sukuk.xlsx";

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
