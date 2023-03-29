package com.sasha.ui.pages.popup;

import com.sasha.ui.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HelloWorldPage extends BasePage {
    @AndroidFindBy(accessibility = "Hello, World!")
    private WebElement helloWorldView;

    public String getTextFromHelloWorldView() {
        log.info("[STEP]::Retrieve text from display view");
        return helloWorldView.getText();
    }
}
