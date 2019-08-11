package com.condinground.tests;

import com.codinground.base.TestBase;
import com.codinground.pageobject.SignInPage;
import com.codinground.utility.WaitUtility;
import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends TestBase {

    private SignInPage signInPage;
    private WaitUtility waitUtil;


    @Test(priority = 0, description = "Sign In Test Case To Check Invalid Login Attempt Without User Credentials.")
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        signInPage = new SignInPage(driver);
        waitUtil = new WaitUtility();
        waitUtil.PauseBrowser(2);

        /*
        waitFor(2000);
        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();

        driver.findElement(By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();

         */

        signInPage.clickSignInButtonOnYourTripsMenu();
        signInPage.clickSignInButtonOnPopLogInPopUp();

        String errorMessage = signInPage.getErrorMessage();

        System.out.print("------------- ERROR MESSAGE -----------------------");
        System.out.print(errorMessage);
        System.out.print("------------- ERROR MESSAGE -----------------------");

        Assert.assertTrue(errorMessage.contains("THERE WERE ERRORS IN YOUR SUBMISSION"));
        Assert.assertTrue(errorMessage.contains("YOUR USERNAME IS A REQUIRED FIELD"));
        Assert.assertTrue(errorMessage.contains("YOUR ACCOUNT PASSWORD IS A REQUIRED FIELD"));

    }

    /*private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void setDriverPath() {
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
