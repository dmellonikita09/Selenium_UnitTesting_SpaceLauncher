package cpsc542.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyNoTrips {
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

        //click on profile tab
        driver.findElement(By.xpath("//*[contains(text(), 'Profile')]")).click();
        Thread.sleep(5000);

        //check if there no trips
        String tripText = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/p")).getText();

        String actual_message = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/p")).getText();
        String expected_message = "You haven't booked any trips";
        Assert.assertEquals(expected_message, actual_message);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.close();
    }
}
