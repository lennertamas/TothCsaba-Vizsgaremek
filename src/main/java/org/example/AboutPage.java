package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class AboutPage extends BasePage{

    //Actions actions = new Actions(driver);

    public AboutPage(WebDriver driver) {
        super(driver);
    }

    public final By EXPERTISE_DATA = By.xpath("//ul[@class='site-expertise-list']/li");
    public final By MEMBERS_DATA = By.xpath("//div[@class='site-team-member-content']");

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
}
