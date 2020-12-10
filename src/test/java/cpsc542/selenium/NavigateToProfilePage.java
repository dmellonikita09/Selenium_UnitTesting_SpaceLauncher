package cpsc542.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavigateToProfilePage {
    WebDriver driver;

    @BeforeMethod
    public void LaunchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bzhuang\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:3000");
    }

    @Test
    public void checkprofilepage() throws InterruptedException{
        WebElement element=driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/input"));
        element.sendKeys("a@a.com");

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/button")).click();
        Thread.sleep(5000);

        //check if user is able to navigate to profile page
        driver.findElement(By.xpath("//*[contains(text(), 'Profile')]")).click();
        Thread.sleep(5000);

        System.out.println("*********** User navigated to Profile page **********");
        String actual_url = driver.getCurrentUrl();
        String expected_url = "http://localhost:3000/profile";

        Assert.assertEquals(expected_url, actual_url);

    }

    @AfterMethod
    public void closeBrowser(){
        driver.close();
    }
}
