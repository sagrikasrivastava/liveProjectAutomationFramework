package org.sagsri.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.sagsri.base.TestBase;
import org.sagsri.utilities.ExtentManager;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OpenAccountTest extends TestBase {



    @Test(dataProvider = "getData")
    public void openAccountTest(String customer, String currency){
        ExtentManager.log("Starting AddCustomerTest...");

        driver.findElement(By.cssSelector(OR.getProperty("openaccount_CSS"))).click();
       WebElement dropdownOne = driver.findElement(By.cssSelector(OR.getProperty("customer_CSS")));
        Select selectOne= new Select(dropdownOne);
        selectOne.selectByVisibleText(customer);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement dropdownTwo = driver.findElement(By.cssSelector(OR.getProperty("currency_CSS")));
        Select selectTwo= new Select(dropdownTwo);
        selectTwo.selectByVisibleText(currency);
        driver.findElement(By.cssSelector(OR.getProperty("process_CSS"))).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    @DataProvider
    public Object[][] getData(){

        String sheetName = "OpenAccountTest";
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


