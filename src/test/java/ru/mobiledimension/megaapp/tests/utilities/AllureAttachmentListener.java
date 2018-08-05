package ru.mobiledimension.megaapp.tests.utilities;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;

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
        System.out.println("Screenshot has been captured for test-case: ");

        saveScreenshotPNG(getDriver2());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Status::" + result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Attachment(value = "{0}", type = "image/png")
    public byte[] saveScreenshotPNG(AppiumDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);

    }

    public void saveAttachement(byte[] byteRepresentation) {
        File file = new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/allure-report/TemporaryAttachmentFolder");

        if (driver.getPlatformName().equals("android"))
            file.renameTo(new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/allure-report/TemporaryAttachmentFolder/android"));
        else file.renameTo(new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/allure-report/TemporaryAttachmentFolder/ios"));

        try {
            FileUtils.writeByteArrayToFile(file, byteRepresentation);
        }
        catch (IOException e) { }
    }
}
