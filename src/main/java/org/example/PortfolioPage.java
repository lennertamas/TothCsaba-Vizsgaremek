package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class PortfolioPage extends BasePage {
    public PortfolioPage(WebDriver driver) {
        super(driver);
    }

    public final String url = "https://lennertamas.github.io/roxo/portfolio.html";

    public final By PAGINATION_AREA = By.xpath("//ul[@class='pagination']");
    //public final By NEXT_BUTTON = By.xpath("//a[@aria-label='Next']/span");

    public final By PRODUCTS = By.xpath("site-project-item-thumb");

    //public final By ACTIVE_PAGE_BUTTON = By.xpath("//*[@id=\"project\"]/div/div/div[5]/div/ul/li[@class='page-item active']");
    public final By ACTIVE_PAGE_BUTTON = By.xpath(" //*[@class='page-item active']");

    //public final By NEXT_BUTTON = By.xpath("//*[@id=\"project\"]/div/div/div[5]/div/ul/li[5]/a");
    public final By NEXT_BUTTON = By.xpath(" //*[@aria-label='Next']");
    public final By NEXT_BUTTON_PARENT = By.xpath(" //div[5]/div/ul/li[5]");

    public final By LAST_BUTTON = By.xpath(" //*[@aria-label='Last']");

    public final By PREVIOUS_BUTTON = By.xpath(" //*[@aria-label='Previous']");

    public final By FIRST_BUTTON = By.xpath(" //*[@aria-label='First']");

    //WebElement NEXT_BUTTON = driver.findElement(By.xpath("\"//a[@aria-label='Next']/span\""));
    //WebElement NEXT_BUTTON_PARENT = NEXT_BUTTON.findElement(By.xpath("./parent::li[contains(@class, 'page-item')]"));

    public int GetPageNumber() {
        WebElement body = driver.findElement(By.tagName("body"));
        for (int i = 0; i < 3; i++) {
            body.sendKeys(Keys.PAGE_DOWN);
        }
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //Thread.sleep(300);
        WebElement activePageButton = driver.findElement(ACTIVE_PAGE_BUTTON);
        WebElement activePageButtonChild = activePageButton.findElement(By.tagName("a"));
        String activePageButtonText = activePageButtonChild.getText();
        int pageNumber = Integer.parseInt(activePageButtonText);
        return pageNumber;
        //driver.findElement(ACTIVE_PAGE_BUTTON).getAttribute("href").;
    }

    public void clickNext () {
        WebElement body = driver.findElement(By.tagName("body"));
        for (int i = 0; i < 3; i++) {
            body.sendKeys(Keys.PAGE_DOWN);
        }
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.findElement(NEXT_BUTTON).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    public void clickPrevious () {
        WebElement body = driver.findElement(By.tagName("body"));
        for (int i = 0; i < 3; i++) {
            body.sendKeys(Keys.PAGE_DOWN);
        }
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.findElement(PREVIOUS_BUTTON).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    public void clickLastPage () {
        WebElement body = driver.findElement(By.tagName("body"));
        for (int i = 0; i < 3; i++) {
            body.sendKeys(Keys.PAGE_DOWN);
        }
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.findElement(LAST_BUTTON).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    public void clickFirstPage () {
        WebElement body = driver.findElement(By.tagName("body"));
        for (int i = 0; i < 3; i++) {
            body.sendKeys(Keys.PAGE_DOWN);
        }
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.findElement(FIRST_BUTTON).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    public int GetPageNumberMax (int maxPageNumber) throws InterruptedException {
        WebElement body = driver.findElement(By.tagName("body"));
        for (int i = 0; i < 3; i++) {
            body.sendKeys(Keys.PAGE_DOWN);
        }
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        int pageNumber = GetPageNumber();
        scrollToElement(PAGINATION_AREA);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        while (pageNumber < maxPageNumber && !driver.findElement(NEXT_BUTTON_PARENT).getAttribute("class").contains("disabled")) {
            driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
            driver.findElement(NEXT_BUTTON).click();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            for (int i = 0; i < 3; i++) {
                body.sendKeys(Keys.PAGE_DOWN);
            }
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            pageNumber++;
        } return pageNumber;
    }

    public int GetProductNumber(int maxPageNumber) throws InterruptedException {
        WebElement body = driver.findElement(By.tagName("body"));
        for (int i = 0; i < 3; i++) {
            body.sendKeys(Keys.PAGE_DOWN);
        }
        Thread.sleep(300);
        int pageNumber = GetPageNumber();
        int productNumber = driver.findElements(PRODUCTS).size();
        while (pageNumber < maxPageNumber && !driver.findElement(NEXT_BUTTON_PARENT).getAttribute("class").contains("disabled")) {
            driver.findElement(NEXT_BUTTON).click();
            for (int i = 0; i < 3; i++) {
                body.sendKeys(Keys.PAGE_DOWN);
            }
            Thread.sleep(300);
            productNumber += driver.findElements(PRODUCTS).size();
        }
        return productNumber;

    }

}


