package ru.mobiledimension.megaapp.tests.profile;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.mobiledimension.megaapp.screens.profile.SignInScreen;
import ru.mobiledimension.megaapp.screens.profile.SignUpScreen;
import ru.mobiledimension.megaapp.screens.tabs.BottomTab;
import ru.mobiledimension.megaapp.screens.tabs.ProfileLandingScreen;
import ru.mobiledimension.megaapp.tests.utilities.BaseTest;

@Epic("Личный кабинет пользователя")
@Feature("Процесс регистрации пользователя")
public class SignUpScreenTest extends BaseTest {
    BottomTab bottomTab;
    ProfileLandingScreen profileLandingScreen;
    SignInScreen signInScreen;
    SignUpScreen signUpScreen;

    @BeforeClass(description = "Создание объектов экранов",
            alwaysRun = true)
    @Parameters({"deviceName", "platform", "udid", "mobilePort", "serverPort"})
    public void createPageObjects(String deviceName, String platform, String udid, String mobilePort, String serverPort) {
        bottomTab = new BottomTab(setDriver(deviceName, platform, udid, mobilePort, serverPort));
        profileLandingScreen = new ProfileLandingScreen(setDriver(deviceName, platform, udid, mobilePort, serverPort));
        signInScreen = new SignInScreen(setDriver(deviceName, platform, udid, mobilePort, serverPort));
        signUpScreen = new SignUpScreen(setDriver(deviceName, platform, udid, mobilePort, serverPort));
    }

    @BeforeClass(description = "Переход на целевой экран",
            alwaysRun = true)
    public void goToTargetScreen() {
        bottomTab.clickProfileLandingScreen();
        profileLandingScreen.clickProfileSection();
        signInScreen.clickSignUpButton();
    }

    @BeforeMethod(description = "Подготовка тестового окружения",
            alwaysRun = true)
    public void prepareTestEnvironment() {
        signUpScreen.clearUserData();
    }

    @Test(description = "Кнопка \"Регистрация\" доступна для нажатия",
            groups = {"functional", "check-in"})
    @Description("Кнопка \"Регистрация\" доступна для нажатия в случае, если поле \"Имя\" / \"E-mail\" / \"Пароль\" заполнено валидным значением")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Пользователь собирается зарегистрироваться, вводя корректные данные регистрации")
    public void isEnabledSignUpButton() {
        signUpScreen.setUserData("Anton", "agalkin@mobiledimension.ru", "Qazxswdf12");
        signUpScreen.hideKeyBoard();
        signUpScreen.setPersonalDataProccessingSwitch();
        Assert.assertTrue(signUpScreen.isEnabledSignUpButton());
    }

    @AfterMethod(description = "Очистка тестового окружения", alwaysRun = true)
    public void clearTestEnvironment() {
        signUpScreen.clickBackButton();
        signInScreen.clickSignUpButton();
    }

    @AfterClass(description = "Возврат на главный экран", alwaysRun = true)
    public void goToStartScreen() {
        bottomTab.clickProfileLandingScreen();
        bottomTab.clickMainLandingScreen();
    }
}
