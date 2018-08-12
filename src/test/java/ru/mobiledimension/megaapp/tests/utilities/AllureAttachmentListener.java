package ru.mobiledimension.megaapp.tests.utilities;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public abstract class AllureAttachmentListener extends BaseTest implements ISuiteListener, IInvokedMethodListener {

    public void onStart(ISuite suite) {
        System.out.println("Before executing suite: " + suite.getName());
    }

    public void beforeInvocation(IInvokedMethod invokedMethod, ITestResult result) { }

    public void afterInvocation(IInvokedMethod invokedMethod, ITestResult result) {
        if (invokedMethod.getTestResult().getStatus() == 2) {
            Object currentClass = result.getInstance();

            AppiumDriver driver = ((BaseTest) currentClass).getDriver();

            if (driver != null) {
                saveAttachement(makeScreenshot(driver), driver.getPlatformName());
            }
        }
    }

    public void onFinish(ISuite suite) {
        System.out.println("After executing Suite:" + suite.getName());
        deleteAllureHistoryTrend();
        addBuildEnvironmentVariables();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] makeScreenshot(AppiumDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private void saveAttachement(byte[] byteRepresentation, String platformName) {
        File file = new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/allure-report/data/attachments/" + platformName
                + "_" + new Timestamp(System.currentTimeMillis()) + ".png");

        try {
            FileUtils.writeByteArrayToFile(file, byteRepresentation);
        }
        catch (IOException e) { }
    }

    private void deleteAllureHistoryTrend() {
        File trendReport = new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/allure-report/history/history-trend.json");
        File historyReport = new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/allure-report/history/history.json");
        File trendResults = new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/allure-results/history/history-trend.json");
        File historyResults = new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/allure-results/history/history.json");
        if (trendReport.exists())
            trendReport.delete();
        if (historyReport.exists())
            historyReport.delete();
        if (trendResults.delete())
            trendResults.delete();
        if (historyResults.exists())
            historyResults.delete();
    }

    private void addBuildEnvironmentVariables() {
        File environment = new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/src/test/resources/environment.properties");
        File allureResultsDir = new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f/allure-results");
        if (allureResultsDir.exists() && allureResultsDir.isDirectory())
            environment.renameTo(new File("/Users/anton/Development/TeamCity/buildAgent/work/fc4047a659d7949f"));
    }
}
