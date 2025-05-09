package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.Locale;

public class BaseTest {
    public WebDriver driver;

    @Parameters("browserName")
    @BeforeMethod
    public void createDriver(@Optional("chrome") String browserName) {
        switch (browserName.trim().toLowerCase(Locale.ROOT)) {
            case "chrome":
                System.out.println("Launching Chrome browser");
                driver = new ChromeDriver();
                break;
            case "edge":
                System.out.println("Lauching Egde browser");
                driver = new EdgeDriver();
                break;
            case "firefox":
                System.out.println("Lauching Firefox browser");
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + "is invalid, Launching Chrome as browser of choice...");
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();;
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));

        new WebUI(driver);
    }

    @AfterMethod
    public void closeDriver() {
        WebUI.sleep(2);
        driver.quit();
    }
}
