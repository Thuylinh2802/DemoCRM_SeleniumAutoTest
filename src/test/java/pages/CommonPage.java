package pages;

import common.BaseTest;
import common.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonPage {
    private WebDriver driver;

    public CommonPage(WebDriver driver) {
        this.driver = driver;
        new WebUI(driver);
    }

    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    private By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    private By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    private By inputSearch = By.xpath("//input[@id='search_input']");
    private By dropdownProfile = By.xpath("//img[contains(@class,'staff-profile-image-small')]");
    private By itemLogout = By.xpath("//a[contains(@class, 'profile')]//following-sibling::ul//a[normalize-space()='Logout']");

    public DashboardPage openDashboardPage() {
        WebUI.clickElement(menuDashboard);
        WebUI.waitForPageLoaded();
        return new DashboardPage(driver);
    }

    public CustomerPage openCustomersPage() {
        WebUI.clickElement(menuCustomers);
        WebUI.waitForPageLoaded();
        return new CustomerPage(driver);
    }

    public ProjectPage openProjectsPage() {
        WebUI.clickElement(menuProjects);
        WebUI.waitForPageLoaded();
        return new ProjectPage(driver);
    }

    public LoginPage logout() {
        WebUI.clickElement(dropdownProfile);
        WebUI.sleep(1);
        WebUI.clickElement(itemLogout);
        WebUI.waitForPageLoaded();
        return new LoginPage(driver);
    }
}
