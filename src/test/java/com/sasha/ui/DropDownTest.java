package com.sasha.ui;

import com.sasha.ui.pages.DropDownAppPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DropDownTest extends BaseTest {
    DropDownTest() {
        super("spinner");
    }

    @Test(groups = "all_ui")
    public void verifyDropDownItemTest() {
        DropDownAppPage dropDownAppPage = new DropDownAppPage();
        String expectedDropDownText = "CYAN";
        String actualDropDownText = dropDownAppPage
                .clickOnDropDownMenu()
                .scrollAndClickOnItemByText(expectedDropDownText, dropDownAppPage)
                .getDropDownText();
        assertThat(actualDropDownText)
                .as("Expected Dropdown text should have: %s", expectedDropDownText)
                .isEqualTo(expectedDropDownText);
        log.info("[TEST]::Verify dropdown menu item exists in menu list");
    }

}

