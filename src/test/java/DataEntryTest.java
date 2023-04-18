import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.example.AboutPage;
import org.example.LandingPage;
import org.example.LoginPage;
import org.example.MessagePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DataEntryTest {

    WebDriver driver;
    LoginPage loginPage;
    LandingPage landingPage;
    MessagePage messagePage;


    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = BaseTest.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //loginPage = new LoginPage(driver);
    }

    @Test
    @Order(1)
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Send message without parameters")
    public void EmptyMessageTest() {
        loginPage = new LoginPage(driver);
        landingPage = new LandingPage(driver);
        messagePage = new MessagePage(driver);
        String validUserName = "beckz";
        String validPassword = "30y123";
        loginPage.Navigate();
        loginPage.AcceptTermsAndConditions();
        loginPage.LoginFunction(validUserName, validPassword);
        Assertions.assertEquals("https://lennertamas.github.io/roxo/landing.html", landingPage.GetURL());
        landingPage.GoToMessage();
        messagePage.clickSubmit();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Duration waitTime = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        String expected = "Message sent!";
        Assertions.assertNotEquals(expected, alertText);
        messagePage.handleAlert();
    }

    @Test
    @Order(2)
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Send message with parameters")
    public void ValidMessageTest() {
        loginPage = new LoginPage(driver);
        landingPage = new LandingPage(driver);
        messagePage = new MessagePage(driver);
        String validUserName = "beckz";
        String validPassword = "30y123";
        loginPage.Navigate();
        loginPage.AcceptTermsAndConditions();
        loginPage.LoginFunction(validUserName, validPassword);
        Assertions.assertEquals("https://lennertamas.github.io/roxo/landing.html", landingPage.GetURL());
        landingPage.GoToMessage();
        String firstname = "Jack";
        String lastname = "Burton";
        String email = "jackburton@gmail.com";
        String Type = "Web Design";
        String message = "\"Dear Roxo, \n" +
                "\n" +
                "I would like to have a web design made for my transportation company's upcoming website. When could we schedule a meeting to discuss the details? \n" +
                "\n" +
                "Best regards, \n" +
                "\n" +
                "Jack Burton.\" ";
        messagePage.fillOutFormBasic(firstname, lastname, email, Type, message);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Duration waitTime = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        String expected = "Message sent!";
        Assertions.assertEquals(expected, alertText);
        messagePage.handleAlert();
        Set<Cookie> cookies = driver.manage().getCookies();
        String result = null;
        for (Cookie cookie : cookies) {
            result = cookie.getName() + ": " + cookie.getValue();
            Assertions.assertEquals("tandc: true", result);
        }

    }
}
