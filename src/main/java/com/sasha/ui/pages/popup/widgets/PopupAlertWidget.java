package com.sasha.ui.pages.popup.widgets;

import com.sasha.ui.pages.DefaultStubWidget;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;
@AndroidFindBy(xpath = "//android.widget.ListView[@resource-id='android:id/select_dialog_listview']")
public class PopupAlertWidget extends DefaultStubWidget {
    protected PopupAlertWidget(WebElement element) {
        super(element);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView")
    private List<PopupAlertWidget> popUpAlertList;

    @SuppressWarnings("unchecked")
    @Override
    public List<PopupAlertWidget> getSubWidgets() {
        return popUpAlertList;
    }
}
