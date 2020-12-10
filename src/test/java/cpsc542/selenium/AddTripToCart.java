package cpsc542.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddTripToCart {
    WebDriver driver;
    int WAIT_TIME =5000;

    @BeforeMethod
    public void LaunchBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bzhuang\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:3000");
    }

    @Test
    public  void enterRegister(){
        driver.findElement(By.name("email")).sendKeys("test@test.com");
        waitMethod();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/button")).click();
        waitMethod();
        AddToCart();
        VerifyCart();
    }

    public void AddToCart(){
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/a[1]/h3")).click();
        waitMethod();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        waitMethod();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/button")).click();
        waitMethod();
    }

    public void VerifyCart(){
        driver.findElement(By.xpath("//*[@id=\"root\"]/footer/div/a[2]")).click();
        waitMethod();
        String text = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/a/h3")).getText();
        Assert.assertEquals(text, "Starlink-15 (v1.0)");
    }

    @AfterMethod
    public void closeBrowser(){
        driver.close();
    }

    public void waitMethod(){
        try {
            Thread.sleep(WAIT_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
