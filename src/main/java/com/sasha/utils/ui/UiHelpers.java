package com.sasha.utils.ui;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UiHelpers {
    private final WebDriver driver = LocalDriverManager.getMobileDriver();
    protected WebElement scrollTo(String visibleText) {
        return findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(" +
                "new UiSelector().scrollable(true).instance(0))" +
                ".scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))"));
    }

    protected WebElement findElement(By element) {
        return findElement(element, 5);
    }
    protected WebElement findElement(By element, int defaultTimeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    protected Alert waitUntilAlertIsPresent(long timeToWait) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeToWait))
                .until(ExpectedConditions.alertIsPresent());
    }

    public  <T> T goBack(T type) {
        driver.navigate().back();
        return type;
    }

    public void restartAndroidApp() {
        ((AndroidDriver) driver).terminateApp("io.appium.android.apis");
        ((AndroidDriver) driver).activateApp("io.appium.android.apis");
    }

    public void restartIosApp() {
        ((IOSDriver) driver).terminateApp("com.apple.Preferencess");
        ((IOSDriver) driver).activateApp("com.apple.Preferences");
    }
}
