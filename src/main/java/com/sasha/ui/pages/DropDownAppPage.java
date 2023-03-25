package com.sasha.ui.pages;

import com.sasha.ui.BasePage;
import io.appium.java_client.AppiumBy;

public class DropDownAppPage extends BasePage {

    public DropDownAppPage clickOnDropDownMenu() {
        findElement(AppiumBy.xpath("//android.widget.Spinner[@resource-id='com.jaimin.spinner:id/spinner1']")).click();
        return this;
    }
}
