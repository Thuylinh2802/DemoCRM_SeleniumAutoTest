package pages;

import common.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;

public class CustomerPage {
    private WebDriver driver;
    private String URL = "https://crm.anhtester.com/admin/clients";

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    private By totalCustomers = By.xpath("//span[normalize-space()='Total Customers']/preceding-sibling::span");
    private By activeCustomers = By.xpath("//span[normalize-space()='Active Customers']/preceding-sibling::span");
    private By inactiveCustomers = By.xpath("//span[normalize-space()='Inactive Customers']/preceding-sibling::span");
    private By buttonNewCustomers = By.xpath("//a[normalize-space()='New Customer']");
    private By buttonImportCustomers = By.xpath("//a[normalize-space()='Import Customers']");
    private By inputSearch = By.xpath("//div[@id='clients_filter']//input");

    private By inputCompany = By.xpath("//input[@id='company']");
    private By inputVATNumber = By.xpath("//input[@id='vat']");
    private By inputPhone = By.xpath("//input[@id='phonenumber']");
    private By dropdownDefaultLanguage = By.xpath("//label[normalize-space()='Default Language']/parent::div//button");
    private By dropdownGroups = By.xpath("//label[normalize-space()='Groups']/parent::div//button");
    private By dropdownCurrency = By.xpath("//label[contains(normalize-space(),'Currency')]/parent::div//button");
    private By dropdownCountry = By.xpath("//label[@for='zip']/parent::div//following-sibling::div//button");
    private By buttonSave = By.xpath("//button[contains(@class,'only-save')]");
    private By alertAddSuccess = By.xpath("//div[@id='alert_float_1']");

    private By itemFistCustomerOnTable = By.xpath("//tbody/tr[1]/td[3]/a");

    private By inputUploadFile = By.xpath("//input[@id='file_csv']");
    private By buttonSimulateImport = By.xpath("//button[normalize-space()='Simulate Import']");
    private By buttonImport = By.xpath("//button[normalize-space()='Import']");

    public void verifyTotalCustomers(String totalCustomerValue) {
        String totalActual = WebUI.getElementText(totalCustomers);
        WebUI.logConsole("Total Customers Actual: " +  totalActual);
        Assert.assertEquals(totalActual, totalCustomerValue, "The total of Customers not match");
    }

    public void verifyActiveCustomers(String activeCustomerValue) {
        String activeCustomersActual = WebUI.getElementText(activeCustomers);
        WebUI.logConsole("Total Customers Actual: " +  activeCustomersActual);
        Assert.assertEquals(activeCustomersActual, activeCustomerValue, "The active of Customers not match");
    }

    public void verifyInactiveCustomers(String inactiveCustomerValue) {
        String inactiveCustomersActual = WebUI.getElementText(inactiveCustomers);
        WebUI.logConsole("Total Customers Actual: " +  inactiveCustomersActual);
        Assert.assertEquals(inactiveCustomersActual, inactiveCustomerValue, "The inactive of Customers not match");
    }

    public void addNewCustomer(String companyName, String VATNumber, String phone) {
        WebUI.waitForPageLoaded();
        WebUI.clickElement(buttonNewCustomers);
        WebUI.setText(inputCompany, companyName);
        WebUI.setText(inputVATNumber, VATNumber);
        WebUI.setText(inputPhone, phone);
        WebUI.selectValueInDynamicDropdown(dropdownGroups, By.xpath("//label[normalize-space()='Groups']/parent::div//input"), "VIP");
        WebUI.selectValueInDynamicDropdown(dropdownCurrency, By.xpath("//label[contains(normalize-space(),'Currency')]/parent::div//input"), "USD");
        WebUI.selectValueInStaticDropdown(dropdownDefaultLanguage, By.xpath("//span[normalize-space()='English']"));
        WebUI.selectValueInDynamicDropdown(dropdownCountry, By.xpath("//label[@for='zip']/parent::div//following-sibling::div//input"), "Vietnam");
        WebUI.clickElement(buttonSave);
    }

    public void verifyAddNewCustomerSuccess(String companyName) {
        WebUI.waitForElementVisible(alertAddSuccess);
        WebUI.getElementText(alertAddSuccess);
        WebUI.openURL(URL);
        WebUI.setText(inputSearch, companyName);
        WebUI.sleep(2);
        Assert.assertTrue(WebUI.isDisplay(itemFistCustomerOnTable), "The customer is not display");
        Assert.assertEquals(WebUI.getElementText(itemFistCustomerOnTable), companyName, "The customer is not match");
        WebUI.clickElement(itemFistCustomerOnTable);
        WebUI.waitForPageLoaded();
        WebUI.sleep(1);
        Assert.assertEquals(WebUI.getElementAttribute(inputCompany, "value"), companyName, "The company name is not match");
        WebUI.logConsole("Success. Tạo mới khách hàng thành công");
    }

    public void verifyAddNewCustomerFail() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://crm.anhtester.com/admin/clients/client", "Success.Tạo mới khách hàng thành công");
        WebUI.logConsole("Fail. Tạo mới khách hàng không thành công");
    }

    public void importCustomer() {
        File uploadFile = new File("D:/Downloads/sample_import_file.csv");
        WebUI.waitForPageLoaded();
        WebUI.clickElement(buttonImportCustomers);
        WebUI.waitForPageLoaded();
        WebUI.getWebElement(inputUploadFile).sendKeys(uploadFile.getAbsolutePath());
        WebUI.sleep(3);
        WebUI.clickElement(buttonSimulateImport);
        WebUI.sleep(3);
    }
}
