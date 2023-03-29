package com.sasha.ui.pages.popup;

import com.sasha.ui.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

public class AlertDialogPage extends BasePage {
    @AndroidFindBy(accessibility = "List dialog")
    private WebElement listDialog;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Command three']")
    private WebElement popUpAlertTitleThree;

    public AlertDialogPage chooseListDialogItem() {
        listDialog.click();
        log.info("[STEP]::Choose List Dialog Item");
        return this;
    }

    public <T> T catchPopUpAlertOnListDialog(T type) {
        Alert alert = isAlertPresent();
        if (alert != null) {
            popUpAlertTitleThree.click();
            goBack(type);
        }
        log.info("[STEP]::Catch popup alert on List Dialog");
        return type;
    }
}
