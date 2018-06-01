package ru.mobiledimension.megaapp.tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    protected static AndroidDriver driver;

    private String serverIp = "0.0.0.0";
    private String serverPort = "4723";

    private void setup() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Nexus 5");
        capabilities.setCapability("udid", "0391d3a4437f32ab"); //Give Device ID of your mobile phone
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "6.0.1");
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("appPackage", "ru.mobiledimension.mega");
        capabilities.setCapability("appActivity", "ru.mobiledimension.mega.ui.splash.SplashActivity");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("appWaitActivity", "ru.mobiledimension.mega.ui.navigation.v2.NavigationActivity");

        String serverUrl = "http://" + serverIp + ":" + serverPort + "/wd/hub";

        try {
            driver = new AndroidDriver(new URL(serverUrl), capabilities);
        }
        catch (MalformedURLException e) { }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeSuite
    protected AndroidDriver getDriver() {
        if (driver == null) {
            setup();
        }
        return driver;
    }

    @AfterSuite
    protected void tearDown() {
        driver.quit();
    }

}
