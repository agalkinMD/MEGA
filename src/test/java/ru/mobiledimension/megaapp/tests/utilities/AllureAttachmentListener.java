package ru.mobiledimension.megaapp.tests.utilities;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;

public class AllureAttachmentListener extends BaseTest implements ITestListener, ISuiteListener {
    public void onStart(ISuite suite) {
        System.out.println("After executing Suite:" + suite.getName());
    }

    public void onStart(ITestContext context) {
        System.out.println("Begin executing Test:" + context.getName());
    }

    public void onFinish(ITestContext context) {
        System.out.println("Completed executing test:" + context.getName());
    }

    public void onFinish(ISuite suite) {
        System.out.println("After executing Suite:" + suite.getName());
    }

    public void onTestStart(ITestResult result) {
        System.out.println("Test Status:" + result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Completed executing test:" + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("Test Status::" + result.getName());

        if (driver instanceof AppiumDriver) {
            System.out.println("Screenshot has been captured for test-case: ");
            saveScreenshotPNG(driver);
        }
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Status::" + result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Attachment(type = "image/png")
    public File saveScreenshotPNG(WebDriver driver) {
        //return ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    }
}
