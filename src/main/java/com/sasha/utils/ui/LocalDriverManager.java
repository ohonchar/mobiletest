package com.sasha.utils.ui;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class LocalDriverManager {
    private static Logger log = LogManager.getRootLogger();
    private static final ThreadLocal<WebDriver> mobileDriver = new ThreadLocal<>();
    private static final ThreadLocal<String> applicationName = new ThreadLocal<>();

    public static synchronized void setAppName(String appName) {
        applicationName.set(appName);
    }

    public static synchronized void setMobileDriver(WebDriver driver) {
        mobileDriver.set(driver);
    }

    public static synchronized WebDriver getMobileDriver() {
        if (mobileDriver.get() == null) {
            log.debug("[LDM]::Initializing mobile driver");
            setMobileDriver(getMobileDriver("Android"));
        } else {
            log.debug("[LDM]::Mobile driver already initialized");
        }
        return mobileDriver.get();
    }

    private static WebDriver getMobileDriver(String osType) {
        String resourcesDirectory = new File("src/main/resources/apps").getAbsolutePath();
        URL localAppiumUrl;
        try {
            //TODO for the remote running, url should have some factory as well
            localAppiumUrl = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        DesiredCapabilities dc = new DesiredCapabilities();

        switch (osType) {
            case "iOS":
                dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
                dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                dc.setCapability(MobileCapabilityType.APP, "/some/path/to/app.ipa");
                log.debug("[LDM]::iOS platform capabilities");
                return new IOSDriver(localAppiumUrl, dc);

            default:
            case "Android":
                dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                dc.setCapability(MobileCapabilityType.APP, String.format("%s/%s.apk",resourcesDirectory, applicationName.get()));
                log.debug("[LDM]::iOS platform capabilities");
                return new AndroidDriver(localAppiumUrl, dc);
        }
    }

}
