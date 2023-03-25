package com.sasha.ui;

import com.sasha.ui.pages.DropDownAppPage;
import org.testng.annotations.Test;

public class SimpleTest extends BaseTest {


    @Test
    public void firstTest() {
        new DropDownAppPage().clickOnDropDownMenu();
    }

}

