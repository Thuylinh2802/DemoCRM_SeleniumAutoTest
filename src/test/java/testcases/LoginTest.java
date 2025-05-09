package testcases;

import common.BaseTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Test(priority = 1)
    public void testLoginSuccess() {
        loginPage = new LoginPage(driver);
        loginPage.loginCRM("admin@example.com", "123456");
        loginPage.verifyLoginSuccess();
    }

    @Test(priority = 2)
    public void testLoginFailWithEmailInvalid() {
        loginPage = new LoginPage(driver);
        loginPage.loginCRM("admin@example123.com","123456");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test(priority = 3)
    public void testLoginFailWithEmailInvalidFormat() {
        loginPage = new LoginPage(driver);
        loginPage.loginCRM("admin@examplecom","123456");
        loginPage.verifyLoginFail("The Email Address field must contain a valid email address.");
    }

    @Test(priority = 4)
    public void testLoginFailWithEmailNull() {
        loginPage = new LoginPage(driver);
        loginPage.loginCRM("","123456");
        loginPage.verifyLoginFail("The Email Address field is required.");
    }

    @Test(priority = 5)
    public void testLoginFailWithPasswordInvalid() {
        loginPage = new LoginPage(driver);
        loginPage.loginCRM("admin@example.com","1234567");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    @Test(priority = 6)
    public void testLoginFailWithPasswordNull() {
        loginPage = new LoginPage(driver);
        loginPage.loginCRM("admin@example.com","");
        loginPage.verifyLoginFail("The Password field is required.");
    }
}
