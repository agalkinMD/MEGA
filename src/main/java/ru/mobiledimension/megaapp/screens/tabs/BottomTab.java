package ru.mobiledimension.megaapp.screens.tabs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import ru.mobiledimension.megaapp.screens.BaseScreen;

public class BottomTab extends BaseScreen {

    public BottomTab(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @iOSFindBy(accessibility = "Главная")
    @AndroidFindBy(id = "ru.mobiledimension.mega:id/tab_home")
    MobileElement home;

    @iOSFindBy(accessibility = "Профиль")
    @AndroidFindBy(id = "ru.mobiledimension.mega:id/tab_profile")
    MobileElement profile;

    @Step("Перехожу в таб \"Главная\"...")
    public void clickMainLandingScreen() {
        click(home);
    }

    @Step("Перехожу в таб \"Профиль\"...")
    public void clickProfileLandingScreen() {
        click(profile);
    }
}
