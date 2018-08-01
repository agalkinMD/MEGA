package ru.mobiledimension.megaapp.screens.mallselection;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import ru.mobiledimension.megaapp.screens.BaseScreen;

public class MallSelectionScreen extends BaseScreen {
    public MallSelectionScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @iOSFindBy(accessibility = "Выбери свою МЕГУУ")
    @AndroidFindBy(id = "ru.mobiledimension.mega:id/choose_mall_hint")
    MobileElement chooseMallLabel;

    @iOSFindBy(accessibility = "Белая Дача")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]")
    MobileElement belayaDachaButton;

    @Step("Проверяю, что лейбл \"Выбери свою МЕГУ\" существует...")
    public boolean isChooseMallLabelPresent() {
        return chooseMallLabel.isDisplayed();
    }

    @Step("Перехожу на главный экран МЕГИ Белая Дача...")
    public void clickBelayaDachaButton() {
        click(belayaDachaButton);
    }
}
