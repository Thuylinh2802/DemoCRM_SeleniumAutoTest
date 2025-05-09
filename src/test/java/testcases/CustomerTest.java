package testcases;

import common.BaseTest;
import org.testng.annotations.Test;
import pages.CommonPage;
import pages.CustomerPage;
import pages.LoginPage;

public class CustomerTest extends BaseTest {
    CustomerPage customerPage;

    @Test
    public void testVerifyTotalCustomer() {
        LoginPage loginPage = new LoginPage(driver);
        CommonPage commonPage = new CommonPage(driver);
        customerPage = new CustomerPage(driver);
        loginPage.loginCRM("admin@example.com", "123456");
        commonPage.openCustomersPage();
        customerPage.verifyTotalCustomers("875");
    }

    @Test
    public void testAddNewCustomerSuccess(){
        LoginPage loginPage = new LoginPage(driver);
        CommonPage commonPage = new CommonPage(driver);
        CustomerPage customerPage = new CustomerPage(driver);
        loginPage.loginCRM("admin@example.com", "123456");
        commonPage.openCustomersPage();
        customerPage.addNewCustomer("LTT", "10", "123456");
        customerPage.verifyAddNewCustomerSuccess("LTT");
    }

    @Test
    public void testAddNewCustomerFailWithCompanyNull(){
        LoginPage loginPage = new LoginPage(driver);
        CommonPage commonPage = new CommonPage(driver);
        CustomerPage customerPage = new CustomerPage(driver);
        loginPage.loginCRM("admin@example.com", "123456");
        commonPage.openCustomersPage();
        customerPage.addNewCustomer("", "10", "123456");
        customerPage.verifyAddNewCustomerFail();
    }

    @Test
    public void testImportCustomer() {
        LoginPage loginPage = new LoginPage(driver);
        CommonPage commonPage = new CommonPage(driver);
        customerPage = new CustomerPage(driver);
        loginPage.loginCRM("admin@example.com", "123456");
        commonPage.openCustomersPage();
        customerPage.importCustomer();
    }
}
