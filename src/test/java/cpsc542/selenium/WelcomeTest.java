package cpsc542.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WelcomeTest {
    WebDriver driver;

    int WAIT_TIME = 5000;
    @BeforeMethod
    public void LaunchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bzhuang\\Downloads\\chromedriver_win32\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("http://localhost:3000");
        //  driver.get("http://192.168.1.3:3000/")
    }
    @Test
    public void verify(){
        waitMethod();
        String text = driver.findElement(By.xpath("//*[@id=\"root\"]/div/h1")).getText();
        Assert.assertEquals(text, "Space Explorer");
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