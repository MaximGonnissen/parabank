package com.parasoft.parabank.graphwalker.modelimpl;

import com.parasoft.parabank.graphwalker.Base.TestExecutionContext;
import com.parasoft.parabank.graphwalker.utils.Driver;
import com.parasoft.parabank.graphwalker.utils.Helpers;
import com.parasoft.parabank.graphwalker.utils.Urls;
import org.graphwalker.Navigation;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;

@GraphWalker(value = "random(edge_coverage(100))")
public class NavigationImpl extends TestExecutionContext implements Navigation {
    @Override
    public void e_Click_Find_Transactions() {
        Driver.navigateTo(Urls.FIND_TRANSACTIONS_URL);
    }

    @Override
    public void e_Navigate() {
        Driver.navigateTo(Urls.ACCOUNTS_OVERVIEW_URL);
    }

    @Override
    public void v_Dummy_Navigation_SHARED() {
//        if (getLastElement() == null) {
//            // Assume start of an isolated test, log in and navigate
//            Helpers.ensureLoggedIn();
//            Driver.navigateTo(Urls.ACCOUNTS_OVERVIEW_URL);
//        }

        Assert.assertTrue(Driver.containsUrl(Urls.ACCOUNTS_OVERVIEW_URL));
    }

    @Override
    public void e_Click_Request_Loan() {
        Driver.navigateTo(Urls.REQUEST_LOAN_URL);
    }

    @Override
    public void e_Click_Transfer_Funds() {
        Driver.navigateTo(Urls.TRANSFER_FUNDS_URL);
    }

    @Override
    public void e_Click_Open_New_Account() {
        Driver.navigateTo(Urls.OPEN_NEW_ACCOUNT_URL);
    }

    @Override
    public void v_Account_Overview_SHARED() {
        Assert.assertTrue(Driver.containsUrl(Urls.ACCOUNTS_OVERVIEW_URL));
    }

    @Override
    public void v_Bill_Pay_SHARED() {
        Assert.assertTrue(Driver.containsUrl(Urls.BILL_PAY_URL));
    }

    @Override
    public void v_Transfer_Funds_SHARED() {
        Assert.assertTrue(Driver.containsUrl(Urls.TRANSFER_FUNDS_URL));
    }

    @Override
    public void v_Request_Loan_SHARED() {
        Assert.assertTrue(Driver.containsUrl(Urls.REQUEST_LOAN_URL));
    }

    @Override
    public void v_Open_New_Account_SHARED() {
        Assert.assertTrue(Driver.containsUrl(Urls.OPEN_NEW_ACCOUNT_URL));
    }

    @Override
    public void e_Click_Bill_Pay() {
        Driver.navigateTo(Urls.BILL_PAY_URL);
    }

    @Override
    public void e_Click_Update_Contact_Info() {
        Driver.navigateTo(Urls.UPDATE_INFO_URL);
    }

    @Override
    public void e_Click_Account_Overview() {
        Driver.navigateTo(Urls.ACCOUNTS_OVERVIEW_URL);
    }

    @Override
    public void v_Find_Transactions_SHARED() {
        Assert.assertTrue(Driver.containsUrl(Urls.FIND_TRANSACTIONS_URL));
    }

    @Override
    public void v_Update_Contact_Info_SHARED() {
        Assert.assertTrue(Driver.containsUrl(Urls.UPDATE_INFO_URL));
    }
}
