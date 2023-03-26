package com.sasha.ui.pages;

import com.sasha.ui.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DropDownAppPage extends BasePage {
    public DropDownAppPage() {
        super();
    }
    private final String spinnerLocator = "//android.widget.Spinner[@resource-id='com.jaimin.spinner:id/spinner1']";

    @FindBy(xpath = spinnerLocator)
    private WebElement dropDown;

    @FindBy(xpath = spinnerLocator + extendedTextLocator)
    private WebElement dropDownText;

    public DropDownAppPage clickOnDropDownMenu() {
        dropDown.click();
        log.info("[STEP]::Click on dropdown menu");
        return this;
    }

    public String getDropDownText() {
        log.info("[STEP]::Retrieve DropDown text");
        return dropDownText.getText();
    }

}
