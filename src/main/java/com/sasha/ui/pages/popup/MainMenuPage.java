package com.sasha.ui.pages.popup;

import com.sasha.ui.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MainMenuPage extends BasePage {
    @AndroidFindBy(accessibility = "App")
    private WebElement appMenuItem;

    public AppMenuPage chooseAppMenuItem() {
        appMenuItem.click();
        log.info("[STEP]::Choose App from menu items");
        return new AppMenuPage();
    }


}
