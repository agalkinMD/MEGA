package ru.mobiledimension.megaapp.screens.tabs;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import ru.mobiledimension.megaapp.screens.BaseScreen;

public class ProfileTab extends BaseScreen {

    public ProfileTab(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "ru.mobiledimension.mega:id/login_btn")
    private AndroidElement profileSection;

    public void clickProfileSection() {
        click(profileSection);
    }
}
