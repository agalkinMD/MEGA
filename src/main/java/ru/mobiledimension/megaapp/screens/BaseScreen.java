package ru.mobiledimension.megaapp.screens;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.ElementOption;

public class BaseScreen {
    TouchAction touchAction;

    public BaseScreen(AndroidDriver driver) {
        touchAction = new TouchAction(driver);
    }

    protected void click(AndroidElement element) {
        touchAction.tap(new ElementOption().withElement(element)).perform();
    }
}
