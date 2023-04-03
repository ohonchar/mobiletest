package com.sasha.ui.pages.popup;

import com.sasha.ui.BasePage;
import com.sasha.ui.pages.popup.widgets.PopupAlertWidget;
import com.sasha.ui.pages.spinner.widgets.DropDownWidget;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AlertDialogPage extends BasePage {
    @AndroidFindBy(accessibility = "List dialog")
    private WebElement listDialog;

    private List<PopupAlertWidget> popupAlertWidget;

    public AlertDialogPage chooseListDialogItem() {
        listDialog.click();
        log.info("[STEP]::Choose List Dialog Item");
        return this;
    }

    public <T> T catchPopUpAlertOnListDialog(T type, Integer...alertItemIndex) {
        int tempIndex = (alertItemIndex.length > 0) ? alertItemIndex[0] : 0;
        Alert alert = isAlertPresent();
        if (alert != null) {
            popupAlertWidget.get(0).getSubWidgets().get(tempIndex).getSelfReference().getWrappedElement().click();
            goBack(type);
        }
        log.info("[STEP]::Catch popup alert on List Dialog");
        return type;
    }
}
