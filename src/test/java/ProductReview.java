import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ProductReview {

    private WebDriver driver;

    @BeforeMethod
    public void SetUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\RoaMingOne\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void leaveAndApproveReview(){
        driver.get("http://shop.pragmatic.bg/");
        driver.findElement(By.cssSelector("ul.nav>li:nth-of-type(1)>div li:nth-of-type(2)")).click();
        driver.findElement(By.cssSelector("div.caption>h4>a")).click();
        driver.findElement(By.cssSelector("ul.nav-tabs>li:nth-of-type(2)")).click();
        driver.findElement(By.id("input-name")).sendKeys("KrumK" + RandomStringUtils.randomAlphabetic(1));
        driver.findElement(By.id("input-review")).sendKeys("Very good product");
        if (!driver.findElement(By.cssSelector("input[value='5']")).isSelected());{
            driver.findElement(By.cssSelector("input[value='5']")).click();
        }
        driver.findElement(By.cssSelector("div.pull-right>button")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.alert-success")).getText().contains(" Thank you for your review. It has been submitted to the webmaster for approval."));
    }
}
