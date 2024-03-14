package com.parasoft.parabank.graphwalker.utils;

import org.openqa.selenium.By;

import java.util.Random;

public class Helpers {
    public static Random random = new Random();
    public static boolean ensureLoggedIn() {
        Driver.navigateTo(Urls.BASE_URL);

        if (!Driver.containsText("Customer Login")) {
            return true;
        }

        Driver.fillField(By.name("username"), "Test");
        Driver.fillField(By.name("password"), "Test");
        Driver.clickElement(By.xpath("//input[@value='Log In']"));

        return false;
    }

    public static void seedRandom(long seed) {
        random = new Random(seed);
    }
}
