package com.sasha.ui.pages.spinner.widgets;

import com.sasha.ui.pages.DefaultStubWidget;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class DropDownWidget extends DefaultStubWidget {
    protected static Logger log = LogManager.getRootLogger();

    protected DropDownWidget(WebElement element) {
        super(element);
    }
    protected final String extendedTextLocator = "//android.widget.TextView";

    @AndroidFindBy(xpath = extendedTextLocator)
    private DropDownWidget dropDown;

    @SuppressWarnings("unchecked")
    @Override
    public DropDownWidget getSubWidget() {
        return dropDown;
    }
}
