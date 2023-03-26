package com.sasha.utils.testng;

import com.sasha.utils.annotations.AppName;
import com.sasha.utils.ui.LocalDriverManager;
import org.testng.*;

public class TestNGListener extends TestListenerAdapter implements IClassListener {
    @Override
    public synchronized void onBeforeClass(ITestClass itestClass) {
        String a = itestClass.getRealClass().getAnnotation(AppName.class).appName();
        LocalDriverManager.setAppName(a);
    }

    @Override
    public synchronized void onAfterClass(ITestClass itestClass) {
        LocalDriverManager.setAppName(null);
    }
}
