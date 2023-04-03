package com.sasha.ui;

import com.sasha.ui.pages.popup.ActivityMenuPage;
import com.sasha.ui.pages.popup.AppMenuPage;
import com.sasha.ui.pages.popup.MainMenuPage;
import com.sasha.utils.annotations.AppName;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@AppName(appName = "ApiDemos-debug")
public class CatchAlertsTest extends BaseTest {
    private MainMenuPage mainMenuPage;

    @Test(groups = {"all_ui", "popup"})
    public void catchUnexpectedPopUpIsPresent() {
        mainMenuPage = new MainMenuPage();
        ActivityMenuPage activityMenuPage = mainMenuPage
                .chooseAppMenuItem()
                .chooseAlertDialogsItem()
                .chooseListDialogItem()
                .catchPopUpAlertOnListDialog(new ActivityMenuPage(), 2);
        String actualText = activityMenuPage.goBack(new AppMenuPage())
                .chooseActivityItem()
                .chooseHelloWorldItem()
                .getTextFromHelloWorldView();
        assertThat(actualText).as("Screen text should be equals to: Hello, World!").isEqualTo("Hello, World!");
        log.info("[TEST]::Catch unexpected popup when it is present");
    }

    @Test(groups = {"all_ui", "popup"})
    public void catchUnexpectedPopUpIsAbsent() {
        mainMenuPage = new MainMenuPage();
        String actualText = mainMenuPage
                .chooseAppMenuItem()
                .chooseAlertDialogsItem()
                .catchPopUpAlertOnListDialog(mainMenuPage)
                .goBack(new AppMenuPage())
                .chooseActivityItem()
                .chooseHelloWorldItem()
                .getTextFromHelloWorldView();
        assertThat(actualText).as("Screen text should be equals to: Hello, World!").isEqualTo("Hello, World!");
        log.info("[TEST]::Catch unexpected popup when it is absent");
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        mainMenuPage.restartAndroidApp();
    }
}
