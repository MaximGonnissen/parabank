package com.parasoft.parabank.graphwalker.modelimpl;

import com.parasoft.parabank.graphwalker.Base.TestExecutionContext;
import com.parasoft.parabank.graphwalker.utils.Coverage;
import com.parasoft.parabank.graphwalker.utils.Driver;
import com.parasoft.parabank.graphwalker.utils.Helpers;
import com.parasoft.parabank.graphwalker.utils.Urls;
import org.graphwalker.AccountActivity;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@GraphWalker(value = Coverage.Default)
public class AccountActivityImpl extends TestExecutionContext implements AccountActivity {

    @Override
    public void e_Navigate() {
        Driver.navigateTo(Urls.ACCOUNTS_OVERVIEW_URL);
    }


    @Override
    public void v_Account_Activity_SHARED() {
//        if (getLastElement() == null) {
//            // Assume start of an isolated test, log in and navigate to account activity
//            Helpers.ensureLoggedIn();
//            Driver.navigateTo(Urls.ACCOUNTS_OVERVIEW_URL);
//            Driver.clickElement(By.cssSelector("html > body > div:nth-of-type(1) > div:nth-of-type(3) > div:nth-of-type(2) > div > div > table > tbody > tr:nth-of-type(1) > td:nth-of-type(1) > a"));
//            Driver.waitForElement(By.xpath("//h1[contains(text(),'Account Activity')]"));
//        }

        // At account activity page
        Assert.assertTrue(Driver.containsUrl(Urls.ACCOUNTS_ACTIVITY_URL));
    }

    @Override
    public void v_Transaction_Details() {
        // At transaction details page
        // TODO: Can be in invalid state if no transactions are present --> Model incomplete?
        boolean atTransactionDetails = Driver.containsUrl(Urls.TRANSACTION_DETAILS_URL);
        boolean atAccountActivity = Driver.containsUrl(Urls.ACCOUNTS_ACTIVITY_URL); // TODO: Workaround in case no transactions are present
        Assert.assertTrue(atTransactionDetails || atAccountActivity);
    }

    @Override
    public void v_Dummy_Navigation_SHARED() {
        // Dummy navigation, nothing to do
        Assert.assertFalse(Driver.containsUrl(Urls.INDEX_URL));
    }

    @Override
    public void e_No_Action() {
        // No action
    }

    @Override
    public void e_Set_Activity_Period_Filter() {
        WebElement dropDown = Driver.findElement(By.xpath("//*[@id='month']"));
        List<WebElement> options = dropDown.findElements(By.tagName("option"));
        options.get(Helpers.random.nextInt(options.size())).click();
    }

    @Override
    public void e_Press_Go() {
        Driver.clickElement(By.xpath("//input[@value='Go']"));
    }

    @Override
    public void v_Type_Filter_Set() {
        Driver.containsUrl(Urls.ACCOUNTS_ACTIVITY_URL);
        // TODO: Check for type filter?
    }

    @Override
    public void v_Activity_Period_Filter_Set() {
        Assert.assertTrue(Driver.containsUrl(Urls.ACCOUNTS_ACTIVITY_URL));
        // TODO: Check for activity period filter?
    }

    @Override
    public void v_Transactions_Filtered() {
        Assert.assertTrue(Driver.containsUrl(Urls.ACCOUNTS_ACTIVITY_URL));
        // TODO: Check for filtered transactions?
    }

    @Override
    public void e_Set_Type_Filter() {
        WebElement dropDown = Driver.findElement(By.xpath("//*[@id='transactionType']"));
        List<WebElement> options = dropDown.findElements(By.tagName("option"));
        options.get(Helpers.random.nextInt(options.size())).click();
    }

    @Override
    public void e_Click_On_Transaction() {
        Driver.waitFor(100);
        List<WebElement> transactions = Driver.findElements(By.xpath("//a[contains(text(),'Funds Transfer Sent')]"));
        if (!transactions.isEmpty()) {
            transactions.get(Helpers.random.nextInt(transactions.size())).click();
        }
    }
}
