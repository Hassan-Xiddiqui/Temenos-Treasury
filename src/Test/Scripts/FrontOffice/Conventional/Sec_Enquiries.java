package Test.Scripts.FrontOffice.Conventional;

import POM.PageObject;
import Test.General.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
public class Sec_Enquiries extends BaseClass {

    @Test (groups ={"BOinputter"})
    public void viewHoldingsReport(){

        PageObject.menu_Dropdown("Bond Outright Menu");
        PageObject.menu_Dropdown("Refine Reports ");
        PageObject.menu_Dropdown("Securities Postion (SBP Holdings)");
        String holdingName ="Treasury Bills Position ";
        PageObject.menu_Link(holdingName);
//        PageObject.find_Button();
        driver.findElement(By.xpath("//a[@alt='Run Selection']")).click();
    }

    @Test (groups ={"BOinputter"})
    public void viewOutrightEnquiry(){

        PageObject.menu_Dropdown("Bond Outright Menu");
        PageObject.menu_Dropdown("Refine Reports ");
        String enquiryName ="Outright Purchase of Sukuk Bonds  ";
        PageObject.menu_Link(enquiryName);
        driver.findElement(By.xpath("//a[@alt='Run Selection']")).click();
    }

}
