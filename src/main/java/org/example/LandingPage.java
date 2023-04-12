package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage{
    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public final String url = "https://lennertamas.github.io/roxo/landing.html";

    public final By ABOUT_BUTTON = By.xpath("//li[@class='nav-item']/a[contains(@href, 'about')]");

    public String GetURL() {
        return(driver.getCurrentUrl());

    }

    public void GoToAbout() {
        driver.findElement(ABOUT_BUTTON).click();
    }

}
