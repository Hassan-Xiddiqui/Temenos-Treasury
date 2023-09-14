package Test.Scripts.FrontOffice.IBG;

import POM.PageObject;
import Test.General.BaseClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class FXMaturityLedgerEnquiry extends BaseClass {

    @Test(groups = {"IBGInputter"})
    public void Enquiry() {

        PageObject.menu_Dropdown("Treasury Menu");
        PageObject.menu_Dropdown("Front Office");
        PageObject.menu_Dropdown("Forex Enquiries");
        PageObject.menu_Link("FX Maturity Ledger (Inter Branch Deals)  ");
        String homePage = PageObject.switchToChildWindow();
        PageObject.find_Button();
    }

}
