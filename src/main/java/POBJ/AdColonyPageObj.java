package POBJ;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdColonyPageObj {
    @FindBy(xpath = "//input[@id='email']")
    public static WebElement mailField;

    @FindBy(xpath = "//input[@id='password']")
    public static WebElement passwdField;

    @FindBy(xpath = "//div[@class = 'action-block col-auto']/button")
    public static WebElement logInButton;

    @FindBy(xpath = "//a[@href='/app/new']")
    public static WebElement newApp;

    @FindBy(xpath = "//input[@id = 'name']")
    public static WebElement appName;

    @FindBy(xpath = "//select[@name = 'platform']//option[@value = 'android']")
    public static WebElement platformAndroid;

    @FindBy(xpath = "//div[@id = 'control_filters']//button[contains(text(),'Deselect All')]")
    public static WebElement deselectAllAds;
    // Generally check a few posibilities to get Ads elements
    @FindBy(xpath = "//div[@class = 'pull-left v-space-top h-space-right'][1]/div[3]/label/input")
    public static WebElement gamblingAd;

    @FindBy(xpath = "//input[@id='filters' and @value = '11']")
    public static WebElement apps17Plus;

    @FindBy(xpath = "//input[@value = 26]")
    public static WebElement foodAd;

    @FindBy(xpath = "//div[@class = 'pull-left v-space-top h-space-right'][3]//input[@value = 28]")
    public static WebElement internetAd;

    @FindBy(xpath = "//input[@value = 40]")
    public static WebElement healthAndwellnesAd;

    @FindBy(xpath = "//button[contains(text(),'Create')]")
    public static WebElement createAppbtn;

    @FindBy(xpath = "//button[@id = 'dropdownMenuButton']")
    public static WebElement menuIcon;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    public static WebElement logOut;

    @FindBy(xpath = "//a[@href='/apps']")
    public static WebElement monetization;

}
