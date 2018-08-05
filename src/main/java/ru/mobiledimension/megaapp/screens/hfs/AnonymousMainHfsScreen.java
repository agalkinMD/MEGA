package ru.mobiledimension.megaapp.screens.hfs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import ru.mobiledimension.megaapp.screens.BaseScreen;

public class AnonymousMainHfsScreen extends BaseScreen {
    public AnonymousMainHfsScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @iOSFindBy(id = "Шопинг налегке")
    MobileElement screenTitle;

    public boolean isDisplayedAnonymousMainHfsScreenTitle() {
        return screenTitle.isDisplayed();
    }
}
