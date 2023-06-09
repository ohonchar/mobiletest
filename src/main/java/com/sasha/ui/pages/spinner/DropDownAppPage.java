package com.sasha.ui.pages.spinner;

import com.sasha.ui.BasePage;
import com.sasha.ui.pages.spinner.widgets.DropDownWidget;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class DropDownAppPage extends BasePage {
    public DropDownAppPage() {
        super();
    }
    private final String spinnerLocator = "//android.widget.Spinner[@resource-id='com.jaimin.spinner:id/spinner1']";

    @AndroidFindBy(xpath = spinnerLocator)
    private WebElement dropDown;

    @AndroidFindBy(xpath = spinnerLocator)
    private DropDownWidget dropDownText;

    public DropDownAppPage clickOnDropDownMenu() {
        dropDown.click();
        log.info("[STEP]::Click on dropdown menu");
        return this;
    }

    public String getDropDownText() {
        log.info("[STEP]::Retrieve DropDown text");
        return dropDownText.getSubWidget().getSelfReference().getWrappedElement().getText();
    }

}
