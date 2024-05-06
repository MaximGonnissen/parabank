package com.parasoft.parabank.graphwalker.modelimpl;

import com.parasoft.parabank.graphwalker.Base.TestExecutionContext;
import com.parasoft.parabank.graphwalker.utils.Coverage;
import com.parasoft.parabank.graphwalker.utils.Driver;
import com.parasoft.parabank.graphwalker.utils.Urls;
import org.graphwalker.BillPay;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

@GraphWalker(value = Coverage.Default)
public class BillPayImpl extends TestExecutionContext implements BillPay {

    private static final By PAYEE_NAME = By.name("payee.name");
    private static final By PAYEE_ADDRESS_STREET = By.name("payee.address.street");
    private static final By PAYEE_ADDRESS_CITY = By.name("payee.address.city");
    private static final By PAYEE_ADDRESS_STATE = By.name("payee.address.state");
    private static final By PAYEE_ADDRESS_ZIP_CODE = By.name("payee.address.zipCode");
    private static final By PAYEE_PHONE_NUMBER = By.name("payee.phoneNumber");
    private static final By PAYEE_ACCOUNT_NUMBER = By.name("payee.accountNumber");
    private static final By VERIFY_ACCOUNT = By.name("verifyAccount");
    private static final By AMOUNT = By.name("amount");

    private void sendPayment() {
        Driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @Override
    public void v_Bill_Payment_Complete() {
        Assert.assertTrue(Driver.containsUrl(Urls.BILL_PAY_URL));
        Assert.assertTrue(Driver.containsText("Bill Payment Complete"));
    }


    @Override
    public void e_Navigate() {
        // Handled by outgoing edges in other models
    }

    @Override
    public void v_Error() {
        Assert.assertTrue(Driver.containsUrl(Urls.BILL_PAY_URL));
        Assert.assertTrue(Driver.containsText("An internal error has occurred and has been logged."));
    }

    @Override
    public void v_Dummy_Navigation_SHARED() {
        // Dummy navigation, nothing to do
    }

    @Override
    public void e_Invalid_Input() {
        e_Fill_Correctly();
        Driver.setField(PAYEE_ACCOUNT_NUMBER, "1a");
        Driver.setField(VERIFY_ACCOUNT, "1a");
    }

    @Override
    public void v_Invalid_Amount() {
        Assert.assertTrue(Driver.containsUrl(Urls.BILL_PAY_URL));
        Assert.assertTrue(Driver.containsText("Please enter a valid amount."));
    }

    @Override
    public void e_No_Action() {
        // No action
    }

    @Override
    public void v_Information_Filled() {
        Assert.assertTrue(Driver.containsUrl(Urls.BILL_PAY_URL));
    }

    @Override
    public void e_Invalid_Account_Filled() {
        e_Fill_Correctly();
        Driver.setField(PAYEE_ACCOUNT_NUMBER, "a");
        Driver.setField(VERIFY_ACCOUNT, "a");
    }

    @Override
    public void v_Invalid_Account() {
        Assert.assertTrue(Driver.containsUrl(Urls.BILL_PAY_URL));
    }

    @Override
    public void v_Bill_Pay_SHARED() {
        Assert.assertTrue(Driver.containsUrl(Urls.BILL_PAY_URL));
    }

    @Override
    public void e_Valid_Send_Payment() {
        sendPayment();
    }

    @Override
    public void v_Missing_Field() {
        Assert.assertTrue(Driver.containsUrl(Urls.BILL_PAY_URL));
    }

    @Override
    public void e_Invalid_Missing_Field() {
        Driver.clearField(PAYEE_NAME);
        Driver.clearField(PAYEE_ACCOUNT_NUMBER);
        Driver.clearField(VERIFY_ACCOUNT);
        Driver.clearField(AMOUNT);
    }

    @Override
    public void e_Invalid_Amount_Filled() {
        Driver.setField(AMOUNT, "a");
    }

    @Override
    public void v_Missing_Field_Attempt() {
        Assert.assertTrue(Driver.containsUrl(Urls.BILL_PAY_URL));

        List<String> missingStrings = Arrays.asList("is required", "cannot be empty");

        boolean containsAny = false;
        for (String missingString : missingStrings) {
            if (Driver.containsText(missingString)) {
                containsAny = true;
                break;
            }
        }

        Assert.assertTrue(containsAny);
    }

    @Override
    public void e_Invalid_Account_Send_Payment() {
        sendPayment();
    }

    @Override
    public void v_Invalid_Account_Attempt() {
        Assert.assertTrue(Driver.containsUrl(Urls.BILL_PAY_URL));
        Assert.assertTrue(Driver.containsText("Please enter a valid number."));
    }

    @Override
    public void e_Invalid_Missing_Field_Send_Payment() {
        sendPayment();
    }

    @Override
    public void v_Invalid_Input() {
        Assert.assertTrue(Driver.containsUrl(Urls.BILL_PAY_URL));
    }

    @Override
    public void e_Invalid_Amount_Send_Payment() {
        sendPayment();
    }

    @Override
    public void v_Invalid_Amount_Attempt() {
        Assert.assertTrue(Driver.containsUrl(Urls.BILL_PAY_URL));
        Assert.assertTrue(Driver.containsText("Please enter a valid amount."));
    }

    @Override
    public void e_Invalid_Input_Send_Payment() {
        sendPayment();
    }

    @Override
    public void e_Fill_Correctly() {
        Driver.setField(PAYEE_NAME, "John Doe");
        Driver.setField(PAYEE_ADDRESS_STREET, "123 Some Street");
        Driver.setField(PAYEE_ADDRESS_CITY, "Some City");
        Driver.setField(PAYEE_ADDRESS_STATE, "Some State");
        Driver.setField(PAYEE_ADDRESS_ZIP_CODE, "12345");
        Driver.setField(PAYEE_PHONE_NUMBER, "123-456-7890");
        Driver.setField(PAYEE_ACCOUNT_NUMBER, "12345");
        Driver.setField(VERIFY_ACCOUNT, "12345");
        Driver.setField(AMOUNT, "100");
    }

    @Override
    public void e_Invalid_Account_Mismatch() {
        e_Fill_Correctly();
        Driver.setField(PAYEE_ACCOUNT_NUMBER, "12345");
        Driver.setField(VERIFY_ACCOUNT, "54321");
    }

    @Override
    public void v_Invalid_Account_Mismatch_Attempt() {
        Assert.assertTrue(Driver.containsUrl(Urls.BILL_PAY_URL));
        Assert.assertTrue(Driver.containsText("The account numbers do not match."));
    }

    @Override
    public void e_Invalid_Account_Mismatch_Send_Payment() {
        sendPayment();
    }

    @Override
    public void v_Invalid_Account_Mismatch() {
        Assert.assertTrue(Driver.containsUrl(Urls.BILL_PAY_URL));
    }

    @Override
    public void e_Click_Bill_Pay() {
        Driver.navigateTo(Urls.BILL_PAY_URL);
    }
}
