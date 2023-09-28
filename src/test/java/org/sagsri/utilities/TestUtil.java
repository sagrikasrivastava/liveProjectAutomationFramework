package org.sagsri.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.sagsri.base.TestBase;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TestUtil extends TestBase {

    public static String screenshotPath;
    public static String screenshotName;

    public static void captureScreenshot() throws IOException {

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Date d = new Date();
        screenshotName= d.toString().replace(":","_").replace(" ","_")+".jpg";
        FileUtils.copyFile(scrFile,new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\screenshot\\"+screenshotName));
    }

}
