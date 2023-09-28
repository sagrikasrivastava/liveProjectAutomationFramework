package org.sagsri.listeners;

import com.aventstack.extentreports.Status;
import org.sagsri.base.TestBase;
import org.sagsri.utilities.ExtentManager;
import org.sagsri.utilities.TestUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;

public class CustomListeners extends TestBase implements ITestListener {

    public synchronized void onTestStart(ITestContext arg0) {
        ExtentManager.getReportInstance();
        ExtentManager.createTest(arg0.getHost(),arg0.getName());
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

        Reporter.log("click to see Screenshot");
        Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a");
        Reporter.log("<br>");
        Reporter.log("<br>");
        Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a");


    }

    public void onTestSkipped(ITestResult arg0) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
        ExtentManager.flushReport();
    }

}
