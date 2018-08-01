package ru.mobiledimension.megaapp.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;

public abstract class BaseScreen {
    TouchAction touchAction;
    AppiumDriver driver;

    public BaseScreen(AppiumDriver driver) {
        this.driver = driver;
        touchAction = new TouchAction(driver);
    }

    protected void click(MobileElement element) {
        touchAction.tap(new ElementOption().withElement(element)).perform();
    }

    protected boolean isKeyBoardShown() {
        return ((AndroidDriver) driver).isKeyboardShown();
    }

    protected void hideKeyBoard() {
        driver.hideKeyboard();
    }
}
