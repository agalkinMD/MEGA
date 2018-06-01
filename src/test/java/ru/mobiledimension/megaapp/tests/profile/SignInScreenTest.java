package ru.mobiledimension.megaapp.tests.profile;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.mobiledimension.megaapp.tests.BaseTest;
import ru.mobiledimension.megaapp.screens.profile.SignInScreen;
import ru.mobiledimension.megaapp.screens.tabs.BottomTab;
import ru.mobiledimension.megaapp.screens.tabs.ProfileTab;

public class SignInScreenTest extends BaseTest {

    BottomTab bottomTab = new BottomTab(getDriver());
    ProfileTab profileTab = new ProfileTab(getDriver());
    SignInScreen signInScreen = new SignInScreen(getDriver());

    @DataProvider(name = "AuthData")
    public Object[][] userData() {
        return new Object[][] {
                {"agalkin@mobiledimension.ru", "Qazxswdf12"},
                {"agalkin@mobiledimension.ru", "qazxswdf12"},
                {"agalkin@mobiledimension.ru", ""},
                {"agalkin", "Qazxswdf12"},
                {"", "Qazxswdf12"}
        };
    }

    @BeforeTest
    public void beforeTestExecution() {
        bottomTab.clickProfileTab();
        profileTab.clickProfileSection();
    }

    @BeforeMethod
    public void beforeMethodExecution() {
        signInScreen.clearUserData();
    }

    @Test(dataProvider = "userData")
    public void isEnabledLoginButton(String userName, String userPassword) {
        signInScreen.setUserLogin(userName);
        signInScreen.setUserPassword(userPassword);
        Assert.assertTrue(signInScreen.isEnabledSignInButton());
    }

    @AfterTest
    public void afterTestExecution() {
        bottomTab.clickProfileTab();
        bottomTab.clickMainTab();
    }
}
