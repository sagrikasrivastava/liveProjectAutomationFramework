package org.sagsri.testcases;

import org.openqa.selenium.By;
import org.sagsri.base.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest extends TestBase {

    @Test(dataProvider = "getData")
    public void addCustomer(String firstName, String lastName, String postCode){

        driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
        driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstName);
        driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(lastName);
        driver.findElement(By.cssSelector(OR.getProperty("postcode"))).sendKeys(postCode);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.cssSelector(OR.getProperty("addBtn"))).click();

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
