package com.parasoft.parabank.graphwalker.modelimpl;

import com.parasoft.parabank.graphwalker.Base.TestExecutionContext;
import com.parasoft.parabank.graphwalker.utils.Coverage;
import com.parasoft.parabank.graphwalker.utils.Driver;
import com.parasoft.parabank.graphwalker.utils.Urls;
import org.graphwalker.AccountOverview;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;

@GraphWalker(value = Coverage.Default)
public class AccountOverviewImpl extends TestExecutionContext implements AccountOverview {
    @Override
    public void e_Select_Account() {
        Driver.clickElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/table/tbody/tr[1]/td[1]/a"));
    }

    @Override
    public void v_Account_Activity_SHARED() {
        Assert.assertTrue(Driver.containsUrl(Urls.ACCOUNTS_ACTIVITY_URL));
    }

    @Override
    public void v_Account_Overview_SHARED() {
//        if (getLastElement() == null) {
//            // Assume start of an isolated test, log in and navigate
//            Helpers.ensureLoggedIn();
//            Driver.navigateTo(Urls.ACCOUNTS_OVERVIEW_URL);
//        }

        Assert.assertTrue(Driver.containsUrl(Urls.ACCOUNTS_OVERVIEW_URL));
    }

//    @Override
//    public void e_Navigate() {
//        // Handled by outgoing edges in other models
//    }

    @Override
    public void v_Dummy_Navigation_SHARED() {
        // Dummy navigation, nothing to do
    }

    @Override
    public void e_Click_Account_Overview() {
        Driver.navigateTo(Urls.ACCOUNTS_OVERVIEW_URL);
    }
}
