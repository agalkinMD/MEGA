package ru.mobiledimension.megaapp.screens.tabs;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import ru.mobiledimension.megaapp.screens.BaseScreen;

public class BottomTab extends BaseScreen {

    public BottomTab(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "ru.mobiledimension.mega:id/tab_home")
    AndroidElement home;

    @AndroidFindBy(id = "ru.mobiledimension.mega:id/tab_profile")
    AndroidElement profile;

    public void clickMainTab() {
        click(home);
    }

    public void clickProfileTab() {
        click(profile);
    }
}
