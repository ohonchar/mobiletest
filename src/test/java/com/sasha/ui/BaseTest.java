package com.sasha.ui;

import com.sasha.utils.ui.LocalDriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {
    protected static Logger log = LogManager.getRootLogger();
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public synchronized void openDriver() {
        driver.set(LocalDriverManager.getMobileDriver());
        ((AndroidDriver) driver.get()).unlockDevice();
    }

    @AfterClass(alwaysRun = true)
    public synchronized void closeDriver() {
        try {
            driver.get().quit();
        } catch (Exception error) {
            log.error("There are errors when closing the web driver session");
        }
        LocalDriverManager.setMobileDriver(null);
    }
}
