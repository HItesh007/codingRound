package com.codinground.tests;

import com.codinground.base.TestBase;
import com.codinground.pageobject.HotelBookingPage;
import com.codinground.utility.WaitUtility;
import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest extends TestBase {

    private HotelBookingPage hotelBookingPage;
    private WaitUtility waitUtility;

    /*private WebDriver driver = new ChromeDriver();

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;*/

    @Test(priority = 1, description = "User should be able to search for hotels.")
    public void shouldBeAbleToSearchForHotels() {
        /*setDriverPath();

        driver.get("https://www.cleartrip.com/");
        hotelLink.click();

        localityTextBox.sendKeys("Indiranagar, Bangalore");

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();

        driver.quit();*/

        hotelBookingPage = new HotelBookingPage(driver);
        waitUtility = new WaitUtility();
        waitUtility.PauseBrowser(2);

        hotelBookingPage.navigateToHotelTab();

        waitUtility.PauseBrowser(1);

        hotelBookingPage.searchForHotelAt("Indiranagar, Bangalore");

        hotelBookingPage.searchHotelForTravellersAs("1 room, 2 adults");

        waitUtility.PauseBrowser(2);

        hotelBookingPage.searchForHotels();

        System.out.println("Total Hotel List : " + hotelBookingPage.getTotalHotelCount());

        waitUtility.PauseBrowser(2);
    }

    /*private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }*/

}
