package cpsc542.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyUserEmailChange {
    WebDriver driver;

    @BeforeMethod
    public void LaunchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bzhuang\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:3000");
    }
    /*
    Check user is able to navigate to the home page
    */
    @Test
    public void verifyUserEmailChanged() throws InterruptedException{
        WebElement element=driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/input"));
        element.sendKeys("a@a.com");

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/button")).click();
        Thread.sleep(5000);

        //check if user is able to log in
        String elementText = driver.findElement(By.xpath("//*[contains(text(),'a@a.com')]")).getText();
        Assert.assertEquals(elementText, "A@A.COM");
        //click on Logout
        driver.findElement(By.xpath("//*[contains(text(), 'Logout')]")).click();
        Thread.sleep(2000);
        //Click on login button
        WebElement emailInput = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/form[1]/input[1]"));
        emailInput.click();
        emailInput.sendKeys("b@b.com");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/button")).click();
        Thread.sleep(2000);

        String actual_email = driver.findElement(By.xpath("//*[contains(text(),'b@b.com')]")).getText().toLowerCase();
        String expected_email = "b@b.com";
        Assert.assertEquals(expected_email, actual_email);
    }


    @AfterMethod
    public void closeBrowser(){
        driver.close();
    }
}
