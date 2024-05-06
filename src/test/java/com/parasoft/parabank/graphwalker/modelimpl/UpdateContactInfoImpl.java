package com.parasoft.parabank.graphwalker.modelimpl;

import com.parasoft.parabank.graphwalker.Base.TestExecutionContext;
import com.parasoft.parabank.graphwalker.utils.Coverage;
import com.parasoft.parabank.graphwalker.utils.Driver;
import com.parasoft.parabank.graphwalker.utils.Urls;
import org.graphwalker.UpdateContactInfo;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@GraphWalker(value = Coverage.Default)
public class UpdateContactInfoImpl extends TestExecutionContext implements UpdateContactInfo {

    private boolean isEmpty(WebElement element) {
        return element.getAttribute("value").isEmpty();
    }

    private void clearFields() {
        Driver.clearField(By.xpath("//*[@id='customer.firstName']"));
        Driver.clearField(By.xpath("//*[@id='customer.lastName']"));
        Driver.clearField(By.xpath("//*[@id='customer.address.street']"));
        Driver.clearField(By.xpath("//*[@id='customer.address.city']"));
        Driver.clearField(By.xpath("//*[@id='customer.address.state']"));
        Driver.clearField(By.xpath("//*[@id='customer.address.zipCode']"));
        Driver.clearField(By.xpath("//*[@id='customer.phoneNumber']"));
    }

    private void fillRequiredFields() {
        // Clear fields
        clearFields();

        // Fill in required fields
        Driver.findElement(By.xpath("//*[@id='customer.firstName']")).sendKeys("John");
        Driver.findElement(By.xpath("//*[@id='customer.lastName']")).sendKeys("Doe");
        Driver.findElement(By.xpath("//*[@id='customer.address.street']")).sendKeys("123 Some Street");
        Driver.findElement(By.xpath("//*[@id='customer.address.city']")).sendKeys("Some City");
        Driver.findElement(By.xpath("//*[@id='customer.address.state']")).sendKeys("Some State");
        Driver.findElement(By.xpath("//*[@id='customer.address.zipCode']")).sendKeys("12345");
        Driver.findElement(By.xpath("//*[@id='customer.phoneNumber']")).sendKeys("1234567890");
    }

    @Override
    public void e_Required_Fields_Filled() {
        fillRequiredFields();
    }

    @Override
    public void e_Navigate() {
        // Handled by outgoing edges in other models
    }

    @Override
    public void v_Dummy_Navigation_SHARED() {
        // Dummy navigation, nothing to do
    }

    @Override
    public void e_Update_Profile() {
        Driver.clickElement(By.xpath("//input[@type='submit']"));
    }

    @Override
    public void v_Missing_Required_Fields() {
        Assert.assertTrue(Driver.containsUrl(Urls.UPDATE_INFO_URL));
        Assert.assertTrue(Driver.containsText("is required"));
    }

    @Override
    public void e_No_Action() {
        // No action
    }

    @Override
    public void e_Missing_Required_Fields() {
        Driver.clearField(By.xpath("//*[@id='customer.firstName']"));
    }

    @Override
    public void v_Profile_Updated() {
        Assert.assertTrue(Driver.containsUrl(Urls.UPDATE_INFO_URL));
        Assert.assertTrue(Driver.containsText("Profile Updated"));
    }

    @Override
    public void v_Update_Contact_Info_SHARED() {
//        if (getLastElement() == null) {
//            // Assume start of an isolated test, log in and navigate
//            Helpers.ensureLoggedIn();
//            Driver.navigateTo(Urls.UPDATE_INFO_URL);
//        }

        Assert.assertTrue(Driver.containsUrl(Urls.UPDATE_INFO_URL));
    }

    @Override
    public void e_Change_Valid_Contact_Info() {
        fillRequiredFields();
        Driver.clearField(By.xpath("//*[@id='customer.firstName']"));
        Driver.fillField(By.xpath("//*[@id='customer.firstName']"), "Jane");
    }

    @Override
    public void v_Valid_Contact_Info() {
        Assert.assertTrue(Driver.containsUrl(Urls.UPDATE_INFO_URL));
        Assert.assertFalse(isEmpty(Driver.findElement(By.xpath("//*[@id='customer.firstName']"))));
        Assert.assertFalse(isEmpty(Driver.findElement(By.xpath("//*[@id='customer.lastName']"))));
        Assert.assertFalse(isEmpty(Driver.findElement(By.xpath("//*[@id='customer.address.street']"))));
        Assert.assertFalse(isEmpty(Driver.findElement(By.xpath("//*[@id='customer.address.city']"))));
        Assert.assertFalse(isEmpty(Driver.findElement(By.xpath("//*[@id='customer.address.state']"))));
        Assert.assertFalse(isEmpty(Driver.findElement(By.xpath("//*[@id='customer.address.zipCode']"))));
    }

    @Override
    public void v_Valid_Changed_Contact_Info() {
        Assert.assertTrue(Driver.containsUrl(Urls.UPDATE_INFO_URL));
        Assert.assertEquals("Jane", Driver.findElement(By.xpath("//*[@id='customer.firstName']")).getAttribute("value"));
    }

    @Override
    public void e_Click_Update_Contact_Info() {
        Driver.navigateTo(Urls.UPDATE_INFO_URL);
    }
}
