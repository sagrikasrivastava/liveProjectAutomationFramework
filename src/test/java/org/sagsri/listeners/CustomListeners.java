package org.sagsri.listeners;

import com.aventstack.extentreports.Status;
import org.sagsri.base.TestBase;
import org.sagsri.utilities.ExtentManager;
import org.sagsri.utilities.TestUtil;
import org.testng.*;

import java.io.IOException;

public class CustomListeners extends TestBase implements ITestListener {

    public synchronized void onTestStart(ITestContext arg0) {
        ExtentManager.getReportInstance();
        ExtentManager.createTest(arg0.getHost(),arg0.getName());

        if (TestUtil.isTestRunnable(arg0.getName(),excel)){
            throw new SkipException("Skipping the test "+arg0.getName().toUpperCase()+" as the Run mode is No");
        }
    }

    public void onTestSuccess(ITestResult arg0) {

    }

    public synchronized void onTestFailure(ITestResult arg0) {
        ExtentManager.getTest().fail(arg0.getThrowable());
        System.out.println("Test Failed"+arg0.getName());
        try {
            TestUtil.captureScreenshot();
            ExtentManager.attachImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.setProperty("org.uncommons.reportng.escape-output","false");

        Reporter.log("click to see Screenshot");
        Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a");
        Reporter.log("<br>");
        Reporter.log("<br>");
        Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a");


    }

    public void onTestSkipped(ITestResult arg0) {
        test.log(Status.SKIP, arg0.getName().toUpperCase()+ " - Skipped test as ber run modes given");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
        ExtentManager.getReportInstance();
        ExtentManager.createTest("Test",context.getName());
    }

    public void onFinish(ITestContext context) {
        ExtentManager.flushReport();
    }

}
