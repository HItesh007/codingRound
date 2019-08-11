package com.codinground.utility;

import com.codinground.base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitUtility {

    public WebDriverWait getExplicitWaitObject(WebDriver driver, int timeInSeconds) {
        try {
            return new WebDriverWait(driver,timeInSeconds);
        } catch (WebDriverException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void PauseBrowser(int timeInSeconds) {
        try {
            Thread.sleep(1000 * timeInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
