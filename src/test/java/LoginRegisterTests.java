import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.LoginPage;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginRegisterTests {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeEach
    public void init() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        //options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");

        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //wait = new WebDriverWait(driver, 10);

    }

    @Test
    @Order(1)
    public void RegisterTestWithoutParameters() {
        loginPage = new LoginPage(driver);
        loginPage.Navigate();
        loginPage.AcceptTermsAndConditions();
        loginPage.RegisterBasic("","","","");
        Assertions.assertFalse(loginPage.RegisterAlertIsDisplayed());
    }

}
