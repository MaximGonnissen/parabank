package com.parasoft.parabank.graphwalker.modelimpl;


import com.parasoft.parabank.graphwalker.Base.TestExecutionContext;
import com.parasoft.parabank.graphwalker.utils.Coverage;
import com.parasoft.parabank.graphwalker.utils.Driver;
import com.parasoft.parabank.graphwalker.utils.Urls;
import org.graphwalker.RequestLoan;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;


@GraphWalker(value = Coverage.RandomEdgeCoverage100)
public class RequestLoanImpl extends TestExecutionContext implements RequestLoan {
    @Override
    public void v_Information_Filled_Valid() {

    }

    @Override
    public void e_Navigate() {
        Driver.navigateTo(Urls.ACCOUNTS_OVERVIEW_URL);
    }

    @Override
    public void v_Error() {

    }

    @Override
    public void v_Account_Activity_SHARED() {

    }

    @Override
    public void v_Dummy_Navigation_SHARED() {
        Assert.assertFalse(Driver.containsUrl(Urls.INDEX_URL));
    }

    @Override
    public void v_Information_Filled_Invalid() {

    }

    @Override
    public void e_No_Action() {
        // No action
    }

    @Override
    public void v_Request_Loan_SHARED() {

    }

    @Override
    public void e_Press_Apply() {

    }

    @Override
    public void v_Loan_Processed() {

    }

    @Override
    public void v_Insufficient_Funds() {

    }

    @Override
    public void v_Loan_Too_High() {

    }

    @Override
    public void e_Insufficient_Funds_Apply_Now() {

    }

    @Override
    public void e_Loan_Too_High() {

    }

    @Override
    public void e_Invalid_Down_Payment_Missing() {

    }

    @Override
    public void v_Loan_Too_High_Error() {

    }

    @Override
    public void e_Goto_New_Account() {

    }

    @Override
    public void e_Insufficient_Funds() {

    }

    @Override
    public void e_Invalid_Loan_Amount_Missing() {

    }

    @Override
    public void v_Insufficient_Funds_Error() {

    }

    @Override
    public void e_Invalid_Apply_Now() {

    }

    @Override
    public void v_Loan_Amount_Missing() {

    }

    @Override
    public void e_Valid_Information_Filled() {

    }

    @Override
    public void v_Down_Payment_Missing() {

    }

    @Override
    public void e_Loan_Too_High_Apply_Now() {

    }

    @Override
    public void e_Invalid_Information() {

    }

    @Override
    public void e_NewEdge() {

    }
}
