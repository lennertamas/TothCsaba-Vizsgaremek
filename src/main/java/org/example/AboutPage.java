package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AboutPage extends BasePage{

    public AboutPage(WebDriver driver) {
        super(driver);
    }

    public final By EXPERTISE_DATA = By.xpath("//ul[@class='site-expertise-list']/li");
    public final By MEMBERS_DATA = By.xpath("//div[@class='site-team-member-content']");

    public final By MEMBERS_NAME = By.xpath("//*[@class='site-team-member-content']/h3");


    public String getExpertiseData () {
        List<WebElement> lines = driver.findElements(EXPERTISE_DATA);
        String result = "";
        for (WebElement line : lines) {
            String value = line.getText();
            result = result + ", " + value;
        }
        return result.substring(2);
    }

    public List<String> getMembers() throws InterruptedException {
        WebElement body = driver.findElement(By.tagName("body"));
        for (int i = 0; i < 3; i++) {
            body.sendKeys(Keys.PAGE_DOWN);
        }
        Thread.sleep(300);
        List<WebElement> datas = driver.findElements(MEMBERS_DATA);
        List<String> result = new ArrayList<>();
        for (WebElement data : datas) {
                String[] parts = data.getText().split("\n");
                String name = parts[0];
                String position = parts[1];
                result.add(name + " - " + position);
            }
        return result;
        }

    public List<String> getMembersNameonly() {
        WebElement body = driver.findElement(By.tagName("body"));
        for (int i = 0; i < 3; i++) {
            body.sendKeys(Keys.PAGE_DOWN);
        }
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<WebElement> names = driver.findElements(MEMBERS_NAME);
        List<String> nameList = new ArrayList<>();
        for (WebElement name : names) {
            String[] nameArray = name.getText().split("\n");
            nameList.add(nameArray[0]);
        }
        return nameList;

    }



    //nyilván csak arraylist-be tudja kiolvasni
    //azt alakítottam utána tömbbé
    //utána assertArraysEquals

}
