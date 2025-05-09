package common;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class WebUI {
    private static WebDriver driver;
    private static int TIMEOUT = 10;
    private static double SLEEP_TIME = 0.5;

    public WebUI(WebDriver _driver) {
        driver = _driver;
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void logConsole(Object message) {
        System.out.println(message);
    }

    public static WebElement getWebElement(By by) {
        return driver.findElement(by);
    }

    public static List<WebElement> getWebElements(By by) {
        return driver.findElements(by);
    }

    public static Boolean checkElementExist(By by) {
        List<WebElement> listElement = driver.findElements(by);

        if (listElement.size() > 0) {
            System.out.println("checkElementExist: " + true + " --- " + by);
            return true;
        } else {
            System.out.println("checkElementExist: " + false + " --- " + by);
            return false;
        }
    }

    //Chờ đợi trang load xong mới thao tác
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            //System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

    public static void waitForElementVisible(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            logConsole("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            logConsole("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            logConsole("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            logConsole("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable error) {
            logConsole("Time waiting for element ready to click " + by.toString());
            Assert.fail("Time waiting for element ready to click. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable error) {
            logConsole("Time waiting for element ready to click. " + by.toString());
            Assert.fail("Time waiting for element ready to click. " + by.toString());
        }
    }

    public static void openURL(String url) {
        driver.get(url);
        sleep(SLEEP_TIME);
        logConsole("Open URL: " + url);
    }

    public static void clickElement(By by) {
        waitForElementClickable(by);
        sleep(SLEEP_TIME);
        getWebElement(by).click();
        logConsole("Click on element: " + by);
    }

    public static void clickElement(By by, int timeout) {
        waitForElementClickable(by, timeout);
        sleep(SLEEP_TIME);
        getWebElement(by).click();
        logConsole("Click on element: " + by);
    }

    public static void setText(By by, String value) {
        waitForElementVisible(by);
        sleep(SLEEP_TIME);
        getWebElement(by).sendKeys(value);
        logConsole("Set text " + value + " on element " + by);
    }

//    public static void setText(By by, String value, int timeout) {
//        waitForElementVisible(by,timeout);
//        sleep(SLEEP_TIME);
//        getWebElement(by).sendKeys(value);
//        logConsole("Set text " + value + " on element " + by);
//    }

    public static boolean isDisplay(By by) {
        return getWebElement(by).isDisplayed();
    }

    public static String getElementText(By by) {
        waitForElementPresent(by);
        sleep(SLEEP_TIME);
        logConsole("Get text of element " + by);
        String text = getWebElement(by).getText();
        logConsole("==> TEXT: " + text);
        return text;
    }

    public static String getElementAttribute(By by, String attributeName) {
        waitForElementPresent(by);
        sleep(SLEEP_TIME);
        logConsole("Get attribute of element " + by);
        String value = getWebElement(by).getAttribute(attributeName);
        logConsole("==> Attribute value: " + value);
        return value;
    }

    public static String getElementCssValue(By by, String cssPropertyName) {
        waitForElementPresent(by);
        sleep(SLEEP_TIME);
        logConsole("Get CSS value of element " + by);
        String value = getWebElement(by).getCssValue(cssPropertyName);
        logConsole("==> Css value: " + value);
        return value;
    }

    public static void selectValueInStaticDropdown(By dropdown, By value) {
        clickElement(dropdown);
        sleep(SLEEP_TIME);
        clickElement(value);
    }

    public static void selectValueInDynamicDropdown(By dropdown, By inputSearch, String value) {
        clickElement(dropdown);
        sleep(SLEEP_TIME);
        clickElement(inputSearch);
        setText(inputSearch, value);
        getWebElement(inputSearch).sendKeys(Keys.ENTER);
        clickElement(dropdown);
    }
}
