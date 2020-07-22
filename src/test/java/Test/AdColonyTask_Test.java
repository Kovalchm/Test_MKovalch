package Test;

import POBJ.AdColonyPageObj;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class AdColonyTask_Test {

    public WebDriver driver;
    private String baseUrl = "https://clients.adcolony.com";
    private String email = "testuserfortesttask001@gmail.com";
    private String passwd = "@dColony001"; // normally should be encrypted but it is test task, so write it in natural way
    private String appName = "AdColony Take Home Assignment - Michael";

    @Before
    public void start() {
        driver = new ChromeDriver();
        PageFactory.initElements(driver, AdColonyPageObj.class);
    }

    @After
    public void teardown(){
        driver.close();
        driver.quit();
    }

    @Test
    public void execute()  {
        /*login();
        stepOne();
        stepTwo();
        stepThree();*/  //Uncomment it if you want to see how new prod is created
        stepFour();
    }

    public void login() {

        driver.get(baseUrl);
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        AdColonyPageObj.mailField.sendKeys(email);
        AdColonyPageObj.passwdField.sendKeys(passwd);
        AdColonyPageObj.logInButton.click();
    }

    public void stepOne(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/app/new']")));
        if(element.isEnabled()) {
            AdColonyPageObj.newApp.click();
        }else {
            System.err.println("New App didn't detect !");
            System.exit(1);
        }
    }

    public void stepTwo(){

        String checkOpenPage = driver.findElement(By.xpath("//h1[@class = 'form-header']")).getText();
        Assert.assertTrue(checkOpenPage.contains("Create New App")); // Just checking if page got open; Also we could do it by searching a field we need or some other element

        AdColonyPageObj.appName.sendKeys(appName);
        AdColonyPageObj.platformAndroid.click();

        // Scrolling to element we need - just to make script elegant
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",AdColonyPageObj.deselectAllAds);
        AdColonyPageObj.deselectAllAds.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        selectCustomizeAds(AdColonyPageObj.gamblingAd, AdColonyPageObj.apps17Plus, AdColonyPageObj.foodAd, AdColonyPageObj.internetAd, AdColonyPageObj.healthAndwellnesAd);
        AdColonyPageObj.createAppbtn.click();

    }

    public void stepThree(){

        // Scrollup to menu for log out and decline cookies if they are visible
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement nocookies = driver.findElement(By.xpath("//a[@id = 'pi_tracking_opt_in_no']"));
        if(nocookies.isDisplayed()){
            jse.executeScript("arguments[0].scrollIntoView(true);",nocookies);
            nocookies.click(); //After closing cookie menu is visible and clickable
            AdColonyPageObj.menuIcon.click();
            AdColonyPageObj.logOut.click();
        }else {
            jse.executeScript("arguments[0].scrollIntoView(true);",AdColonyPageObj.menuIcon);
            AdColonyPageObj.menuIcon.click();
            AdColonyPageObj.logOut.click();
        }

    }

    // Now we can check if App was created
    public void stepFour(){
        login();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement nocookies = driver.findElement(By.xpath("//a[@id = 'pi_tracking_opt_in_no']"));
        if(nocookies.isDisplayed()) {
            jse.executeScript("arguments[0].scrollIntoView(true);", nocookies);
            nocookies.click();
        }
        AdColonyPageObj.monetization.click();
        // We can also check if it is present comparing from db or another storage
        WebElement prod = driver.findElement(By.xpath("//span[@class = 'truncated-tooltip']"));
        Boolean productVisible = prod.isDisplayed();
        Assert.assertTrue(productVisible);
        System.out.print(prod.getText());

    }


    // More sophisticated way to execute this function is to send a String like arguments, but I found it problematic while creating a PObj so sent there regular objects
    private void selectCustomizeAds (WebElement element1,WebElement element2,WebElement element3,WebElement element4,WebElement element5){
        if (!element1.isSelected()) element1.click();
        if (!element2.isSelected()) element2.click();
        if (!element3.isSelected()) element3.click();
        if (!element4.isSelected()) element4.click();
        if (!element5.isSelected()) element5.click();

    }
}
