package ru.mobiledimension.megaapp.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Point;

import java.time.Duration;

public abstract class BaseScreen {
    TouchAction touchAction;
    protected AppiumDriver driver;

    public BaseScreen(AppiumDriver driver) {
        this.driver = driver;
        touchAction = new TouchAction(driver);
    }

    protected void click(MobileElement element) {
        touchAction.tap(new ElementOption().withElement(element)).perform();
    }

    protected void scroll() {
        int coordinateX = driver.manage().window().getSize().width / 3;
        int initialCoordinateY = driver.manage().window().getSize().height * 4 / 5;
        int finalCoordinateY = driver.manage().window().getSize().height / 8 ;

        //PointOption initialCoordinates = new PointOption().withCoordinates(coordinateX, initialCoordinateY);
        PointOption finalCoordinates  = new PointOption().withCoordinates(coordinateX, finalCoordinateY);
        WaitOptions wait = new WaitOptions();

        touchAction.press(new PointOption().withCoordinates(coordinateX, initialCoordinateY))
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
                .moveTo(new PointOption().withCoordinates(coordinateX, finalCoordinateY))
                .release()
                .perform();
    }

    protected boolean isKeyBoardShown() {
        return ((AndroidDriver) driver).isKeyboardShown();
    }

    protected void hideKeyBoard() {
        driver.hideKeyboard();
    }
}
