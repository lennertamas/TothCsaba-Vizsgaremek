package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


public class LoginPage extends BasePage{


    public LoginPage(WebDriver driver) { super(driver); }

    private final String url = "https://lennertamas.github.io/roxo/";

    private final By TERMS_AND_CONDITIONS = By.xpath("//h1[text()=\"Terms and conditions\"]");
    private final By ACCEPT_BUTTON = By.id("terms-and-conditions-button");

    private final By REGISTER_BUTTON = By.id("register-form-button");

    private final By REGISTER_BUTTON_REPEAT = By.xpath("//div[@id='register']/button[@id=\"register-form-button\"]");
    private final By REGISTER_USERNAME_FIELD = By.id("register-username");
    private final By REGISTER_PASSWORD_FIELD = By.id("register-password");
    private final By REGISTER_EMAIL_FIELD = By.id("register-email");
    private final By REGISTER_DESCRIPTION_FIELD = By.id("register-description");
    private final By REGISTER_CONFIRM_BUTTON = By.xpath("//button[@onclick='registerUser()']");
    private final By REGISTER_ALERT = By.id("register-alert");

    private final By LOGIN_BUTTON_ABOVE = By.xpath("//div[@id='register']/button[@id='login-form-button']");
    //private final By LOGIN_BUTTON_ABOVE = By.xpath("//div[@id='register']/button[@onclick='showLogin()']");

    private final By LOGIN_USERNAME_FIELD = By.id("email");
    private final By LOGIN_PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON_AFTERFILLED = By.xpath("//button[@onclick='myFunction()']");
    private final By LOGIN_ALERT = By.id("alert");




    public void Navigate() {

        driver.get(url);
    }

    public void AcceptTermsAndConditions() {
        //if (driver.findElement(TERMS_AND_CONDITIONS).isDisplayed()) {
            driver.findElement(ACCEPT_BUTTON).click();
        //}
    }

    public void ClickOnRegisterButton() {
        driver.findElement(REGISTER_BUTTON).click();
    }

    public void ClickOnRegisterButtonRepeat() {
        driver.findElement(REGISTER_BUTTON_REPEAT).click();
        driver.findElement(REGISTER_EMAIL_FIELD).clear();
        driver.findElement(REGISTER_PASSWORD_FIELD).clear();
    }

    public void ClearInputFields() {
        driver.findElement(REGISTER_EMAIL_FIELD).clear();
        driver.findElement(REGISTER_PASSWORD_FIELD).clear();
    }

    public void RegisterBasic(String username, String password, String email, String description) {
        driver.findElement(REGISTER_USERNAME_FIELD).sendKeys(username);
        driver.findElement(REGISTER_PASSWORD_FIELD).sendKeys(password);
        driver.findElement(REGISTER_EMAIL_FIELD).sendKeys(email);
        driver.findElement(REGISTER_DESCRIPTION_FIELD).sendKeys(description);
        driver.findElement(REGISTER_CONFIRM_BUTTON).click();
    }

    public boolean RegisterAlertIsDisplayed () {
        return(driver.findElement(REGISTER_ALERT).isDisplayed());
    }

    public void LoginFromRegister () {
        driver.findElement(LOGIN_BUTTON_ABOVE).click();
    }

    public void LoginFunction(String username, String password) {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(LOGIN_USERNAME_FIELD).click();
        driver.findElement(LOGIN_USERNAME_FIELD).sendKeys(username);
        driver.findElement(LOGIN_PASSWORD_FIELD).click();
        driver.findElement(LOGIN_PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON_AFTERFILLED).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public boolean LoginAlertIsDisplayed () {
        return(driver.findElement(LOGIN_ALERT).isDisplayed());
    }


}
