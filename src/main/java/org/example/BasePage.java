package org.example;

import org.openqa.selenium.WebDriver;

public class BasePage {

    WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    //public void setDriver(WebDriver driver) {
    //        this.driver = driver;
    //    }

}