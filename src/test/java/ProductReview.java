import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
        driver.findElement(By.cssSelector("ul.nav>li:nth-of-type(1)")).click();
        driver.findElement(By.cssSelector("ul.nav>li:nth-of-type(1)>div li:nth-of-type(2)")).click();
        driver.findElement(By.cssSelector("div.caption>h4>a")).click();
        driver.findElement(By.cssSelector("ul.nav-tabs>li:nth-of-type(2)")).click();
        String reviewerName = "KrumK" + RandomStringUtils.randomAlphabetic(1);
        driver.findElement(By.id("input-name")).sendKeys(reviewerName);
        driver.findElement(By.id("input-review")).sendKeys("Very good product, worth it");
        if (!driver.findElement(By.cssSelector("input[value='5']")).isSelected()){
            driver.findElement(By.cssSelector("input[value='5']")).click();
        }
        driver.findElement(By.cssSelector("div.pull-right>button")).click();
        driver.get("http://shop.pragmatic.bg/admin/");
        driver.findElement(By.id("input-username")).sendKeys("admin22");
        driver.findElement(By.id("input-password")).sendKeys("parola123!");
        driver.findElement(By.cssSelector("div.text-right>button")).click();
        driver.findElement(By.cssSelector("li#menu-catalog")).click();
        driver.findElement(By.cssSelector("li#menu-catalog li:nth-of-type(9)")).click();
        driver.findElement(By.cssSelector("td>a.btn-primary")).click();
        new Select(driver.findElement(By.id("input-status"))).selectByValue("1");
        driver.findElement(By.cssSelector("div>button")).click();
        driver.get("http://shop.pragmatic.bg/");
        driver.findElement(By.cssSelector("ul.nav>li:nth-of-type(1)")).click();
        driver.findElement(By.cssSelector("ul.nav>li:nth-of-type(1)>div li:nth-of-type(2)")).click();
        driver.findElement(By.cssSelector("div.caption>h4>a")).click();
        driver.findElement(By.cssSelector("ul.nav-tabs>li:nth-of-type(2)")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div#review")).getText().contains("Very good product, worth it"), "review not successful");
        Assert.assertTrue(driver.findElement(By.cssSelector("div#review")).getText().contains(reviewerName), "review not successful");
    }
}
