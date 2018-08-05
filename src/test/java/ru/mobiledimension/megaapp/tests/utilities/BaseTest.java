package ru.mobiledimension.megaapp.tests.utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    static AppiumDriver driver;

    AppiumDriverLocalService service;

    private AppiumDriver setUp(String deviceName, String platform, String udid, String mobilePort, String serverPort) {
        service = new AppiumServiceBuilder().usingPort(Integer.valueOf(serverPort)).build();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException("An Appium server node is not started!");
        }

        String[] platformInfo = platform.split(" ");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformInfo[0]);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformInfo[1]);
        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);


        switch (platformInfo[0].toLowerCase()) {
            case "ios":
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, Integer.valueOf(mobilePort));
                capabilities.setCapability(IOSMobileCapabilityType.USE_PREBUILT_WDA, true);
                capabilities.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, false);
                capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "ru.mobiledimension.Mega");

                if (driver != null)
                    return driver;

                driver = new IOSDriver(service.getUrl(), capabilities);

                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

                return driver;

            case "android":
                //capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, Integer.valueOf(mobilePort));
                capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "ru.mobiledimension.mega");
                capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "ru.mobiledimension.mega.ui.splash.SplashActivity");
                capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "ru.mobiledimension.mega.ui.navigation.v2.NavigationActivity");

                if (driver != null)
                    return driver;

                driver = new AndroidDriver(service.getUrl(), capabilities);

                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

                return driver;

            default:
                return driver = null;
        }
    }

    protected AppiumDriver getDriver(String deviceName, String platform, String udid, String mobilePort, String serverPort) {
        if (driver == null)
            if (setUp(deviceName, platform, udid, mobilePort, serverPort) == null)
                System.out.println("Can't instantiate a new driver instance due to invalid OS type!");
        return driver;
    }

    private void deleteAllureHistory() {
        File trendReport = new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/allure-report/history/history-trend.json");
        File historyReport = new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/allure-report/history/history.json");
        File trendResults = new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/allure-results/history/history-trend.json");
        File historyResults = new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/allure-results/history/history.json");
        trendReport.delete();
        historyReport.delete();
        trendResults.delete();
        historyResults.delete();
    }

    @AfterSuite(alwaysRun = true)
    private void prepareAllureReport() {
        deleteAllureHistory();
    }
}
