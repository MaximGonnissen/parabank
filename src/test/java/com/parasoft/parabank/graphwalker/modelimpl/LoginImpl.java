package com.parasoft.parabank.graphwalker.modelimpl;

import com.parasoft.parabank.graphwalker.Base.TestExecutionContext;
import com.parasoft.parabank.graphwalker.utils.Coverage;
import com.parasoft.parabank.graphwalker.utils.Driver;
import com.parasoft.parabank.graphwalker.utils.Urls;
import org.graphwalker.Login;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;

@GraphWalker(value = Coverage.DirectedChinesePostmanCoverage, start = "v_Start")
public class LoginImpl extends TestExecutionContext implements Login {

    private boolean atLoginPage() {
        return Driver.containsUrl(Urls.INDEX_URL) || Driver.containsUrl(Urls.LOGIN_URL) || Driver.containsUrl(Urls.REGISTER_URL) || Driver.containsUrl(Urls.FORGOT_LOGIN_URL);
    }

    @Override
    public void v_Verify_Invalid_Username() {
        // At login page, check popup for invalid username
        Assert.assertTrue(atLoginPage());
        String expected = "The username and password could not be verified.";
        Assert.assertTrue(Driver.containsText(expected));
    }

//    @Override
//    public void e_Navigate() {
//        // Handled by outgoing edges in other models
//    }

    @Override
    public void e_Valid_Login() {
        Driver.setField(By.name("username"), "Test");
        Driver.setField(By.name("password"), "Test");
        Driver.clickElement(By.xpath("//input[@value='Log In']"));
    }

    @Override
    public void v_Register_SHARED() {
        Assert.assertTrue(Driver.containsUrl(Urls.REGISTER_URL));
    }

    @Override
    public void e_Invalid_Login_Username() {
        Driver.setField(By.name("username"), "Invalid");
        Driver.setField(By.name("password"), "Test");
        Driver.clickElement(By.xpath("//input[@value='Log In']"));
    }

    @Override
    public void v_Dummy_Navigation_SHARED() {
        // Dummy navigation, nothing to do
    }

    @Override
    public void e_Click_Forgot_Login() {
        Driver.navigateTo(Urls.FORGOT_LOGIN_URL);
    }

    @Override
    public void e_No_Action() {
        // No action
    }

    @Override
    public void e_Logout() {
        Driver.navigateTo(Urls.LOGOUT_URL);
    }

    @Override
    public void v_Account_Overview_SHARED() {
        Assert.assertTrue(Driver.containsUrl(Urls.ACCOUNTS_OVERVIEW_URL));
    }

    @Override
    public void e_goto_login() {
        // Login is technically the index page
        Driver.getDriver().get(Urls.INDEX_URL);
    }

    @Override
    public void e_Click_Register() {
        Driver.navigateTo(Urls.REGISTER_URL);
    }

    @Override
    public void v_Login_Page() {
        // Check if we're on a page with the login form
        Assert.assertTrue(atLoginPage());
    }

    @Override
    public void v_Start() {
        // Start, nothing to do
    }

    @Override
    public void v_Verify_Invalid_Password() {
        // At login page, check popup for invalid password
        Assert.assertTrue(Driver.containsUrl(Urls.LOGIN_URL));
        String expected = "The username and password could not be verified.";
        Assert.assertTrue(Driver.containsText(expected));
    }

    @Override
    public void v_Verify_Invalid_Login_Empty() {
        // At login page, check popup for invalid login
        Assert.assertTrue(Driver.containsUrl(Urls.LOGIN_URL));
        String expected = "Please enter a username and password.";
        Assert.assertTrue(Driver.containsText(expected));
    }

    @Override
    public void v_Forgot_Login() {
        Assert.assertTrue(Driver.containsUrl(Urls.FORGOT_LOGIN_URL));
    }

    @Override
    public void e_Invalid_Login_Empty() {
        Driver.clearField(By.name("username"));
        Driver.clearField(By.name("password"));
        Driver.clickElement(By.xpath("//input[@value='Log In']"));
    }

    @Override
    public void e_Invalid_Login_Password() {
        Driver.setField(By.name("username"), "Test");
        Driver.setField(By.name("password"), "Invalid");
        Driver.clickElement(By.xpath("//input[@value='Log In']"));
    }
}
