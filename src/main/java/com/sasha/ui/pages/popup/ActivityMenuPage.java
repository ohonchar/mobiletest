package com.sasha.ui.pages.popup;

import com.sasha.ui.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ActivityMenuPage extends BasePage {
    @AndroidFindBy(accessibility = "Hello World")
    private WebElement helloWorldItem;

    public HelloWorldPage chooseHelloWorldItem() {
        helloWorldItem.click();
        log.info("[STEP]::Choose Hello World Item");
        return new HelloWorldPage();
    }
}
