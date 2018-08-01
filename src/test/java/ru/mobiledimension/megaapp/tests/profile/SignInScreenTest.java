package ru.mobiledimension.megaapp.tests.profile;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.mobiledimension.megaapp.screens.profile.SignInScreen;
import ru.mobiledimension.megaapp.screens.tabs.BottomTab;
import ru.mobiledimension.megaapp.screens.tabs.ProfileLandingScreen;
import ru.mobiledimension.megaapp.tests.BaseTest;

@Epic("Личный кабинет пользователя")
@Feature("Процесс авторизации пользователя")
public class SignInScreenTest extends BaseTest {

    BottomTab bottomTab;
    ProfileLandingScreen profileLandingScreen;
    SignInScreen signInScreen;

    @DataProvider(name = "userData")
    public Object[][] userData() {
        return new Object[][]{
                {"agalkin@mobiledimension.ru", "qazxswdf12"},
                {"agalkin@mobiledimension.ru", ""},
                {"agalkin", "Qazxswdf12"},
                {"", "Qazxswdf12"},
                {"", ""}
        };
    }

    @BeforeClass(description = "Создание объектов экранов",
            alwaysRun = true)
    @Parameters({"deviceName", "platform", "udid", "mobilePort", "serverPort"})
    public void createPageObjects(String deviceName, String platform, String udid, String mobilePort, String serverPort) {
        bottomTab = new BottomTab(getDriver(deviceName, platform, udid, mobilePort, serverPort));
        profileLandingScreen = new ProfileLandingScreen(getDriver(deviceName, platform, udid, mobilePort, serverPort));
        signInScreen = new SignInScreen(getDriver(deviceName, platform, udid, mobilePort, serverPort));
    }

    @BeforeClass(description = "Переход на целевой экран",
            alwaysRun = true)
    public void goToTargetScreen() {
        bottomTab.clickProfileLandingScreen();
        profileLandingScreen.clickProfileSection();
    }

    @BeforeMethod(description = "Подготовка тестового окружения",
            alwaysRun = true)
    public void prepareTestEnvironment() {
        signInScreen.clearUserData();
    }

    @Test(description = "Кнопка \"Войти\" доступна для нажатия",
            groups = {"functional", "check-in"})
    @Description("Кнопка \"Войти\" доступна для нажатия в случае, если поле \"E-mail\" / \"Пароль\" заполнено валидным значением")
    @Story("Пользователь собирается авторизоваться, вводя корректные данные авторизации")
    @Severity(SeverityLevel.BLOCKER)
    public void isEnabledSignInButton() {
        signInScreen.setUserData("agalkin@mobiledimension.ru", "Qazxswdf12");
        signInScreen.hideKeyBoard();
        Assert.assertTrue(signInScreen.isEnabledSignInButton());
    }

    @Test(description = "Кнопка \"Войти\" недоступна для нажатия",
            groups = {"functional"},
            dataProvider = "userData")
    @Description("Кнопка \"Войти\" недоступна для нажатия в случае, если поле \"E-mail\" / \"Пароль\" - пустое или заполнено невалидным значением")
    @Story("Пользователь собирается авторизоваться, вводя некорректные данные авторизации")
    @Severity(SeverityLevel.NORMAL)
    public void isDisabledSignInButton(String userName, String userPassword) {
        signInScreen.setUserData(userName, userPassword);
        signInScreen.hideKeyBoard();
        Assert.assertFalse(signInScreen.isEnabledSignInButton());
    }

    @AfterClass(description = "Возврат назад на главный экран", alwaysRun = true)
    public void goToStartScreen() {
        bottomTab.clickProfileLandingScreen();
        bottomTab.clickMainLandingScreen();
    }
}
