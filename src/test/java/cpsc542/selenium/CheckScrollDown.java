package cpsc542.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckScrollDown {
    WebDriver driver;

    @BeforeMethod
    public void LaunchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bzhuang\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:3000");
    }

    @Test
    public void checkBackButton() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/input"));
        element.sendKeys("nik@d.com");

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/button")).click();
        Thread.sleep(5000);

        //scroll down the page
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,9000)");
        Thread.sleep(5000);

//check load more option at the end of the page
        String tripTextActual = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/button[text()='Load More']")).getText();
        Thread.sleep(5000);
        String tripTextExpected = "LOAD MORE";

        Assert.assertEquals(tripTextExpected, tripTextActual);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.close();
    }
}