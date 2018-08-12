package ru.mobiledimension.megaapp.screens.tabs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import ru.mobiledimension.megaapp.screens.BaseScreen;

public class ProfileLandingScreen extends BaseScreen {

    public ProfileLandingScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @iOSFindBy(accessibility = "Войти")
    @AndroidFindBy(id = "ru.mobiledimension.mega:id/login_btn")
    MobileElement profileSection;

    @Step("Перехожу в раздел \"Профиль\"...")
    public void clickProfileSection() {
        click(profileSection);
    }
}
