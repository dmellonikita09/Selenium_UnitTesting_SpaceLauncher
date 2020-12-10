package cpsc542.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckBackButton {
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

//click on profile
        driver.findElement(By.xpath("//*[contains(text(), 'Profile')]")).click();
        Thread.sleep(5000);
        //you will see 'You haven't booked any trips' message

//click on cart
        driver.findElement(By.xpath("//*[contains(text(), 'Cart')]")).click();
        Thread.sleep(5000);

//click on back button to navigate to previous page
        driver.navigate().back();
        Thread.sleep(5000);

//check if is the previous page
        String tripTextActual = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/p")).getText();
        Thread.sleep(5000);
        String tripTextExpected = "You haven't booked any trips";

        Assert.assertEquals(tripTextExpected, tripTextActual);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.close();
    }

}