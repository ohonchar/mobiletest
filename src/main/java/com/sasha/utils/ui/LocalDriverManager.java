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
    private static final Logger log = LogManager.getRootLogger();
    protected static String androidBundleId = "io.appium.android.apis";
    protected static String iOSBundleId = "com.apple.Preferences";
    private static final ThreadLocal<WebDriver> mobileDriver = new ThreadLocal<>();
    private static final ThreadLocal<String> applicationName = new ThreadLocal<>();
    private static final ThreadLocal<DesiredCapabilities> dc = new ThreadLocal<>();

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


        switch (osType) {
            case "iOS":
                setCapabilities("iOS", "XCUITest", resourcesDirectory);
                log.debug("[LDM]::iOS platform capabilities");
                return new IOSDriver(localAppiumUrl, dc.get());

            default:
            case "Android":
                setCapabilities("Android", "UiAutomator2", resourcesDirectory);
                log.debug("[LDM]::iOS platform capabilities");
                return new AndroidDriver(localAppiumUrl, dc.get());
        }
    }

    private static synchronized void setCapabilities(String platformName, String automationName, String resourcesDirectory) {
        String fileExtension = (platformName.equals("Android")) ? "apk" : "ipa";
        DesiredCapabilities tempDc = new DesiredCapabilities();
        tempDc.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        tempDc.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
        tempDc.setCapability(MobileCapabilityType.APP, String.format("%s/%s.%s", resourcesDirectory, applicationName.get(), fileExtension));
        dc.set(tempDc);
    }

}
