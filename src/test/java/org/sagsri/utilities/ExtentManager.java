package org.sagsri.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.sagsri.base.TestBase;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ExtentManager extends TestBase {
    public static ExtentReports extentReport;
    public static String extentReportPrefix;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();


    public static ExtentReports getReportInstance(){
        if (extentReport ==null){
            setupExtentReport("Live Project Automation Framework");
        }
        return extentReport;
    }

    public static ExtentReports setupExtentReport(String testName){
        extentReport=new ExtentReports();
        ExtentSparkReporter spark=new ExtentSparkReporter(System.getProperty("user.dir")+"/report/"+extentReportsprefix_Name(testName)+".html");
        extentReport.attachReporter((spark));

        extentReport.setSystemInfo("Tester","Sagrika Srivastava");
        spark.config().setReportName("Regression Tet");
        spark.config().setDocumentTitle("TestResults");
        spark.config().setTheme(Theme.DARK);

        return extentReport;
    }

    public static String extentReportsprefix_Name(String testName){
        String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        extentReportPrefix=testName+"_"+date;
        return extentReportPrefix;
    }

    public static void flushReport(){

        extentReport.flush();
    }

    public synchronized static ExtentTest getTest(){
        return extentTest.get();
    }

    public synchronized static ExtentTest createTest(String name, String description){
        ExtentTest test=extentReport.createTest(name,description);
        extentTest.set(test);
        return getTest();
    }
    public synchronized static void log(String message){
        getTest().info(message);
    }
    public synchronized static void pass(String message){
        getTest().pass(message);
    }
    public synchronized static void fail(String message){
        getTest().fail(message);
    }
    public synchronized static void attachImage(){
        getTest().addScreenCaptureFromPath(TestUtil.screenshotName);
        //System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\screenshot\\"+TestUtil.screenshotName
    }
}
