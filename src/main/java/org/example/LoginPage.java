package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private final String url = "https://lennertamas.github.io/roxo/";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //    //setDriver(driver);
    //}

    //private final String url = "https://lennertamas.github.io/roxo/";

    private final By TERMS_AND_CONDITIONS = By.xpath("//h1[text()=\"Terms and conditions\"]");
    private final By ACCEPT_BUTTON = By.id("terms-and-conditions-button");

    private final By REGISTER_BUTTON = By.id("register-form-button");
    private final By REGISTER_USERNAME_FIELD = By.id("register-username");
    private final By REGISTER_PASSWORD_FIELD = By.id("register-password");
    private final By REGISTER_EMAIL_FIELD = By.id("register-email");
    private final By REGISTER_DESCRIPTION_FIELD = By.id("register-description");
    private final By REGISTER_CONFIRM_BUTTON = By.xpath("//button[@onclick='registerUser()']");
    private final By REGISTER_ALERT = By.id("import org.example.BootstrapDownload;");



    public void Navigate() {
        driver.navigate().to(url);
    }

    public void AcceptTermsAndConditions() {
        //if (driver.findElement(TERMS_AND_CONDITIONS).isDisplayed()) {
            driver.findElement(ACCEPT_BUTTON).click();
        //}

    }

    public void RegisterBasic(String username, String password, String email, String description) {
        driver.findElement(REGISTER_BUTTON).click();
        driver.findElement(REGISTER_USERNAME_FIELD).sendKeys(username);
        driver.findElement(REGISTER_PASSWORD_FIELD).sendKeys(password);
        driver.findElement(REGISTER_EMAIL_FIELD).sendKeys(email);
        driver.findElement(REGISTER_DESCRIPTION_FIELD).sendKeys(description);
        driver.findElement(REGISTER_CONFIRM_BUTTON).click();

    }
    public boolean RegisterAlertIsDisplayed () {
        return(driver.findElement(REGISTER_ALERT).isDisplayed());
    }

}
