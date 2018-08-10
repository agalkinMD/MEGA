package ru.mobiledimension.megaapp.tests.utilities;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.File;
import java.io.IOException;

public class AllureAttachmentListener extends BaseTest implements ITestListener, ISuiteListener, IInvokedMethodListener {

    public void onStart(ISuite suite) {
        System.out.println("After executing Suite:" + suite.getName());
    }

    public void onStart(ITestContext context) {
        System.out.println("Begin executing Test:" + context.getName());
    }

    public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {

    }

    public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
        //saveScreenshotPNG(driver);
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
        Object currentClass = result.getInstance();

        AppiumDriver driver = ((BaseTest) currentClass).getDriver();

        if (driver != null) {
            saveScreenshotPNG(driver);
        }
        /*Class clazz = result.getTestClass().getRealClass();
        Field field = null;

        try {
            field = clazz.getDeclaredField("driver");
        }
        catch (NoSuchFieldException e) { }

        field.setAccessible(true);

        try {
            AppiumDriver driver = (AppiumDriver) field.get(result.getInstance());
        }
        catch (IllegalAccessException e) { }

        saveAttachement(saveScreenshotPNG(driver));*/
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
        File file = new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/allure-report/data/attachments");
        try {
            FileUtils.writeByteArrayToFile(file, byteRepresentation);
        }
        catch (IOException e) { }

        /*if (driver.getPlatformName().equals("ios"))
            file.renameTo(new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/allure-report/data/attachments/iOS.png"));
        else
            file.renameTo(new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/allure-report/data/attachments/Android.png"));*/
    }
}
