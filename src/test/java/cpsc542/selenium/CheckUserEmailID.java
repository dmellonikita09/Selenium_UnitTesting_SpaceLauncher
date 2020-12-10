package cpsc542.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckUserEmailID {
    WebDriver driver;

    @BeforeMethod
    public void LaunchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bzhuang\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:3000");
    }

    @Test
    public void checkUserEmailID() throws InterruptedException{
        WebElement element=driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/input"));
        element.sendKeys("a@a.com");

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/button")).click();
        Thread.sleep(5000);

        //check if user is able to log in
        driver.findElement(By.xpath("//h2[contains(text(),'Space Explorer')]")).getText();

        //check the email id
        String expected_email = "a@a.com";
        String actual_email = driver.findElement(By.xpath("//*[contains(text(),'a@a.com')]")).getText().toLowerCase();
        //Assert
        Assert.assertEquals(expected_email, actual_email);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.close();
    }

}
