package com.parasoft.parabank.graphwalker.modelimpl;

import com.parasoft.parabank.graphwalker.Base.TestExecutionContext;
import com.parasoft.parabank.graphwalker.utils.Coverage;
import com.parasoft.parabank.graphwalker.utils.Driver;
import com.parasoft.parabank.graphwalker.utils.Helpers;
import com.parasoft.parabank.graphwalker.utils.Urls;
import org.graphwalker.BillPay;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

@GraphWalker(value = Coverage.RandomEdgeCoverage100)
public class BillPayImpl extends TestExecutionContext implements BillPay {

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
        Driver.navigateTo(Urls.ACCOUNTS_OVERVIEW_URL);
    }

    @Override
    public void v_Error() {
        Assert.assertTrue(Driver.containsUrl(Urls.BILL_PAY_URL));
        Assert.assertTrue(Driver.containsText("An internal error has occurred and has been logged."));
    }

    @Override
    public void v_Dummy_Navigation_SHARED() {
        Assert.assertFalse(Driver.containsUrl(Urls.INDEX_URL));
    }

    @Override
    public void e_Invalid_Input() {
        e_Fill_Correctly();
        Driver.findElement(By.name("payee.accountNumber")).sendKeys("1a");
        Driver.findElement(By.name("verifyAccount")).sendKeys("1a");
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
        Driver.findElement(By.name("payee.accountNumber")).sendKeys("a");
        Driver.findElement(By.name("verifyAccount")).sendKeys("a");
    }

    @Override
    public void v_Invalid_Account() {
        Assert.assertTrue(Driver.containsUrl(Urls.BILL_PAY_URL));
    }

    @Override
    public void v_Bill_Pay_SHARED() {
//        if (getLastElement() == null) {
//            // Assume start of an isolated test, log in and navigate
//            Helpers.ensureLoggedIn();
//            Driver.navigateTo(Urls.BILL_PAY_URL);
//        }

        // At bill pay page
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
        Driver.findElement(By.name("payee.name")).clear();
        Driver.findElement(By.name("payee.accountNumber")).clear();
        Driver.findElement(By.name("verifyAccount")).clear();
        Driver.findElement(By.name("amount")).clear();
    }

    @Override
    public void e_Invalid_Amount_Filled() {
        Driver.findElement(By.name("amount")).sendKeys("a");
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
        Driver.findElement(By.name("payee.name")).sendKeys("John Doe");
        Driver.findElement(By.name("payee.address.street")).sendKeys("123 Some Street");
        Driver.findElement(By.name("payee.address.city")).sendKeys("Some City");
        Driver.findElement(By.name("payee.address.state")).sendKeys("Some State");
        Driver.findElement(By.name("payee.address.zipCode")).sendKeys("12345");
        Driver.findElement(By.name("payee.phoneNumber")).sendKeys("123-456-7890");
        Driver.findElement(By.name("payee.accountNumber")).sendKeys("12345");
        Driver.findElement(By.name("verifyAccount")).sendKeys("12345");
        Driver.findElement(By.name("amount")).sendKeys("100");
    }

    @Override
    public void e_Invalid_Account_Mismatch() {
        e_Fill_Correctly();
        Driver.findElement(By.name("payee.accountNumber")).sendKeys("12345");
        Driver.findElement(By.name("verifyAccount")).sendKeys("54321");
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
}
