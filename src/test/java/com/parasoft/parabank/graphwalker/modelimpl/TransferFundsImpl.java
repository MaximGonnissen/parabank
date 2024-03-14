package com.parasoft.parabank.graphwalker.modelimpl;

import com.parasoft.parabank.graphwalker.Base.TestExecutionContext;
import com.parasoft.parabank.graphwalker.utils.Coverage;
import com.parasoft.parabank.graphwalker.utils.Driver;
import com.parasoft.parabank.graphwalker.utils.Helpers;
import com.parasoft.parabank.graphwalker.utils.Urls;
import org.graphwalker.TransferFunds;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

@GraphWalker(value = Coverage.RandomEdgeCoverage100, start = "v_Transfer_Funds_SHARED")
public class TransferFundsImpl extends TestExecutionContext implements TransferFunds {

    private Select getFromAccount() {
        return new Select(Driver.findElement(By.xpath("//*[@id='fromAccountId']")));
    }

    private Select getToAccount() {
        return new Select(Driver.findElement(By.xpath("//*[@id='toAccountId']")));
    }

    @Override
    public void v_Transfer_Complete() {
        Assert.assertTrue(Driver.containsUrl(Urls.TRANSFER_FUNDS_URL));
        Assert.assertTrue(Driver.containsText("Transfer Complete!"));
    }

    @Override
    public void e_Navigate() {
        Driver.navigateTo(Urls.ACCOUNTS_OVERVIEW_URL);
    }

    @Override
    public void v_Same_Accounts() {
        Assert.assertTrue(Driver.containsUrl(Urls.TRANSFER_FUNDS_URL));
        Assert.assertEquals(getFromAccount().getFirstSelectedOption().getText(), getToAccount().getFirstSelectedOption().getText());
    }

    @Override
    public void v_Dummy_Navigation_SHARED() {
        Assert.assertFalse(Driver.containsUrl(Urls.INDEX_URL));
    }

    @Override
    public void v_Invalid_Amount() {
        Assert.assertTrue(Driver.containsUrl(Urls.TRANSFER_FUNDS_URL));
        Assert.assertTrue(Driver.containsText("Please enter a valid amount."));
    }

    @Override
    public void e_No_Action() {
        // No action
    }

    @Override
    public void e_Select_Different_Accounts() {
        if (getFromAccount().getOptions() == getToAccount().getOptions()) {
            logger.warn("No different accounts available, selecting same account. This will cause a failure.");
        }

        getFromAccount().selectByIndex(Helpers.random.nextInt(getFromAccount().getOptions().size()));
        while (getToAccount().getFirstSelectedOption().getText().equals(getFromAccount().getFirstSelectedOption().getText())) {
            getToAccount().selectByIndex(Helpers.random.nextInt(getToAccount().getOptions().size()));
        }
    }

    @Override
    public void e_Fill_Amount() {
        e_Valid_Amount();
    }

    @Override
    public void v_Information_Filled() {
        Assert.assertTrue(Driver.containsUrl(Urls.TRANSFER_FUNDS_URL));
    }

    @Override
    public void v_Transfer_Funds_SHARED() {
        if (getLastElement() == null) {
            // Assume start of an isolated test, log in and navigate
            Helpers.ensureLoggedIn();
            Driver.navigateTo(Urls.TRANSFER_FUNDS_URL);
        }

        Assert.assertTrue(Driver.containsUrl(Urls.TRANSFER_FUNDS_URL));
    }

    @Override
    public void e_Valid_Amount() {
        Driver.clearField(By.id("amount"));
        Driver.fillField(By.id("amount"), "100");
    }

    @Override
    public void v_Different_Accounts() {
        Assert.assertTrue(Driver.containsUrl(Urls.TRANSFER_FUNDS_URL));
        Assert.assertNotEquals(getFromAccount().getFirstSelectedOption().getText(), getToAccount().getFirstSelectedOption().getText());
    }

    @Override
    public void e_Click_Transfer() {
        Driver.clickElement(By.xpath("//input[@value='Transfer']"));
        Driver.waitFor(100);
    }

    @Override
    public void e_Invalid_Amount() {
        Driver.clearField(By.id("amount"));
        Driver.fillField(By.id("amount"), "a");
        Driver.clickElement(By.xpath("//input[@value='Transfer']"));
        Driver.waitFor(100);
    }

    @Override
    public void e_Select_Same_Accounts() {
        int index = Helpers.random.nextInt(getFromAccount().getOptions().size());
        getFromAccount().selectByIndex(index);
        getToAccount().selectByIndex(index);
    }
}
