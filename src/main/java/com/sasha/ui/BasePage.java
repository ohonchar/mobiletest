package com.sasha.ui;

import com.sasha.utils.ui.LocalDriverManager;
import com.sasha.utils.ui.UiHelpers;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage extends UiHelpers {
    protected BasePage() {
        PageFactory.initElements(new AppiumFieldDecorator(LocalDriverManager.getMobileDriver()), this);
    }
    protected static Logger log = LogManager.getRootLogger();
    protected final String extendedTextLocator = "/android.widget.TextView";
    public <T> T scrollAndClickOnItemByText(String item, T type) {
        scrollTo(item).click();
        log.info("[STEP]::Scrolled and clicked on item {}, successful", item);
        return type;
    }
 }
