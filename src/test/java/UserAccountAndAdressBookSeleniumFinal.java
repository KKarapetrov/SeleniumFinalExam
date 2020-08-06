import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class UserAccountAndAdressBookSeleniumFinal {


    private WebDriver driver;
    private String firstName = "Krum" + RandomStringUtils.randomAlphanumeric(2);
    private String lastName = "Karapetrov" + RandomStringUtils.randomAlphanumeric(2);
    private String eMail = "krum" + RandomStringUtils.randomAlphabetic(2)+".karapetrov@gmail.com";
    private String address = "Sofia, Bulgaria, Lagera, Bogovets str" + RandomStringUtils.randomNumeric(2);
    private String city = "Sofia";
    private String postCode = "1612";
    private String telephone = RandomStringUtils.randomNumeric(8);
    private String password = RandomStringUtils.randomNumeric(8);



    @BeforeMethod
    public void SetUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\RoaMingOne\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    }

    @Test
    public void createCustomerAccount() {
        WebDriverWait wait = new WebDriverWait(driver,5);
        driver.get("http://shop.pragmatic.bg/");
        driver.findElement(By.cssSelector("li.dropdown span.hidden-xs")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.dropdown-menu>li:nth-of-type(1)>a")));
        driver.findElement(By.cssSelector("ul.dropdown-menu>li:nth-of-type(1)>a")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div#content>h1")).getText().contains("Register Account"),"Not on account creation screen");
        driver.findElement(By.id("input-firstname")).sendKeys(firstName);
        driver.findElement(By.id("input-lastname")).sendKeys(lastName);
        driver.findElement(By.id("input-email")).sendKeys(eMail);
        driver.findElement(By.id("input-telephone")).sendKeys(telephone);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.id("input-confirm")).sendKeys(password);
        driver.findElement(By.cssSelector("label.radio-inline:nth-of-type(2)")).click();
        if (!driver.findElement(By.name("agree")).isSelected()){
            driver.findElement(By.name("agree")).click();
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.pull-right>input.btn")));
        driver.findElement(By.cssSelector("div.pull-right>input.btn")).click();
        driver.findElement(By.cssSelector("div.buttons>div>a")).click();
        driver.findElement(By.xpath("//div//a[text()='Modify your address book entries']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.pull-right>a")));
        driver.findElement(By.cssSelector("div.pull-right>a")).click();
        driver.findElement(By.id("input-firstname")).sendKeys(firstName);
        driver.findElement(By.id("input-lastname")).sendKeys(lastName);
        driver.findElement(By.id("input-address-1")).sendKeys(address);
        driver.findElement(By.id("input-city")).sendKeys(city);
        driver.findElement(By.id("input-postcode")).sendKeys();
        new Select(driver.findElement(By.id("input-country"))).selectByValue("33");
        new Select(driver.findElement(By.id("input-zone"))).selectByValue("498");
        if ((!driver.findElement(By.cssSelector("div.col-sm-10>label:nth-of-type(1)")).isSelected())){
            driver.findElement(By.cssSelector("div.col-sm-10>label:nth-of-type(1)")).click();
        }

        driver.findElement(By.cssSelector("div.pull-right>input")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div#content>h2")).getText().contains("Address Book Entries"));
        Assert.assertTrue(driver.findElement(By.cssSelector("td.text-left")).getText().contains(firstName),"first name does not match");
        Assert.assertTrue(driver.findElement(By.cssSelector("td.text-left")).getText().contains(lastName),"last name does not match");
        Assert.assertTrue(driver.findElement(By.cssSelector("td.text-left")).getText().contains(address),"Address does not match");
        Assert.assertTrue(driver.findElement(By.cssSelector("td.text-left")).getText().contains(city),"Address does not match");

    }


    @AfterMethod
    public void tearDown(){
        driver.quit();

    }
}
