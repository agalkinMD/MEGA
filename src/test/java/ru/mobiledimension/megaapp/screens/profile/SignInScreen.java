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

public class SignInScreen extends BaseScreen {

    public SignInScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"МЕГА DEV\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeTextField")
    @AndroidFindBy(id = "ru.mobiledimension.mega:id/loginEditText")
    MobileElement userLoginTextField;

    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"МЕГА DEV\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeSecureTextField")
    @AndroidFindBy(id = "ru.mobiledimension.mega:id/passwordEditText")
    MobileElement userPasswordTextField;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name=\"Войти\"]")
    @AndroidFindBy(id = "ru.mobiledimension.mega:id/loginButton")
    MobileElement signInButton;

    @iOSFindBy(accessibility = "Зарегистрироваться")
    @AndroidFindBy(id = "ru.mobiledimension.mega:id/registerButton")
    MobileElement signUpButton;

    @iOSFindBy(accessibility = "Готово")
    MobileElement keyboardDoneButton;

    @Step("Перехожу на экран регистрации...")
    public void clickSignUpButton() {
        click(signUpButton);
    }

    @Step("Проверяю, что кнопка \"Войти\" (не)доступна для нажатия...")
    public boolean isEnabledSignInButton() {
        return signInButton.isEnabled();
    }

    @Step("Заполняю поле \"E-mail\" значением \"{0}\" и поле \"Пароль\" - значением \"{1}\"...")
    public void setUserData(String userLogin, String userPassword) {
        userLoginTextField.sendKeys(userLogin);
        userPasswordTextField.sendKeys(userPassword);
    }

    @Step("Очищаю поля \"E-mail\" и \"Пароль\"...")
    public void clearUserData() {
        userLoginTextField.clear();
        userPasswordTextField.clear();
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
