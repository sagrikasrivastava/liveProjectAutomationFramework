package org.sagsri.testcases;

import org.openqa.selenium.By;
import org.sagsri.base.TestBase;
import org.sagsri.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class BankManagerLoginTest extends TestBase {

    @Test
    public void loginAsBankManager() {
        ExtentManager.log("Starting loginAsBankManager...");

        log.debug("Inside Login Test");
        driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
        ExtentManager.pass("login successfully completed...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(elementIsPresent(By.cssSelector(OR.getProperty("addCustBtn"))), "Login Not Successful");

        ExtentManager.pass("Bank Manager Page Assertion passed...");
        log.debug("Login successfully executed");
        Reporter.log("Login test successfully executed");
    }
}
