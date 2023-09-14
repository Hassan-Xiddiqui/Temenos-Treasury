package Test.Scripts.BackOffice.Conventional;

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

public class StandardSettlementInstruction_AgencySetup extends BaseClass {

    @Test(groups = {"BOinputter2"}, dataProvider = "StandardSettlementInstruction_AgencySetup")
    public void StandardSettlementInstruction_AgencySetup(Map<String, String> testData) throws IOException, InterruptedException {

        String CustomerID = testData.get("Transaction Number");
        String Currency = "USD";
        String Application = "ALL";
        /*String Currency = testData.get("Currency");
        String Application = testData.get("Application");*/


        PageObject.menu_Dropdown("Forex Menu");
        PageObject.menu_Dropdown("Back Office ");
        PageObject.menu_Dropdown("Treasury Setup Tables ");
        PageObject.menu_Dropdown("Treasury Parameter Setup ");
        PageObject.menu_Link("Settlement Instructions  ");

        String NewDeal = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();

        PageObject.textinput_Locator("transactionId",CustomerID);
        PageObject.img_Button("Edit a contract");

        PageObject.textinput_Locator("fieldName:AUTORTE.CCY:1",Currency);
        PageObject.textinput_Locator("fieldName:AUTORTE.APPL:1:1",Application);
        PageObject.textinput_Locator("fieldName:AUTORTE.BANK:1:1",CustomerID);

        PageObject.radiobutton_Locator("radio:tab1:TEST.SIGNATURE",2);
        PageObject.radiobutton_Locator("radio:tab1:AUTOROUTING",2);
        PageObject.radiobutton_Locator("radio:tab1:DRAFT.ADVICE",2);
        PageObject.commitDeal("StandardSettlementInstruction_AgencySetup");

    }

    @DataProvider(name = "StandardSettlementInstruction_AgencySetup")
    public Object[][] readExcelData_1() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Data\\CustomerCreation.xlsx";

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
