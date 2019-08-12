package com.codinground.base;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class TestBase {

    public static final String BASE_DIR = System.getProperty("user.dir");
    public static final String DRIVER_DIR = "/src/main/driver/";
    public static WebDriver driver;

    @BeforeSuite
    public void InitializeTests() {
        try {

            // Set driver path
            setDriverPath();

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-notifications");

            // Initialize Driver Instance
            driver = new ChromeDriver(chromeOptions);

            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

            // Open Web Url
            setUrl("https://www.cleartrip.com/");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", BASE_DIR + DRIVER_DIR + "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", BASE_DIR + DRIVER_DIR + "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", BASE_DIR + DRIVER_DIR + "chromedriver_linux");
        }
    }

    private void setUrl(String webUrl) {
        try {
            driver.get(webUrl);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @AfterSuite
    public void TearDownDriver() {
        try {
            driver.close();
        } catch (WebDriverException wdExcep) {
            wdExcep.printStackTrace();
        }
    }
}
