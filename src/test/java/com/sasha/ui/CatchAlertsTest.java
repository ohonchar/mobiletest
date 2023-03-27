package com.sasha.ui;

import com.sasha.ui.pages.PopUpPage;
import com.sasha.utils.annotations.AppName;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@AppName(appName = "ApiDemos-debug")
public class CatchAlertsTest extends BaseTest {
    private PopUpPage popUpPage;

    @Test(groups = {"all_ui", "popup"})
    public void catchUnexpectedPopUpIsPresent() {
        popUpPage = new PopUpPage();
        String actualText = popUpPage
                .chooseAppMenuItem()
                .chooseAlertDialogsItem()
                .chooseListDialogItem()
                .catchPopUpAlertOnListDialog(popUpPage)
                .goBack(popUpPage)
                .chooseActivityItem()
                .chooseHelloWorldItem()
                .getTextFromHelloWorldView();
        assertThat(actualText).as("").isEqualTo("Hello, World!");

    }

    @Test(groups = {"all_ui", "popup"})
    public void catchUnexpectedPopUpIsAbsent() {
        popUpPage = new PopUpPage();
        String actualText = popUpPage
                .chooseAppMenuItem()
                .chooseAlertDialogsItem()
                .catchPopUpAlertOnListDialog(popUpPage)
                .goBack(popUpPage)
                .chooseActivityItem()
                .chooseHelloWorldItem()
                .getTextFromHelloWorldView();
        assertThat(actualText).as("").isEqualTo("Hello, World!");
        log.info("[TEST]::Verify popup menu contains Share item and it is clickable");
    }
}
