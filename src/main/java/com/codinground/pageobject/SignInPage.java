package com.codinground.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

    private WebDriverWait wait;
    private WebDriver _driver;

    @FindBy(id = "userAccountLink")
    private WebElement userAccountLinkEle;

    @FindBy(id = "SignIn")
    private WebElement signInButtonEle;

    @FindBy(id = "signInButton")
    private WebElement signInButtonEleOnPopUp;

    @FindBy(id = "errors1")
    private WebElement errorDivEle;

    @FindBy(id = "ContentFrame")
    private WebElement signInPopUpModalElement;

    @FindBy(id = "modal_window")
    private WebElement popUpIFrameElement;

    @FindBy(id = "close")
    private WebElement closePopUpBtnElement;

    // Initializing the Page Object
    public SignInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this._driver = driver;
    }

    public void clickSignInButtonOnYourTripsMenu() {
        userAccountLinkEle.click();

        wait = new WebDriverWait(this._driver, 20);

        // Wait until sign in button is Clickable
        wait.until(ExpectedConditions.elementToBeClickable(signInButtonEle));

        signInButtonEle.click();
    }

    public void clickSignInButtonOnPopLogInPopUp() {

        this._driver.switchTo().frame(popUpIFrameElement);

        wait = new WebDriverWait(this._driver, 20);

        wait.until(ExpectedConditions.visibilityOf(signInPopUpModalElement));

        signInButtonEleOnPopUp.click();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorDivEle));

        return errorDivEle.getText().trim().toUpperCase();
    }

    public void closeSignInPopUp() {
        this._driver.switchTo().parentFrame();

        closePopUpBtnElement.click();
    }

}
