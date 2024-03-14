package com.parasoft.parabank.graphwalker.modelimpl;


import com.parasoft.parabank.graphwalker.Base.TestExecutionContext;
import com.parasoft.parabank.graphwalker.utils.Coverage;
import org.graphwalker.RequestLoan;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;


@GraphWalker(value = Coverage.RandomEdgeCoverage100)
public class RequestLoanImpl extends TestExecutionContext implements RequestLoan {
    @Override
    public void v_Information_Filled_Valid() {

    }

    @Override
    public void e_Navigate() {

    }

    @Override
    public void v_Error() {

    }

    @Override
    public void v_Account_Activity_SHARED() {

    }

    @Override
    public void v_Dummy_Navigation_SHARED() {

    }

    @Override
    public void v_Information_Filled_Invalid() {

    }

    @Override
    public void e_No_Action() {

    }

    @Override
    public void e_Fill_Down_Payment() {

    }

    @Override
    public void e_Goto_Account_Activity() {

    }

    @Override
    public void v_Loan_Amount_Filled() {

    }

    @Override
    public void e_Invalid_Missing_Down_Payment() {

    }

    @Override
    public void e_Invalid_Missing_Loan_Amount_Invalid() {

    }

    @Override
    public void v_Request_Loan_SHARED() {

    }

    @Override
    public void e_Fill_Down_Payment_Invalid() {

    }

    @Override
    public void e_Invalid_Missing_Loan_Amount() {

    }

    @Override
    public void v_Down_Payment_Filled() {

    }

    @Override
    public void e_Press_Apply() {

    }

    @Override
    public void e_Fill_Loan_Amount() {

    }

    @Override
    public void v_Loan_Processed() {

    }
}
