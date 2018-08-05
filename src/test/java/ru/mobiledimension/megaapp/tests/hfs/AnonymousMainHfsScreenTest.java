package ru.mobiledimension.megaapp.tests.hfs;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.mobiledimension.megaapp.screens.hfs.AnonymousMainHfsScreen;
import ru.mobiledimension.megaapp.screens.tabs.BottomTab;
import ru.mobiledimension.megaapp.screens.tabs.MenuLandingScreen;
import ru.mobiledimension.megaapp.tests.utilities.BaseTest;

public class AnonymousMainHfsScreenTest extends BaseTest {
    BottomTab bottomTab;
    MenuLandingScreen menuLandingScreen;
    AnonymousMainHfsScreen anonymousMainHfsScreen;

    @BeforeClass(description = "Создание объектов экранов",
            alwaysRun = true)
    @Parameters({"deviceName", "platform", "udid", "mobilePort", "serverPort"})
    public void createPageObjects(String deviceName, String platform, String udid, String mobilePort, String serverPort) {
        bottomTab = new BottomTab(getDriver(deviceName, platform, udid, mobilePort, serverPort));
        menuLandingScreen = new MenuLandingScreen(getDriver(deviceName, platform, udid, mobilePort, serverPort));
        anonymousMainHfsScreen = new AnonymousMainHfsScreen(getDriver(deviceName, platform, udid, mobilePort, serverPort));
    }

    @BeforeClass(description = "Переход на целевой экран",
            alwaysRun = true)
    public void goToTargetScreen() {
        bottomTab.clickMenuLandingScreen();
        menuLandingScreen.clickHfsSection();
    }

    @Test(description = "Заголовок \"Шопинг налегке\" отображается",
            groups = {"functional", "check-in"})
    @Description("Заголовок \"Шопинг налегке\" отображается при открытии соответствующего экрана")
    @Story("Пользователь впервые заходит в раздел")
    @Severity(SeverityLevel.NORMAL)
    public void isDisplayedAnonymousMainHfsScreenTitle() {
        Assert.assertTrue(anonymousMainHfsScreen.isDisplayedAnonymousMainHfsScreenTitle());
    }

    @AfterClass(description = "Возврат на главный экран", alwaysRun = true)
    public void goToStartScreen() {
        bottomTab.clickMenuLandingScreen();
        bottomTab.clickMainLandingScreen();
    }
}