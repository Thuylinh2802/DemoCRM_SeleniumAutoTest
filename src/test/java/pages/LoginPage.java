package pages;

import common.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {
    private WebDriver driver;
    private String URL = "https://crm.anhtester.com/admin/authentication";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By header = By.xpath("//h1[normalize-space()='Login']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By errorMessage = By.xpath("//div[contains(@class, 'alert alert-danger')]");
    private By checkboxRememberMe = By.xpath("//input[@id='remember']");
    private By linkTextForgotPassword = By.xpath("//a[normalize-space()='Forgot Password?']");

    public DashboardPage loginCRM(String email, String password) {
        WebUI.openURL(URL);
        WebUI.waitForPageLoaded();
        WebUI.setText(inputEmail, email);
        WebUI.setText(inputPassword, password);
        WebUI.clickElement(buttonLogin);
        return new DashboardPage(driver);
    }

    public void verifyLoginSuccess() {
        Assert.assertFalse(driver.getCurrentUrl().contains("authentication"), "Fail. Vẫn ở trang Login");
    }

    public void verifyLoginFail(String errorMessageContent) {
        WebUI.waitForPageLoaded();
        Assert.assertTrue(driver.getCurrentUrl().contains("authentication"), "Success. Không ở trang Login");
        WebUI.waitForElementVisible(errorMessage);
        Assert.assertEquals(WebUI.getElementText(errorMessage), errorMessageContent);
    }
}
