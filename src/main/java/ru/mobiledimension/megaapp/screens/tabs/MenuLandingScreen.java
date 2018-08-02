package ru.mobiledimension.megaapp.screens.tabs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import ru.mobiledimension.megaapp.screens.BaseScreen;

public class MenuLandingScreen extends BaseScreen {
    public MenuLandingScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "ru.mobiledimension.mega:id/about_btn")
    @iOSFindBy(accessibility = "О приложении")
    MobileElement aboutAppSection;

    public void clickAboutAppSection() {
        boolean isNotVisible = true;

        do {
            try {
                if (aboutAppSection.isDisplayed())
                    isNotVisible = driver.getPlatformName().equals("android") ? false : true;
                if (isNotVisible)
                    if (aboutAppSection.getAttribute("visible").equals("false"))
                        scroll();
                    else isNotVisible = false;
            }
            catch (NoSuchElementException e) {
                scroll();
            }
        }
        while (isNotVisible);

        click(aboutAppSection);
    }
}
