package org.sagsri.base;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sagsri.utilities.ExcelReader;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    /*
    WebDriver - done
    Properties - done
    Logs - log4j jar file, after this we need two log files Application.log and Selenium.log, log4j.properties
    ExtentReports
    DB
    Excel
    Mail
    ReportNG , Extent Reports
    Jenkins
     */

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static Logger log  = Logger.getLogger("devpinoyLogger");
    public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testData.xlsx");

    //ChromeDriver driver =new ChromeDriver();

    @BeforeSuite
    public void setUp() {
        System.setProperty("log4j.configurationFile","C:\\Users\\utkarsh\\Desktop\\Java_Automation_Workspace\\liveProjectAutomationFramework\\src\\test\\resources\\log4j.properties");
        try {
            fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        try {
            config.load(fis);
            log.debug("Config File Loaded !!");
        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        try {
            OR.load(fis);
            log.debug("OR File Loaded!!!");
        }catch (IOException e){
            e.printStackTrace();
        }
        if (config.getProperty("browser").equals("firefox")){
            //System.setProperty("webdriver.gecko.driver","gecko.exe");
            driver = new FirefoxDriver();
        }else if (config.getProperty("browser").equals("chrome")){
            //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chrome-win64\\chrome.exe");
            driver=new ChromeDriver();
            log.debug("Chrome Launched !!");
        }
        driver.get(config.getProperty("testsiteurl"));
        log.debug("Navigated to : "+config.getProperty("testsiteurl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
    }

    public boolean elementIsPresent(By by){
        try{
            driver.findElement(by);
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
    @AfterSuite
    public void tearDown(){
        if (driver!=null) {
            driver.quit();
        }
        log.debug("Test execution completed!!!");
    }
}
