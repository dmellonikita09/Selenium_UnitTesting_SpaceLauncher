package cpsc542.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavigateToCartPage {
    WebDriver driver;

    @BeforeMethod
    public void LaunchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bzhuang\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:3000");
    }

    @Test
    public void checkcartpage() throws InterruptedException{
        WebElement element=driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/input"));
        element.sendKeys("a@a.com");

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/button")).click();
        Thread.sleep(5000);

        //check if user is able to navigate to cart page
        driver.findElement(By.xpath("//body/div[@id='root']/footer[1]/div[1]/a[2]/*[1]")).click();
        Thread.sleep(5000);
        String actual_url = driver.getCurrentUrl();
        String expected_url = "http://localhost:3000/cart";
        Assert.assertEquals(expected_url, actual_url);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.close();
    }
}
