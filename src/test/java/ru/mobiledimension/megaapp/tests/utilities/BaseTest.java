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
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    AppiumDriver driver;
    AppiumDriverLocalService service;

    private AppiumDriver setUp(String deviceName, String platform, String udid, String mobilePort, String serverPort) {
        if (driver != null)
            return driver;

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

                driver = new IOSDriver(service.getUrl(), capabilities);

                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

                return driver;

            case "android":
                //capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, Integer.valueOf(mobilePort));
                capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "ru.mobiledimension.mega");
                capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "ru.mobiledimension.mega.ui.splash.SplashActivity");
                capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "ru.mobiledimension.mega.ui.navigation.v2.NavigationActivity");

                driver = new AndroidDriver(service.getUrl(), capabilities);

                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

                return driver;

            default:
                return driver = null;
        }
    }

    protected AppiumDriver getDriver() {
        return driver;
    }

    protected AppiumDriver setDriver(String deviceName, String platform, String udid, String mobilePort, String serverPort) {
        if (driver == null)
            return setUp(deviceName, platform, udid, mobilePort, serverPort);
        return driver;
    }
}
