package com.codinground.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HotelBookingPage {

    private WebDriver _driver;
    private WebDriverWait _wait;

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @FindBy(className = "autoComplete")
    private WebElement autoCompleterParentElement;

    @FindBy(className = "totalCount")
    private List<WebElement> totalHotelCountLabelElement;

    @FindBy(className = "loaderContainer")
    private List<WebElement> loaderContainerElement;

    public HotelBookingPage(WebDriver driver) {
        this._driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void navigateToHotelTab() {
        hotelLink.click();
    }

    public void searchForHotelAt(String hotelLocation) {
        _wait = new WebDriverWait(this._driver, 20);

        _wait.until(ExpectedConditions.visibilityOf(localityTextBox));

        localityTextBox.clear();
        localityTextBox.sendKeys(hotelLocation);

        // wait until the auto completer has options
        _wait.until(ExpectedConditions.visibilityOf(autoCompleterParentElement));

        List<WebElement> suggestionList = autoCompleterParentElement.findElements(By.tagName("li"));

        for (int i=0; i < suggestionList.size(); i++) {
            // System.out.println("Options At  Index ["+ i + "] Is : " + suggestionList.get(i).getText().trim().toUpperCase());
            if(suggestionList.get(i).getText().trim().toUpperCase().contains(hotelLocation.trim().toUpperCase())) {
                suggestionList.get(i).click();
                break;
            }
        }

        // Perform keys.Tab to close the calender
        Actions action = new Actions(_driver);
        action.sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .build()
                .perform();
    }

    public void searchHotelForTravellersAs(String travellerCount) {
        new Select(travellerSelection).selectByVisibleText(travellerCount);
    }

    public void searchForHotels() {
        searchButton.click();
    }

    public String getTotalHotelCount() {
        _wait = new WebDriverWait(_driver,30);

        _wait.until(ExpectedConditions.invisibilityOf(loaderContainerElement.get(1)));

        return totalHotelCountLabelElement.get(1).getText();
    }
}
