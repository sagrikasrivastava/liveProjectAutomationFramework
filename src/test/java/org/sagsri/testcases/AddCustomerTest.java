package org.sagsri.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sagsri.base.TestBase;
import org.sagsri.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest extends TestBase {

    @Test(dataProvider = "getData")
    public void addCustomer(String firstName, String lastName, String postCode, String alertText){
        ExtentManager.log("Starting AddCustomerTest...");

        driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
        driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstName);
        driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(lastName);
        driver.findElement(By.cssSelector(OR.getProperty("postcode"))).sendKeys(postCode);
        driver.findElement(By.cssSelector(OR.getProperty("addBtn"))).click();
        ExtentManager.pass("Customer added successfully...");
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            Assert.assertTrue(alert.getText().contains(alertText));
            alert.accept();
            ExtentManager.pass("Customer added and Assertion Passed...");
        }catch (AssertionError e){
            Assert.fail("Add customer Assertion Failed");
            ExtentManager.fail("Customer added actual message did not match the expected message...");
        }


    }

    @DataProvider
    public Object[][] getData(){

        String sheetName = "AddCustomerTest";
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        Object[][]data =new Object[rows-1][cols];

        for (int rowNum=2; rowNum<=rows; rowNum++){     //2
            for (int colNum=0; colNum<cols; colNum++){
                data[rowNum-2][colNum]=excel.getCellData(sheetName, colNum, rowNum);
            }
        }
        return data;
    }

}
