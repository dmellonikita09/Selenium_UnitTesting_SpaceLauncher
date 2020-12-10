package cpsc542.selenium;

import com.company.Profile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

/*
This class is intended to test multiply booking functions, which include:
1) book all trips from cart
2) see trips in profile after booking
3) remove trips after booking
4) cancel a trip after booking
*/
public class BookTripTestSuite {
    private WebDriver webDriver;
    private Profile newUser;

    @BeforeMethod
    public void openNewBrowser() {
        newUser = new Profile();
        webDriver = newUser.getDriver();
    }

    @Test
    public void bookAllTripsFromCart(){
        //log in
        newUser.login("test0@csu.fullerton.edu");
        //add trips
        int[] selectedTrips = new int[]{ 0, 1 };
        newUser.addTrips(selectedTrips);
        //book trips
        newUser.bookTrips();
        //Assertion
        WebElement tripMsg = new WebDriverWait(webDriver, Duration.ofSeconds(3))
                .until(driver -> driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/p")));
        String noTripMsg = tripMsg.getText();
        Assert.assertEquals(noTripMsg, "No items in your cart");
    }

    @Test
    public void shouldSeeTripAfterBooking(){
        //log in
        newUser.login("test1@csu.fullerton.edu");
        //add trips
        int[] selectedTrips = new int[]{ 0, 1 };
        newUser.addTrips(selectedTrips);
        //book trips
        newUser.bookTrips();
        //Assertion
        List<WebElement> bookedTrips = newUser.getBookedTrips();
        Assert.assertEquals(bookedTrips.size(), 2);
    }

    @Test
    public void shouldRemoveTripsAfterBooking(){
        //log in
        newUser.login("test2@csu.fullerton.edu");
        //add trips
        int[] selectedTrips = new int[]{0,1};
        newUser.addTrips(selectedTrips);
        //book trips
        newUser.bookTrips();
        //cancel all booked trips from profile
        for (int i = 0; i < selectedTrips.length; i++) {
            newUser.cancelTrip(0);
        }
        //Assertion
        webDriver.findElement(By.xpath("//a[@href=\"/profile\"]")).click();
        WebElement tripMsg = new WebDriverWait(webDriver, Duration.ofSeconds(3))
                .until(driver -> driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/p")));
        String noTripMsg = tripMsg.getText();
        Assert.assertEquals(noTripMsg, "You haven't booked any trips");
    }

    @Test
    public void shouldCancelATripAfterBooking() {
        //log in
        newUser.login("test3@csu.fullerton.edu");
        //add trips
        int[] selectedTrips = new int[]{0,1};
        newUser.addTrips(selectedTrips);
        //book trips
        newUser.bookTrips();
        //select and cancel a trip
        newUser.cancelTrip(1);
        //Assertion
        webDriver.findElement(By.xpath("//a[@href=\"/profile\"]")).click();
        List<WebElement> bookedTrips = newUser.getBookedTrips();
        Assert.assertEquals(bookedTrips.size(), 1);
    }

    @Test
    public void shouldLogout(){
        newUser.login("test4@csu.fullerton.edu");
        newUser.logout();
        //Assertion
        WebElement loginButton = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(d -> d.findElement(By.xpath("(//form)[1]/button")));
        String loginMsg = loginButton.getText();
        Assert.assertEquals(loginMsg, "LOG IN");
    }

    @AfterMethod
    public void closeBrowser() {
        webDriver.close();
    }
}
