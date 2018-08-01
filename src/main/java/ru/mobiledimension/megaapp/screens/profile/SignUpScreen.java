package ru.mobiledimension.megaapp.screens.profile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import ru.mobiledimension.megaapp.screens.BaseScreen;

public class SignUpScreen extends BaseScreen {

    public SignUpScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"МЕГА DEV\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]")
    @AndroidFindBy(id = "ru.mobiledimension.mega:id/nameEditText")
    MobileElement userNameTextField;

    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"МЕГА DEV\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeTextField[2]")
    @AndroidFindBy(id = "ru.mobiledimension.mega:id/loginEditText")
    MobileElement userLoginTextField;

    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"МЕГА DEV\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeSecureTextField")
    @AndroidFindBy(id = "ru.mobiledimension.mega:id/passwordEditText")
    MobileElement userPasswordTextField;

    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"МЕГА DEV\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeSwitch")
    @AndroidFindBy(id = "ru.mobiledimension.mega:id/agree_switch")
    MobileElement personalDataProccessingSwitch;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name=\"Регистрация\"]")
    @AndroidFindBy(id = "ru.mobiledimension.mega:id/registerButton")
    MobileElement signUpButton;

    @iOSFindBy(accessibility = "Войти")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.ImageButton")
    MobileElement backButton;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name=\"Готово\"]")
    MobileElement keyboardDoneButton;

    @Step("Возвращаюсь на экран авторизации...")
    public void clickBackButton() {
        click(backButton);
    }

    @Step("Проверяю, что кнопка \"Регистрация\" (не)доступна для нажатия...")
    public boolean isEnabledSignUpButton() {
        return signUpButton.isEnabled();
    }

    @Step("Заполняю поле \"Имя\" значением \"{0}\", поле \"E-mail\" - значением \"{1}\" и поле \"Пароль\" - значением \"{2}\"...")
    public void setUserData(String userName, String userLogin, String userPassword) {
        userNameTextField.sendKeys(userName);
        userLoginTextField.sendKeys(userLogin);
        userPasswordTextField.sendKeys(userPassword);
    }

    @Step("Очищаю поля \"Имя\", \"E-mail\" и \"Пароль\"...")
    public void clearUserData() {
        userNameTextField.clear();
        userLoginTextField.clear();
        userPasswordTextField.clear();
    }

    @Step("Даю согласие на обработку персональных данных...")
    public void setPersonalDataProccessingSwitch() {
        click(personalDataProccessingSwitch);
    }

    @Step("Если необходимо, скрываю клавиатуру...")
    public void hideKeyBoard() {
        try {
            if (keyboardDoneButton.isDisplayed())
                click(keyboardDoneButton);
        }
        catch (NoSuchElementException e) {
            if (isKeyBoardShown())
                super.hideKeyBoard();
        }
    }
}
