package com.codinground.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.List;

public class FlightBookingPage {

    private WebDriver _driver;
    private WebDriverWait _wait;

    @FindBy(linkText = "Flights")
    private WebElement flightLinkElement;

    @FindBy(id = "OneWay")
    private WebElement oneWayRadioBtnElement;

    @FindBy(id = "RoundTrip")
    private WebElement roundTripRadioBtnElement;

    @FindBy(id = "MultiCity")
    private WebElement multiCityTripRadioBtnElement;

    @FindBy(id = "FromTag")
    private WebElement flightFromInputElement;

    @FindBy(id = "ui-id-1")
    private WebElement flightFromAutoCompleteComponentElement;

    @FindBy(id = "ToTag")
    private WebElement flightToInputElement;

    @FindBy(id = "ui-id-2")
    private WebElement flightToAutoCompleteComponentElement;

    @FindBy(id = "DepartDate")
    private WebElement departDateDatePickerElement;

    @FindBy(id = "SearchBtn")
    private WebElement searchFlightButtonElement;

    @FindBy(className = "ctDatePicker")
    private WebElement datePickerDivElement;

    @FindBy(className = "searchSummary")
    private WebElement searchSummaryElement;

    @FindBy(className = "loaderContainer")
    private List<WebElement> loaderContainerElement;

    public FlightBookingPage(WebDriver driver) {
        this._driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void NavigateToFlights() {
        flightLinkElement.click();
    }

    public void SelectTrip(TripType tripType) {
        switch (tripType.toString().toUpperCase()) {
            case "ONEWAY":
                oneWayRadioBtnElement.click();
                break;

            case "ROUNDTRIP":
                roundTripRadioBtnElement.click();
                break;

            case "MULTICITYTRIP":
                multiCityTripRadioBtnElement.click();
                break;
        }
    }

    public void flightFrom(String flightFrom) {

        _wait = new WebDriverWait(this._driver, 30);

        flightFromInputElement.clear();
        flightFromInputElement.sendKeys(flightFrom);

        // Wait until auto completer fetches the result in list
        _wait.until(ExpectedConditions.visibilityOf(flightFromAutoCompleteComponentElement));

        List<WebElement> fromListElements = flightFromAutoCompleteComponentElement.findElements(By.tagName("li"));

        if(fromListElements.size() > 0) {

            for(int i=0; i<fromListElements.size(); i++) {
                if(fromListElements.get(i).getText().trim().toUpperCase().contains(flightFrom.trim().toUpperCase())) {
                    fromListElements.get(i).click();
                    break;
                }
            }
        }
    }

    public void flightTo(String flightTo) {

        _wait = new WebDriverWait(this._driver, 30);

        flightToInputElement.clear();
        flightToInputElement.sendKeys(flightTo);

        // Wait until auto completer fetches the result in list
        _wait.until(ExpectedConditions.visibilityOf(flightToAutoCompleteComponentElement));

        List<WebElement> toListElements = flightToAutoCompleteComponentElement.findElements(By.tagName("li"));

        if(toListElements.size() > 0) {

            for(int i=0; i<toListElements.size(); i++) {
                if(toListElements.get(i).getText().trim().toUpperCase().contains(flightTo.trim().toUpperCase())) {
                    toListElements.get(i).click();
                    break;
                }
            }
        }
    }

    public void flightDepartureDateForCurrentMonthFrom(String date) {

        _wait = new WebDriverWait(this._driver, 30);

        // Wait until Date Picker is visible on UI
        _wait.until(ExpectedConditions.visibilityOf(datePickerDivElement));

        WebElement calendarElement = datePickerDivElement.findElement(By.className("calendar"));
        List<WebElement> calendarDates = calendarElement.findElements(By.tagName("td"));

        if(calendarDates.size() > 0) {
            for(int i=0; i<calendarDates.size(); i++) {
                if(calendarDates.get(i).getText().toString().trim().equals(date.trim())) {
                    calendarDates.get(i).click();
                    break;
                }
            }
        }
    }


    public void searchForFlights() {
        _wait = new WebDriverWait(this._driver, 60);

        searchFlightButtonElement.click();

        _wait.until(ExpectedConditions.invisibilityOf(loaderContainerElement.get(1)));
    }

    public boolean isSearchResultPageRendered() {
        return isElementPresent(By.className("searchSummary"));
    }

    private boolean isElementPresent(By by) {
        try {
            this._driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public enum TripType {
        OneWay,
        RoundTrip,
        MultiCityTrip
    }

}
