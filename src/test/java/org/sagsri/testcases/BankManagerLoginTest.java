package org.sagsri.testcases;

import org.openqa.selenium.By;
import org.sagsri.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BankManagerLoginTest extends TestBase {

    @Test
    public void loginAsBankManager() throws InterruptedException {
        log.debug("Inside Login Test");
        driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(elementIsPresent(By.cssSelector(OR.getProperty("addCustBtn"))), "Login Not Successful");
        log.debug("Login successfully executed");
    }
}
