package cpsc542.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyDogSymbol {
    WebDriver driver;

    @BeforeMethod
    public void LaunchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bzhuang\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:3000");
    }

    @Test
    public void checkUserEmailID() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/input"));
        element.sendKeys("a@a.com");

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/button")).click();
        Thread.sleep(5000);

        //check if user is able to log in
        driver.findElement(By.xpath("//h2[contains(text(),'Space Explorer')]")).getText();

        //Assert is dog symbol is present
        boolean isImageShow = driver.findElement(By.xpath("/html/body/div/div[2]/div/img")).isDisplayed();
        Assert.assertEquals(isImageShow, true);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.close();
    }
}
