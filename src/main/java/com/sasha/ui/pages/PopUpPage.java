package com.sasha.ui.pages;

import com.sasha.ui.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

public class PopUpPage extends BasePage {
    @AndroidFindBy(accessibility = "App")
    private WebElement appMenuItem;

    @AndroidFindBy(accessibility = "Alert Dialogs")
    private WebElement alertDialogItem;

    @AndroidFindBy(accessibility = "List dialog")
    private WebElement listDialog;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Command three']")
    private WebElement popUpAlertTitleThree;

    @AndroidFindBy(accessibility = "Activity")
    private WebElement activityItem;

    @AndroidFindBy(accessibility = "Hello World")
    private WebElement helloWorldItem;

    @AndroidFindBy(accessibility = "Hello, World!")
    private WebElement helloWorldView;

    public PopUpPage chooseAppMenuItem() {
        appMenuItem.click();
        log.info("[STEP]::Choose App from menu items");
        return this;
    }

    public PopUpPage chooseAlertDialogsItem() {
        alertDialogItem.click();
        log.info("[STEP]::Choose Alert Dialogs Item");
        return this;
    }

    public PopUpPage chooseListDialogItem() {
        listDialog.click();
        log.info("[STEP]::Choose List Dialog Item");
        return this;
    }

    public <T> PopUpPage catchPopUpAlertOnListDialog(T type) {
        Alert alert = isAlertPresent();
        if (alert != null) {
            popUpAlertTitleThree.click();
            goBack(type);
        }
        return this;
    }

    public PopUpPage chooseActivityItem() {
        activityItem.click();
        return this;
    }

    public PopUpPage chooseHelloWorldItem() {
        helloWorldItem.click();
        return this;
    }

    public String getTextFromHelloWorldView() {
        return helloWorldView.getText();
    }
}
