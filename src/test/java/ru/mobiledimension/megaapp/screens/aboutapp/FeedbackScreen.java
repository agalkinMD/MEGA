package ru.mobiledimension.megaapp.screens.aboutapp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import ru.mobiledimension.megaapp.screens.BaseScreen;

public class FeedbackScreen extends BaseScreen {
    public FeedbackScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "ru.mobiledimension.mega:id/about_title")
    @iOSFindBy(xpath = "//XCUIElementTypeOther[@name=\"О приложении\"]")
    MobileElement screenTitle;

    public boolean isDisplayedAboutAppSectionTitle() {
        return screenTitle.isDisplayed();
    }
}
