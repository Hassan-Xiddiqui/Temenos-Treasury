package POM;

import Test.General.BaseClass;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.time.Duration;
import java.util.Random;

public class PageObject extends BaseClass {

    public static WebDriver driver;
    public static Actions action;
    public static String parentWindow;

    public PageObject(WebDriver driver) {
        this.driver = driver;

    }

    //This method is to perform actions on Form Input Fields
    public static void textinput_Locator(String Id , String value) {

        driver.findElement(By.xpath("//tr/td/input[@id='"+Id+"']")).clear();
        driver.findElement(By.xpath("//tr/td/input[@id='"+Id+"']")).sendKeys(value);

    }

    //This method is to perform click on Form Input Fields
    public static void click_Locator (String Id) {

        driver.findElement(By.xpath("//tr/td/input[@id='"+Id+"']")).click();

    }

    //This method is to perform actions on Form Images
    public static void img_Button (String alt_Value) {
        driver.findElement(By.xpath("//tr/td/a/img[@alt='"+alt_Value+"']")).click();
    }

    public static void img_Button (String alt_Value , Integer index) {
        driver.findElement(By.xpath("(//tr/td/a/img[@alt='"+alt_Value+"'])["+index+"]")).click();
    }

    public static void find_Button () {
        driver.findElement(By.xpath("//tr/td/a[@alt='Run Selection']")).click();
    }

    //This method is to perform action on Static Select Dropdowns
    public static void select_Locator (String Id , String value) {
        WebElement Dropdown = driver.findElement(By.xpath("//tr/td/select[@id='"+Id+"']"));
        Select static_Dropdown = new Select(Dropdown);
        static_Dropdown.selectByValue(value);
    }

    //This method is to perform SignIn action
    public static void signIn (String userName , String passWord) {
        driver.findElement(By.xpath("//input[@name='signOnName']")).sendKeys(userName);
//        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passWord);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    //Switch Frame
    public static void switchFrame(int index){
        driver.switchTo().frame(index);
    }

    public  static void uploadFile(String filePath,String frameID){

        WebElement fileFrame = driver.findElement(By.xpath("//iframe[@id='"+frameID+"']"));
        driver.switchTo().frame(fileFrame);
        driver.findElement(By.xpath("//input[@id='fileInput']")).sendKeys(filePath);
        driver.findElement(By.xpath("//img[@title='Upload']")).click();

    }

    public static void parentFrame(){
        driver.switchTo().parentFrame();
    }

    //This method is to perform actions on Form Textarea Fields
    public static void textarea_Locator (String Id , String value) {
        driver.findElement(By.xpath("//tr/td/textarea[@id='"+Id+"']")).clear();
        driver.findElement(By.xpath("//tr/td/textarea[@id='"+Id+"']")).sendKeys(value);
    }

    //This method is to perform actions on Form Radio Buttons
    public static void radiobutton_Locator(String Id , int index) {
        //tr/td/input/following-sibling::span[text()='Small']
        driver.findElement(By.xpath("(//tr/td/input[@id='"+Id+"'])["+index+"]")).click();
    }

    //This method is to perform actions on In-Form Tabs
    public static void form_Tab(String text_Value) {
        driver.findElement(By.xpath("//tr/td/a/span[text()='"+text_Value+"']")).click();
    }

//    //This method is to perform action of Accept Override
//    public static void acceptOverride() {
//        driver.findElement(By.xpath("//tr/td/a[text()='Accept Overrides']")).click();
//    }

    //This method is to Expand/Collapse Menu Items
    public static void menu_Dropdown(String alt_Value) {
        driver.findElement(By.xpath("//ul/li/span/img[@alt='"+alt_Value+"']")).click();
    }

    public static void menu_Dropdown(String alt_Value, Integer index) {
        driver.findElement(By.xpath("(//ul/li/span/img[contains(@alt,'"+alt_Value+"')])["+index+"]")).click();
    }

    public static void menu_Dropdown_MM_BackOffice(String alt_Value) {
        driver.findElement(By.xpath("//img[@alt='"+alt_Value+"']")).click();
    }

    public static void menu_Dropdown_MM_BackOffice(String alt_Value,int index) {
        driver.findElement(By.xpath("(//img[@alt='"+alt_Value+"'])["+index+"]")).click();
    }

    //This method is to perform a click on Menu Links
    public static void menu_Link(String text_Value) {
        driver.findElement(By.xpath("//ul/li/a[text()='"+text_Value+"']")).click();
    }

    public static void menu_Link_MM_BackOffice(String text_Value) {
        driver.findElement(By.xpath("//a[text()='"+text_Value+"']")).click();
    }

    public static void assertAuthorization(){
        WebElement theMsg = driver.findElement(By.xpath("(//td[@class='message'])[1]"));
        String Transaction = theMsg.getText();
        Assert.assertTrue(Transaction.contains("Txn Complete"),"Not authorized successfully !");
    }

    public static void menu_Link(String text_Value , Integer index) {
        driver.findElement(By.xpath("(//ul/li/a[contains(text(),'"+text_Value+"')])["+index+"]")).click();
    }

    public static void childmenu_Link(String text_Value , Integer index) {
        driver.findElement(By.xpath("(//ul/li/a[contains(text(),'"+text_Value+"')])["+index+"]")).click();
    }

    public static void form_Link(String text_Value) {
        driver.findElement(By.xpath("//table/tbody/tr/td/a[text()='"+text_Value+"']")).click();
    }

    public static void formindex_Link(String text_Value , Integer index) {
        driver.findElement(By.xpath("(//table/tbody/tr/td/a[text()='"+text_Value+"'])["+index+"]")).click();
    }

    public static void authorizeByTxn(String txn){
        driver.findElement(By.xpath("//td[text()='"+txn+"']/following-sibling::td//a[text()='Authorize Deal']")).click();
    }

    public static void authorizeByTxn(String txn,String textOfAuthorizeLink){
        driver.findElement(By.xpath("//td[text()='"+txn+"']/following-sibling::td//a[text()='"+textOfAuthorizeLink+"']")).click();
    }
    public static void zoomOutPage(int howManyTimestoScroll) throws InterruptedException {

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {

            e.printStackTrace();
        }

        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_F);
        for (int i=0;i<howManyTimestoScroll;i++) {
            Thread.sleep(50);
            robot.mouseWheel(1);
        }
        robot.keyRelease(KeyEvent.VK_CONTROL);

    }

    public static void authorizeDeal () {
        driver.findElement(By.xpath("//tr/td/a/img[@alt='Authorises a deal']")).click();
        if (driver.getPageSource().contains("Accept Overrides")){
            WebElement override = driver.findElement(By.xpath("//tr/td/a[text()='Accept Overrides']"));
            override.click();
        }
    }

    //Generate Random Numbers

    public static int idNumber(int min,int max) {
        Random rand = new Random();


        int rand_value = rand.nextInt((max - min) + 1) + min;
        return rand_value;
    }

    public static String switchToChildWindow() {
        String homePage = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        driver.manage().window().maximize();
        return homePage;
    }

    public static void switchToParentWindow(String window){
        driver.switchTo().window(window);
    }

    public static void maximizeWindow(){
        driver.manage().window().maximize();
    }

    public static void txnValidate(String testCaseName) throws IOException {

       WebElement Txn = driver.findElement(By.xpath("//td[@class='message']"));
       Assert.assertTrue(Txn.isDisplayed(),"Transaction Un-Successful");

       String Transaction = Txn.getText();
       String[] first = Transaction.split(":");
       String[] second = first[1].split(" ");
       String TxnNum = second[1];
       System.out.println("Transaction Number is: "+TxnNum);

        File file = new File(System.getProperty("user.dir") + "\\Data\\" +testCaseName+ ".xlsx");
        XSSFWorkbook workbook;
        Row row;
        Cell cell;
        int rowNum = 0;

        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            rowNum = sheet.getLastRowNum() + 1; // Start writing from the next row

        } else {
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("Transaction Number");
        }

        Sheet sheet = workbook.getSheetAt(0);
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue(TxnNum);

        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();

    }

    public static void txnValidateIBG(String testCaseName) throws IOException {

        WebElement Txn = driver.findElement(By.xpath("//td[@class='message']"));
        Assert.assertTrue(Txn.isDisplayed(),"Transaction Un-Successful");

        String Transaction = Txn.getText();
        String[] first = Transaction.split(":");
        String[] second = first[1].split(" ");
        String TxnNum = second[1];
        System.out.println("Transaction Number is: "+TxnNum);

        File file = new File(System.getProperty("user.dir") + "\\Data\\IBG\\" +testCaseName+ ".xlsx");
        XSSFWorkbook workbook;
        Row row;
        Cell cell;
        int rowNum = 0;

        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            rowNum = sheet.getLastRowNum() + 1; // Start writing from the next row
        } else {
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("Transaction Number");
        }

        Sheet sheet = workbook.getSheetAt(0);
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue(TxnNum);

        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();

    }

    public static void txnValidate(String testCaseName,String txnWindow) throws IOException {

        //System.out.println("Txn validate 1...");
        switchToParentWindow(txnWindow);
        switchFrame(0);
        WebElement Txn = driver.findElement(By.xpath("//td[@class='message']"));
        Assert.assertTrue(Txn.isDisplayed(),"Transaction Un-Successful");
        //System.out.println("Txn validate 2...");
        String Transaction = Txn.getText();
        String[] first = Transaction.split(":");
        String[] second = first[1].split(" ");
        String TxnNum = second[1];
        System.out.println("Transaction Number is: "+TxnNum);

        File file = new File(System.getProperty("user.dir") + "\\Data\\" +testCaseName+ ".xlsx");
        XSSFWorkbook workbook;
        Row row;
        Cell cell;
        int rowNum = 0;

        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            rowNum = sheet.getLastRowNum() + 1; // Start writing from the next row
        } else {
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("Transaction Number");
        }

        Sheet sheet = workbook.getSheetAt(0);
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue(TxnNum);

        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();

    }

    // Commit Deal For Inputter
    public static void commitDeal (String testCaseName) throws IOException {
        driver.findElement(By.xpath("//tr/td/a/img[@alt='Validate a deal']")).click();
        driver.findElement(By.xpath("//tr/td/a/img[@alt='Commit the deal']")).click();
        if (driver.getPageSource().contains("Txn Complete:")){
            txnValidate(testCaseName);
        }else{
            try {
                WebElement acpOverride = driver.findElement(By.xpath("//tr/td/a[text()='Accept Overrides']"));
                acpOverride.click();
                txnValidate(testCaseName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }

    public static void commitDealIBG (String testCaseName) throws IOException {
        driver.findElement(By.xpath("//tr/td/a/img[@alt='Validate a deal']")).click();
        driver.findElement(By.xpath("//tr/td/a/img[@alt='Commit the deal']")).click();
        if (driver.getPageSource().contains("Txn Complete:")){
            txnValidateIBG(testCaseName);
        }else{
            try {
                WebElement acpOverride = driver.findElement(By.xpath("//tr/td/a[text()='Accept Overrides']"));
                acpOverride.click();
                txnValidateIBG(testCaseName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }
    public static void commitDealNoTxn (String testCaseName) throws IOException {
        driver.findElement(By.xpath("//tr/td/a/img[@alt='Validate a deal']")).click();
        driver.findElement(By.xpath("//tr/td/a/img[@alt='Commit the deal']")).click();
        if (driver.getPageSource().contains("Txn Complete:")){
            txnValidate(testCaseName);
        }else{
            try {
                WebElement acpOverride = driver.findElement(By.xpath("//tr/td/a[text()='Accept Overrides']"));
                acpOverride.click();
//                txnValidate(testCaseName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }
    public static void commitDealFrontOffice (String testCaseName,String txnWindow) throws IOException {
        driver.findElement(By.xpath("//tr/td/a/img[@alt='Validate a deal']")).click();
        driver.findElement(By.xpath("//tr/td/a/img[@alt='Commit the deal']")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (driver.getPageSource().contains("Txn Complete:")){

            txnValidate(testCaseName);

        }else{
            try {
                WebElement acpOverride = driver.findElement(By.xpath("//tr/td/a[text()='Accept Overrides']"));
                acpOverride.click();

                Thread.sleep(2000);

                if (driver.getPageSource().contains("Txn Complete:")){
                    txnValidate(testCaseName);
                }

                else {

                    Thread.sleep(20000);
                    Robot robot = null;
                    try {
                        robot = new Robot();
                    } catch (AWTException e) {

                        e.printStackTrace();
                    }
                    robot.keyPress(KeyEvent.VK_ESCAPE);
                    robot.keyRelease(KeyEvent.VK_ESCAPE);
                    Thread.sleep(2000);
                    switchToChildWindow();
                    driver.close();

                    txnValidate(testCaseName, txnWindow);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }

    // Get Txn Number to pass further for Authorization
    public static String getTxn () {
        WebElement Txn = driver.findElement(By.xpath("//table/tbody/tr/td[contains(text(),'Txn Complete:')]"));
        String Transaction = Txn.getText();
        String[] first = Transaction.split(":");
        String[] second = first[1].split(" ");
        String TxnNum = second[1];
        return TxnNum;
    }



    //This method is for user SignOff
    public static void signOff() {
        driver.findElement(By.xpath("//tr/td/a[@alt='Sign off']")).click();
    }

}
