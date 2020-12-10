package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

public class Profile {
    private WebDriver driver;

    public Profile() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bzhuang\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void login(String emailAddress) {
        //launch the web-browser
        driver.get("http://localhost:3000");
        WebElement emailInput = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.findElement(By.name("email")));
        //log-in
        emailInput.sendKeys(emailAddress);
        driver.findElement(withTagName("button").below(emailInput)).click();
    }

    public void logout() {
        WebElement logoutButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.findElement(By.xpath("(//footer)[1]/div/button")));
        logoutButton.click();
    }

    public void addTrips(int[] selectedTrips) {
        for (int trip : selectedTrips) {
            addTripToCart(trip);
            driver.findElement(By.xpath("//a[@href=\"/\"]")).click();
        }
    }

    public void bookTrips() {
        //click book all button
        driver.findElement(By.xpath("//a[@href=\"/cart\"]")).click();
        WebElement bookAllButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.findElement(By.xpath("//button[text()='Book All']")));
        bookAllButton.click();
    }

    public List<WebElement> getBookedTrips() {
        driver.findElement(By.xpath("//a[@href=\"/profile\"]")).click();
        WebElement profileHeader = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.findElement(By.xpath("//h2[text()='My Trips']")));
        return driver.findElements(By.xpath("//*[@id=\"root\"]/div[2]/a"));
    }


    public void cancelTrip(int tripId) {
        //select the trip to cancel
        List<WebElement> bookedTrips = getBookedTrips();
        bookedTrips.get(tripId).click();
        //cancel the trip
        WebElement cancelButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.findElement(By.xpath("//button[text()='Cancel This Trip']")));
        cancelButton.click();
    }



    private void addTripToCart(int tripIndex){
        WebElement homeHeader = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.findElement(By.xpath("//h2[text()='Space Explorer']")));
        List<WebElement> trips = driver.findElements(By.xpath("//*[@id=\"root\"]/div[2]/a"));
        WebElement selectedTrip = trips.get(tripIndex);
        selectedTrip.click();
        WebElement addToCartButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.findElement(By.xpath("//button[text()='Add to Cart']")));
        addToCartButton.click();
    }

}
