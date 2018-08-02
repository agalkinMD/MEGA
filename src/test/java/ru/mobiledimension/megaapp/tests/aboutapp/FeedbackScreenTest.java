package ru.mobiledimension.megaapp.tests.aboutapp;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.mobiledimension.megaapp.screens.aboutapp.FeedbackScreen;
import ru.mobiledimension.megaapp.screens.tabs.BottomTab;
import ru.mobiledimension.megaapp.screens.tabs.MenuLandingScreen;
import ru.mobiledimension.megaapp.tests.BaseTest;

public class FeedbackScreenTest extends BaseTest {
    BottomTab bottomTab;
    MenuLandingScreen menuLandingScreen;
    FeedbackScreen feedbackScreen;

    @BeforeClass(description = "Создание объектов экранов",
            alwaysRun = true)
    @Parameters({"deviceName", "platform", "udid", "mobilePort", "serverPort"})
    public void createPageObjects(String deviceName, String platform, String udid, String mobilePort, String serverPort) {
        bottomTab = new BottomTab(getDriver(deviceName, platform, udid, mobilePort, serverPort));
        menuLandingScreen = new MenuLandingScreen(getDriver(deviceName, platform, udid, mobilePort, serverPort));
        feedbackScreen = new FeedbackScreen(getDriver(deviceName, platform, udid, mobilePort, serverPort));
    }

    @BeforeClass(description = "Переход на целевой экран",
            alwaysRun = true)
    public void goToTargetScreen() {
        bottomTab.clickMenuLandingScreen();
        menuLandingScreen.clickAboutAppSection();
    }

    @Test(description = "Заголовок \"О приложении\" отображается",
            groups = {"functional", "check-in"})
    @Description("Заголовок \"О приложении\" отображается при открытии соответствующего")
    @Story("Пользователь собирается отправить отзыв о приложении")
    @Severity(SeverityLevel.NORMAL)
    public void isDisplayedAboutAppSectionTitle() {
        Assert.assertTrue(feedbackScreen.isDisplayedAboutAppSectionTitle());
    }

    @AfterClass(description = "Возврат на главный экран", alwaysRun = true)
    public void goToStartScreen() {
        bottomTab.clickMenuLandingScreen();
        bottomTab.clickMainLandingScreen();
    }
}
