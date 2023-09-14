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

public class CreationOfDealerLimit extends BaseClass {

    @Test(groups = {"BOInputterIBG"}, dataProvider = "CreationOfDealerLimit")
    public void CreationOfDealerLimit(Map<String, String> testData) throws IOException, InterruptedException {

        String UserID_Copy = testData.get("UserID_Copy");
        String UserId_Current = testData.get("UserId_Current");

        PageObject.menu_Dropdown("Forex Menu");
        PageObject.menu_Dropdown("Back Office",1);
        PageObject.menu_Dropdown("Treasury Setup Tables ",1);
        PageObject.menu_Dropdown("Treasury Parameter Setup ",1);
        PageObject.menu_Link("Treasury Dealer Limit  ",1);

        String NewDeal = PageObject.switchToChildWindow();
        PageObject.maximizeWindow();

        PageObject.textinput_Locator("transactionId",UserID_Copy);
        PageObject.img_Button("Edit a contract");
        PageObject.select_Locator("moreactions","EDIT|COPY");
        PageObject.img_Button("Go");

        String CopyConfirmWindow = PageObject.switchToChildWindow();
        driver.close();
        PageObject.switchToParentWindow(CopyConfirmWindow);

        PageObject.img_Button("Commit the deal");
        PageObject.textinput_Locator("transactionId",UserId_Current);
        PageObject.img_Button("Edit a contract");
        PageObject.select_Locator("moreactions","EDIT|PK.H.DLIMIT,PK.DLIMIT_PASTE");
        PageObject.img_Button("Go");

        PageObject.commitDealIBG("CreationOfDealerLimit");

    }

    @DataProvider(name = "CreationOfDealerLimit")
    public Object[][] readExcelData_1() throws IOException {
        String FILE_PATH = System.getProperty("user.dir")+"\\Excel Data\\IBG\\IBG_CreationOfDealerLimit.xlsx";

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
