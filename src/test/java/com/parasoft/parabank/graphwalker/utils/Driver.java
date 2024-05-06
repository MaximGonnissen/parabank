package com.parasoft.parabank.graphwalker.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Driver {
    protected static WebDriver _driver;

    public static void setDriver(WebDriver driver) {
        _driver = driver;
    }

    public static WebDriver getDriver() {
        return _driver;
    }

    public static void quitDriver() {
        _driver.quit();
    }
    public static void maximizeWindow() {
        _driver.manage().window().maximize();
    }

    public static void navigateTo(String url) {
        _driver.get(url);
    }

    public static boolean equalsUrl(String url) {
        return _driver.getCurrentUrl().equals(url);
    }

    public static boolean containsUrl(String url) {
        return _driver.getCurrentUrl().contains(url);
    }

    public static boolean titleEquals(String title) {
        return _driver.getTitle().equals(title);
    }

    public static boolean titleContains(String title) {
        return _driver.getTitle().contains(title);
    }

    public static boolean containsText(String text) {
        return _driver.getPageSource().contains(text);
    }

    public static void fillField(By by, String value) {
        waitForElement(by);
        _driver.findElement(by).sendKeys(value);
    }

    public static boolean isElementPresent(By by) {
        try {
            _driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public static void clickElement(By by) {
        waitForElement(by);
        _driver.findElement(by).click();
    }

    public static void clearField(By by) {
        waitForElement(by);
        _driver.findElement(by).clear();
    }

    public static void waitFor(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void refresh() {
        _driver.navigate().refresh();
    }

    public static void waitForElement(By by, int timeout) {
        if (!isElementPresent(by)) {
            waitFor(timeout);
        }
    }

    public static void waitForElement(By by) {
        waitForElement(by, 100);
    }

    public static WebElement findElement(By by) {
        waitForElement(by);
        return _driver.findElement(by);
    }

    public static List<WebElement> findElements(By by) {
        waitForElement(by);
        return _driver.findElements(by);
    }
}
