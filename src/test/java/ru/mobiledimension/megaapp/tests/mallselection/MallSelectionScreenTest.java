package ru.mobiledimension.megaapp.tests.mallselection;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.mobiledimension.megaapp.screens.tabs.MainLandingScreen;
import ru.mobiledimension.megaapp.screens.mallselection.MallSelectionScreen;
import ru.mobiledimension.megaapp.tests.utilities.BaseTest;

@Epic("Базовый функционал")
@Feature("Процесс смены МЕГИ")
public class MallSelectionScreenTest extends BaseTest {

    MainLandingScreen mainLandingScreen;
    MallSelectionScreen mallSelectionScreen;

    @BeforeClass(description = "Создание объектов экранов",
            alwaysRun = true)
    @Parameters({"deviceName", "platform", "udid", "mobilePort", "serverPort"})
    public void createPageObjects(String deviceName, String platform, String udid, String mobilePort, String serverPort) {
        mainLandingScreen = new MainLandingScreen(getDriver(deviceName, platform, udid, mobilePort, serverPort));
        mallSelectionScreen = new MallSelectionScreen(getDriver(deviceName, platform, udid, mobilePort, serverPort));
    }

    @BeforeClass(description = "Переход на целевой экран",
            alwaysRun = true)
    public void goToTargetScreen() {
        mainLandingScreen.clickChangeMall();
    }

    @Test(description = "Открыт экран выбора МЕГИ",
            groups = {"functional", "check-in"})
    @Description("Экран выбора МЕГИ открыт в случае, если присутствует лейбл \"Выбери свою МЕГУ\"")
    @Severity(SeverityLevel.BLOCKER)
    public void isChooseMallLabelPresent() {
        Assert.assertTrue(mallSelectionScreen.isChooseMallLabelPresent());
    }

    @AfterClass(description = "Возврат на главный экран", alwaysRun = true)
    public void goToStartScreen() {
        mallSelectionScreen.clickBelayaDachaButton();
    }
}
