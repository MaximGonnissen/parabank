package com.parasoft.parabank.graphwalker.modelimpl;

import com.parasoft.parabank.graphwalker.Base.TestExecutionContext;
import com.parasoft.parabank.graphwalker.utils.Coverage;
import com.parasoft.parabank.graphwalker.utils.Driver;
import com.parasoft.parabank.graphwalker.utils.Helpers;
import com.parasoft.parabank.graphwalker.utils.Urls;
import org.graphwalker.OpenNewAccount;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

@GraphWalker(value = Coverage.RandomEdgeCoverage100, start = "v_Open_New_Account_SHARED")
public class OpenNewAccountImpl extends TestExecutionContext implements OpenNewAccount {

    @Override
    public void v_Savings_Selected() {
        // At open new account page
        Assert.assertTrue(Driver.containsUrl(Urls.OPEN_NEW_ACCOUNT_URL));

        // Savings is the selected account type
        Select select = new Select(Driver.findElement(By.xpath("//*[@id='type']")));
        Assert.assertEquals("SAVINGS", select.getFirstSelectedOption().getText());
    }

    @Override
    public void e_Navigate() {
        Driver.navigateTo(Urls.ACCOUNTS_OVERVIEW_URL);
    }

    @Override
    public void v_Account_Activity_SHARED() {
        Assert.assertTrue(Driver.containsUrl(Urls.ACCOUNTS_ACTIVITY_URL));
    }

    @Override
    public void e_Select_Savings() {
        Select select = new Select(Driver.findElement(By.xpath("//*[@id='type']")));
        select.selectByVisibleText("SAVINGS");
    }

    @Override
    public void v_Dummy_Navigation_SHARED() {
        Assert.assertFalse(Driver.containsUrl(Urls.INDEX_URL));
    }

    @Override
    public void v_Checkings_Created() {
        Assert.assertTrue(Driver.containsUrl(Urls.OPEN_NEW_ACCOUNT_URL));
        Assert.assertTrue(Driver.containsText("Account Opened!"));
    }

    @Override
    public void e_No_Action() {
        // No action
    }

    @Override
    public void e_Goto_Account_Activity() {
        Driver.clickElement(By.xpath("//*[@id='newAccountId']"));
    }

    @Override
    public void v_Open_New_Account_SHARED() {
        if (getLastElement() == null) {
            // Assume start of an isolated test, log in and navigate
            Helpers.ensureLoggedIn();
            Driver.navigateTo(Urls.OPEN_NEW_ACCOUNT_URL);
        }

        // At open new account page
        Assert.assertTrue(Driver.containsUrl(Urls.OPEN_NEW_ACCOUNT_URL));
    }

    @Override
    public void e_Select_Checkings() {
        Select select = new Select(Driver.findElement(By.xpath("//*[@id='type']")));
        select.selectByVisibleText("CHECKING");
    }

    @Override
    public void v_Savings_Created() {
        Assert.assertTrue(Driver.containsUrl(Urls.OPEN_NEW_ACCOUNT_URL));
        Assert.assertTrue(Driver.containsText("Account Opened!"));
    }

    @Override
    public void v_Checkings_Selected() {
        // At open new account page
        Assert.assertTrue(Driver.containsUrl(Urls.OPEN_NEW_ACCOUNT_URL));

        // Checkings is the selected account type
        Select select = new Select(Driver.findElement(By.xpath("//*[@id='type']")));
        Assert.assertEquals("CHECKING", select.getFirstSelectedOption().getText());
    }

    @Override
    public void e_Valid_Open_New_account() {
        Driver.findElement(By.xpath("//input[@value='Open New Account']")).click();
        Driver.waitFor(100);
    }
}
