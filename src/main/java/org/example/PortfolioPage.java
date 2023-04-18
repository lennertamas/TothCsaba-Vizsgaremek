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

    public final By PAGINATION_AREA_ABOVE = By.xpath("//*[@class='site-project-item-content']");

    public final By PRODUCTS = By.xpath("site-project-item-thumb");

    //public final By ACTIVE_PAGE_BUTTON = By.xpath("//*[@id=\"project\"]/div/div/div[5]/div/ul/li[@class='page-item active']");
    public final By ACTIVE_PAGE_BUTTON = By.xpath(" //*[@class='page-item active']");

    //public final By NEXT_BUTTON = By.xpath("//*[@id=\"project\"]/div/div/div[5]/div/ul/li[5]/a");
    public final By NEXT_BUTTON = By.xpath(" //*[@aria-label='Next']");
    //public final By NEXT_BUTTON_PARENT = By.xpath(" //div[5]/div/ul/li[5]");
    public final By NEXT_BUTTON_PARENT = By.xpath(" //*[@aria-label='Next']/..");
    public final By LAST_BUTTON = By.xpath(" //*[@aria-label='Last']");

    public final By PREVIOUS_BUTTON = By.xpath(" //*[@aria-label='Previous']");

    public final By FIRST_BUTTON = By.xpath(" //*[@aria-label='First']");
    //public final By FIRST_BUTTON = By.xpath("//*[@id=\"project\"]//li[1]");

    //WebElement NEXT_BUTTON = driver.findElement(By.xpath("\"//a[@aria-label='Next']/span\""));
    //WebElement NEXT_BUTTON_PARENT = NEXT_BUTTON.findElement(By.xpath("./parent::li[contains(@class, 'page-item')]"));

    public final By PRODUCT_NAME = By.xpath("//*[@class=\"site-project-item-content\"]//h3");

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

    public void clickNext () throws InterruptedException {
        WebElement body = driver.findElement(By.tagName("body"));
        for (int i = 0; i < 2; i++) {
            body.sendKeys(Keys.PAGE_DOWN);
        }
        Thread.sleep(3000);
        scrollToElement(PAGINATION_AREA_ABOVE);
        //Thread.sleep(3000);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        driver.findElement(NEXT_BUTTON).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    public void clickPrevious () {
        WebElement body = driver.findElement(By.tagName("body"));
        for (int i = 0; i < 3; i++) {
            body.sendKeys(Keys.PAGE_DOWN);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(PREVIOUS_BUTTON).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    public void clickLastPage () {
        WebElement body = driver.findElement(By.tagName("body"));
        for (int i = 0; i < 3; i++) {
            body.sendKeys(Keys.PAGE_DOWN);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(LAST_BUTTON).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    public void clickFirstPage () throws InterruptedException {
        //WebElement body = driver.findElement(By.tagName("body"));
        //for (int i = 0; i < 2; i++) {
        //    body.sendKeys(Keys.PAGE_DOWN);
        //}
        Thread.sleep(3000);
        scrollToElement(PAGINATION_AREA_ABOVE);
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Thread.sleep(5000);
        driver.findElement(FIRST_BUTTON).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    public int GetPageNumberMax (int maxPageNumber) throws InterruptedException {
        WebElement body = driver.findElement(By.tagName("body"));
        for (int i = 0; i < 2; i++) {
            body.sendKeys(Keys.PAGE_DOWN);
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        int pageNumber = GetPageNumber();
        scrollToElement(PAGINATION_AREA_ABOVE);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        while (pageNumber < maxPageNumber && !driver.findElement(NEXT_BUTTON_PARENT).getAttribute("class").contains("disabled")) {
            driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
            driver.findElement(NEXT_BUTTON).click();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            Thread.sleep(3000);
            scrollToElement(PAGINATION_AREA_ABOVE);
            Thread.sleep(3000);
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            pageNumber++;
        } return pageNumber;
    }

    public int GetProductNumber(int maxPageNumber) throws InterruptedException {
        WebElement body = driver.findElement(By.tagName("body"));
        for (int i = 0; i < 2; i++) {
            body.sendKeys(Keys.PAGE_DOWN);
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        int pageNumber = GetPageNumber();
        int productNumber = driver.findElements(PRODUCT_NAME).size();
        scrollToElement(PAGINATION_AREA_ABOVE);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        while (pageNumber < maxPageNumber && !driver.findElement(NEXT_BUTTON_PARENT).getAttribute("class").contains("disabled")) {
            driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
            driver.findElement(NEXT_BUTTON).click();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            Thread.sleep(3000);
            scrollToElement(PRODUCTS);
            Thread.sleep(3000);
            int newProductNumber = driver.findElements(PRODUCT_NAME).size();
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            productNumber = productNumber + newProductNumber;
            Thread.sleep(3000);
            scrollToElement(PAGINATION_AREA_ABOVE);
            Thread.sleep(3000);
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        }
        return productNumber;

    }

}


