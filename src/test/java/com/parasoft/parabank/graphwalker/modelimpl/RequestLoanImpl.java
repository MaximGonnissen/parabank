package com.parasoft.parabank.graphwalker.modelimpl;


import com.parasoft.parabank.graphwalker.Base.TestExecutionContext;
import com.parasoft.parabank.graphwalker.utils.Coverage;
import com.parasoft.parabank.graphwalker.utils.Driver;
import com.parasoft.parabank.graphwalker.utils.Urls;
import org.graphwalker.RequestLoan;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;


@GraphWalker(value = Coverage.Default)
public class RequestLoanImpl extends TestExecutionContext implements RequestLoan {

    private static final By LOAN_AMOUNT = By.xpath("//*[@id=\"amount\"]");
    private static final By DOWN_PAYMENT = By.xpath("//*[@id=\"downPayment\"]");
    private static final By FROM_ACCOUNT = By.xpath("//*[@id=\"fromAccountId\"]");
    private static final By APPLY = By.xpath("//*[@id=\"rightPanel\"]/div/div/form/table/tbody/tr[4]/td[2]/input");

    private void clearInformation() {
        Driver.clearField(LOAN_AMOUNT);
        Driver.clearField(DOWN_PAYMENT);
    }

    private void fillInformationValid() {
        clearInformation();
        Driver.setField(LOAN_AMOUNT, "1");
        Driver.setField(DOWN_PAYMENT, "1");
    }

    @Override
    public void v_Information_Filled_Valid() {
        Assert.assertTrue(Driver.containsUrl(Urls.REQUEST_LOAN_URL));
        Assert.assertEquals("1", Driver.findElement(LOAN_AMOUNT).getAttribute("value"));
        Assert.assertEquals("1", Driver.findElement(DOWN_PAYMENT).getAttribute("value"));
    }

    @Override
    public void e_Navigate() {
        // Handled by outgoing edges in other models
    }

    @Override
    public void v_Error() {
        Assert.assertTrue(Driver.containsUrl(Urls.REQUEST_LOAN_URL));
        Assert.assertTrue(Driver.containsText("An internal error has occurred and has been logged."));
    }

    @Override
    public void v_Account_Activity_SHARED() {
        Assert.assertTrue(Driver.containsUrl(Urls.ACCOUNTS_ACTIVITY_URL));
    }

    @Override
    public void v_Dummy_Navigation_SHARED() {
        // Dummy navigation, nothing to do
    }

    @Override
    public void v_Information_Filled_Invalid() {
        Assert.assertTrue(Driver.containsUrl(Urls.REQUEST_LOAN_URL));
        Assert.assertEquals("a", Driver.findElement(LOAN_AMOUNT).getAttribute("value"));
        Assert.assertEquals("a", Driver.findElement(DOWN_PAYMENT).getAttribute("value"));
    }

    @Override
    public void e_No_Action() {
        // No action
    }

    @Override
    public void v_Request_Loan_SHARED() {
        Assert.assertTrue(Driver.containsUrl(Urls.REQUEST_LOAN_URL));
    }

    @Override
    public void e_Press_Apply() {
        Driver.clickElement(APPLY);
    }

    @Override
    public void v_Loan_Processed() {
        Assert.assertTrue(Driver.containsUrl(Urls.REQUEST_LOAN_URL));
        Assert.assertTrue(Driver.containsText("Congratulations, your loan has been approved."));
    }

    @Override
    public void e_Click_Request_Loan() {
        Driver.navigateTo(Urls.REQUEST_LOAN_URL);
    }

    @Override
    public void v_Insufficient_Funds() {
        Assert.assertTrue(Driver.containsUrl(Urls.REQUEST_LOAN_URL));
        Assert.assertEquals("1000000", Driver.findElement(DOWN_PAYMENT).getAttribute("value"));
    }

    @Override
    public void v_Loan_Too_High() {
        Assert.assertTrue(Driver.containsUrl(Urls.REQUEST_LOAN_URL));
        Assert.assertEquals("1000000", Driver.findElement(LOAN_AMOUNT).getAttribute("value"));
    }

    @Override
    public void e_Insufficient_Funds_Apply_Now() {
        e_Press_Apply();
    }

    @Override
    public void e_Loan_Too_High() {
        fillInformationValid();
        Driver.setField(LOAN_AMOUNT, "1000000");
    }

    @Override
    public void e_Invalid_Down_Payment_Missing() {
        fillInformationValid();
        Driver.clearField(DOWN_PAYMENT);
    }

    @Override
    public void v_Loan_Too_High_Error() {
        Assert.assertTrue(Driver.containsUrl(Urls.REQUEST_LOAN_URL));
        Assert.assertTrue(Driver.containsText("We cannot grant a loan in that amount with your available funds."));
    }

    @Override
    public void e_Goto_New_Account() {
        Driver.clickElement(By.xpath("//*[@id=\"newAccountId\"]"));
    }

    @Override
    public void e_Insufficient_Funds() {
        fillInformationValid();
        Driver.setField(DOWN_PAYMENT, "1000000");
    }

    @Override
    public void e_Invalid_Loan_Amount_Missing() {
        fillInformationValid();
        Driver.clearField(LOAN_AMOUNT);
    }

    @Override
    public void v_Insufficient_Funds_Error() {
        Assert.assertTrue(Driver.containsUrl(Urls.REQUEST_LOAN_URL));
        Assert.assertTrue(Driver.containsText("You do not have sufficient funds for the given down payment."));
    }

    @Override
    public void e_Invalid_Apply_Now() {
        e_Press_Apply();
    }

    @Override
    public void v_Loan_Amount_Missing() {
        Assert.assertTrue(Driver.containsUrl(Urls.REQUEST_LOAN_URL));
        Assert.assertEquals("", Driver.findElement(LOAN_AMOUNT).getAttribute("value"));
    }

    @Override
    public void e_Valid_Information_Filled() {
        fillInformationValid();
    }

    @Override
    public void v_Down_Payment_Missing() {
        Assert.assertTrue(Driver.containsUrl(Urls.REQUEST_LOAN_URL));
        Assert.assertEquals("", Driver.findElement(DOWN_PAYMENT).getAttribute("value"));
    }

    @Override
    public void e_Loan_Too_High_Apply_Now() {
        e_Press_Apply();
    }

    @Override
    public void e_Invalid_Information() {
        Driver.setField(LOAN_AMOUNT, "a");
        Driver.setField(DOWN_PAYMENT, "a");
    }
}
