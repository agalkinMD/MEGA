package ru.mobiledimension.megaapp.screens.tabs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import ru.mobiledimension.megaapp.screens.BaseScreen;

public class MainLandingScreen extends BaseScreen {
    public MainLandingScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @iOSFindBy(accessibility = "Белая Дача")
    @AndroidFindBy(id = "ru.mobiledimension.mega:id/title_tvv")
    MobileElement mallChange;

    @Step("Перехожу на экран выбора МЕГИ...")
    public void clickChangeMall() {
        click(mallChange);
    }
}
