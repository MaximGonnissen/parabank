package com.parasoft.parabank.graphwalker.modelimpl;

import com.parasoft.parabank.graphwalker.Base.TestExecutionContext;
import com.parasoft.parabank.graphwalker.utils.Coverage;
import com.parasoft.parabank.graphwalker.utils.Driver;
import com.parasoft.parabank.graphwalker.utils.Helpers;
import com.parasoft.parabank.graphwalker.utils.Urls;
import org.graphwalker.Register;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;

@GraphWalker(value = Coverage.Default)
public class RegisterImpl extends TestExecutionContext implements Register {

    private void clickRegister() {
        Driver.clickElement(By.xpath("//input[@value='Register']"));
    }

    private void clearFields() {
        Driver.clearField(By.xpath("//*[@id='customer.firstName']"));
        Driver.clearField(By.xpath("//*[@id='customer.lastName']"));
        Driver.clearField(By.xpath("//*[@id='customer.address.street']"));
        Driver.clearField(By.xpath("//*[@id='customer.address.city']"));
        Driver.clearField(By.xpath("//*[@id='customer.address.state']"));
        Driver.clearField(By.xpath("//*[@id='customer.address.zipCode']"));
        Driver.clearField(By.xpath("//*[@id='customer.phoneNumber']"));
        Driver.clearField(By.xpath("//*[@id='customer.ssn']"));
        Driver.clearField(By.xpath("//*[@id='customer.username']"));
        Driver.clearField(By.xpath("//*[@id='customer.password']"));
        Driver.clearField(By.xpath("//*[@id='repeatedPassword']"));
    }

    private void ensureLoggedInDefaultAccount() {
        Driver.getDriver().get(Urls.LOGOUT_URL);
        Driver.setField(By.name("username"), "Test");
        Driver.setField(By.name("password"), "Test");
        Driver.clickElement(By.xpath("//input[@value='Log In']"));
    }

    @Override
    public void v_Registration_Complete() {
        Assert.assertTrue(Driver.containsUrl(Urls.REGISTER_URL));
        Assert.assertTrue(Driver.containsText("Your account was created successfully. You are now logged in."));
        Driver.waitFor(100);
        ensureLoggedInDefaultAccount();
    }

    @Override
    public void v_Password_Mismatch() {
        Assert.assertTrue(Driver.containsUrl(Urls.REGISTER_URL));
    }

    @Override
    public void e_Invalid_Password_Mismatch() {
        e_Fill_Information_Correct();
        Driver.setField(By.xpath("//*[@id='customer.password']"), "password");
        Driver.setField(By.xpath("//*[@id='repeatedPassword']"), "password2");
    }

    @Override
    public void e_Navigate() {
        // Handled by outgoing edges in other models
    }

    @Override
    public void v_Register_SHARED() {
        Assert.assertTrue(Driver.containsUrl(Urls.REGISTER_URL));
    }

    @Override
    public void v_Dummy_Navigation_SHARED() {
        // Dummy navigation, nothing to do
    }

    @Override
    public void e_No_Action() {
        // No action
    }

    @Override
    public void v_Missing_Password() {
        Assert.assertTrue(Driver.containsUrl(Urls.REGISTER_URL));
    }

    @Override
    public void v_Information_Filled() {
        Assert.assertTrue(Driver.containsUrl(Urls.REGISTER_URL));
    }

    @Override
    public void e_Invalid_Missing_Password() {
        e_Fill_Information_Correct();
        Driver.clearField(By.xpath("//*[@id='customer.password']"));
        Driver.clearField(By.xpath("//*[@id='repeatedPassword']"));
    }

    @Override
    public void v_Missing_Field() {
        Assert.assertTrue(Driver.containsUrl(Urls.REGISTER_URL));
    }

    @Override
    public void e_Invalid_Missing_Field() {
        e_Fill_Information_Correct();
        Driver.clearField(By.xpath("//*[@id='customer.firstName']"));
    }

    @Override
    public void e_Valid_Registration() {
        clickRegister();
    }

    @Override
    public void e_Invalid_Missing_Field_Attempt() {
        clickRegister();
    }

    @Override
    public void v_Missing_Field_Attempt() {
        Assert.assertTrue(Driver.containsUrl(Urls.REGISTER_URL));
        Assert.assertTrue(Driver.containsText("is required."));
    }

    @Override
    public void e_Invalid_Password_Mismatch_Attempt() {
        clickRegister();
    }

    @Override
    public void e_Invalid_Missing_Password_Attempt() {
        clickRegister();
    }

    @Override
    public void v_Password_Mismatch_Attempt() {
        Assert.assertTrue(Driver.containsUrl(Urls.REGISTER_URL));
        Assert.assertTrue(Driver.containsText("Passwords did not match."));
    }

    @Override
    public void e_Fill_Information_Correct() {
        clearFields();

        Driver.setField(By.xpath("//*[@id='customer.firstName']"), "John");
        Driver.setField(By.xpath("//*[@id='customer.lastName']"), "Doe");
        Driver.setField(By.xpath("//*[@id='customer.address.street']"), "123 Some Street");
        Driver.setField(By.xpath("//*[@id='customer.address.city']"), "Some City");
        Driver.setField(By.xpath("//*[@id='customer.address.state']"), "Some State");
        Driver.setField(By.xpath("//*[@id='customer.address.zipCode']"), "12345");
        Driver.setField(By.xpath("//*[@id='customer.phoneNumber']"), "1234567890");
        Driver.setField(By.xpath("//*[@id='customer.ssn']"), "123456789");
        Driver.setField(By.xpath("//*[@id='customer.username']"), "johndoe " + Helpers.unseededRandom.nextInt(100000));    // Randomised since username must be unique. Not ideal --> should use in-memory test database
        Driver.setField(By.xpath("//*[@id='customer.password']"), "password");
        Driver.setField(By.xpath("//*[@id='repeatedPassword']"), "password");
    }

    @Override
    public void v_Missing_Password_Attempt() {
        Assert.assertTrue(Driver.containsUrl(Urls.REGISTER_URL));
        Assert.assertTrue(Driver.containsText("Password is required"));
    }
}
