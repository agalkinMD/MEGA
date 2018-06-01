package ru.mobiledimension.megaapp.screens.profile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import ru.mobiledimension.megaapp.screens.BaseScreen;

public class SignInScreen extends BaseScreen {

    public SignInScreen(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "ru.mobiledimension.mega:id/loginEditText")
    AndroidElement userLogin;

    @AndroidFindBy(id = "ru.mobiledimension.mega:id/passwordEditText")
    AndroidElement userPassword;

    @AndroidFindBy(id = "ru.mobiledimension.mega:id/loginButton")
    AndroidElement signInButton;

    public boolean isEnabledSignInButton() {
        return signInButton.isEnabled();
    }

    public void setUserLogin(String sUserLogin) {
        userLogin.sendKeys(sUserLogin);
    }

    public void setUserPassword(String sUserPassword) {
        userPassword.sendKeys(sUserPassword);
    }

    public void clearUserData() {
        userLogin.clear();
        userPassword.clear();
    }
}
