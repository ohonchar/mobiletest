package com.sasha.utils.ui;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UiHelpers {
    private final WebDriver driver = LocalDriverManager.getMobileDriver();
    public WebElement scrollTo(String visibleText) {
        return findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(" +
                "new UiSelector().scrollable(true).instance(0))" +
                ".scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))"));
    }

    public WebElement findElement(By element) {
        return findElement(element, 5);
    }
    public WebElement findElement(By element, int defaultTimeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}
