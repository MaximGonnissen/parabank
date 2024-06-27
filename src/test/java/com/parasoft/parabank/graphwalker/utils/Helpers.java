package com.parasoft.parabank.graphwalker.utils;

import org.openqa.selenium.By;

import java.util.Random;

public class Helpers {
    public static Random random = new Random();
    public static Random unseededRandom = new Random(System.currentTimeMillis());
    public static boolean ensureLoggedIn() {
        Driver.navigateTo(Urls.BASE_URL);

        if (!Driver.containsText("Customer Login")) {
            return true;
        }

        Driver.setField(By.name("username"), "Test");
        Driver.setField(By.name("password"), "Test");
        Driver.clickElement(By.xpath("//input[@value='Log In']"));

        return false;
    }

    public static void seedRandom(long seed) {
        random = new Random(seed);
    }

    public static void resetAndSetupParaBank() {
        // We first wipe the database clean, then initialize it with a high initial balance to help prolonged random
        // walks from running out of money.
        Driver.navigateTo(Urls.ADMIN_URL);
        Driver.waitFor(20);
        Driver.setField(By.id("initialBalance"), "100000000");
        Driver.clickElement(By.xpath("//input[@type='submit' and @value='Submit']"));
        Driver.waitFor(10);
        Driver.clickElement(By.xpath("//button[@name='action' and @value='CLEAN']"));
        Driver.waitFor(10);
        Driver.clickElement(By.xpath("//button[@name='action' and @value='INIT']"));
        Driver.waitFor(10);
        Driver.clickElement(By.xpath("//input[@type='submit' and @value='Submit']"));
        Driver.waitFor(10);

        // We then register a Test user.
        Driver.navigateTo(Urls.REGISTER_URL);
        Driver.waitFor(20);
        Driver.setField(By.xpath("//*[@id='customer.firstName']"), "John");
        Driver.setField(By.xpath("//*[@id='customer.lastName']"), "Doe");
        Driver.setField(By.xpath("//*[@id='customer.address.street']"), "123 Some Street");
        Driver.setField(By.xpath("//*[@id='customer.address.city']"), "Some City");
        Driver.setField(By.xpath("//*[@id='customer.address.state']"), "Some State");
        Driver.setField(By.xpath("//*[@id='customer.address.zipCode']"), "12345");
        Driver.setField(By.xpath("//*[@id='customer.phoneNumber']"), "1234567890");
        Driver.setField(By.xpath("//*[@id='customer.ssn']"), "123456789");
        Driver.setField(By.xpath("//*[@id='customer.username']"), "Test");
        Driver.setField(By.xpath("//*[@id='customer.password']"), "Test");
        Driver.setField(By.xpath("//*[@id='repeatedPassword']"), "Test");
        Driver.clickElement(By.xpath("//input[@value='Register']"));
        Driver.waitFor(10);

        // Then, we open a new account for the user to ensure there are two accounts.
        Driver.navigateTo(Urls.OPEN_NEW_ACCOUNT_URL);
        Driver.waitFor(20);
        Driver.findElement(By.xpath("//input[@value='Open New Account']")).click();
        Driver.waitFor(10);

        // Finally, we perform a single transaction to ensure that the user has a transaction history.
        Driver.navigateTo(Urls.TRANSFER_FUNDS_URL);
        Driver.waitFor(20);
        Driver.setField(By.id("amount"), "100");
        Driver.findElement(By.xpath("//input[@value='Transfer']")).click();
        Driver.waitFor(10);

        // We then log out to ensure that the next test starts from a clean slate.
        Driver.navigateTo(Urls.LOGOUT_URL);
    }
}
