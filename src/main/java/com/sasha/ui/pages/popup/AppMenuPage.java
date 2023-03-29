package com.sasha.ui.pages.popup;

import com.sasha.ui.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class AppMenuPage extends BasePage {
    @AndroidFindBy(accessibility = "Alert Dialogs")
    private WebElement alertDialogItem;

    @AndroidFindBy(accessibility = "Activity")
    private WebElement activityItem;

    public AlertDialogPage chooseAlertDialogsItem() {
        alertDialogItem.click();
        log.info("[STEP]::Choose Alert Dialogs Item");
        return new AlertDialogPage();
    }

    public ActivityMenuPage chooseActivityItem() {
        activityItem.click();
        log.info("[STEP]::Choose Activity Item");
        return new ActivityMenuPage();
    }
}
