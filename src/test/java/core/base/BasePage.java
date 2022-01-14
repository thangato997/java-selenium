package core.base;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ExtentTest test;

    protected BasePage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToUrl(String url) {
        driver.get(url);
    }

    public boolean checkIsDisplayed(By locator) {
        return wait.until(isDisplay -> driver.findElement(locator).isDisplayed());
    }

    public void findElementAndSendTexts(By locator, String text) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator)).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void findElementPresenceAndClick(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
    }

    public void findElementVisibilityAndClick(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
    }

    /**
     * Select a value of option from the dropdown list
     * @param locator to catch the element
     * @param by the way to select option (value, index, text)
     * @param value is up to "by" param
     */
    public void selectOptionFromDropDownList(By locator, String by, String value) {
        var select = new Select(driver.findElement(locator));
        switch (by.toUpperCase(Locale.ROOT)) {
            case "VALUE":
                select.selectByValue(value);
                break;
            case "INDEX":
                select.selectByIndex(Integer.parseInt(value));
                break;
            case "TEXT":
                select.selectByVisibleText(value);
                break;
        }
    }

    public String getHtmlValidationMessage(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getAttribute("validationMessage");
    }

    public String getElementText(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
    }

    public String getElementAttributeValue(By locator, String attribute) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getAttribute(attribute);
    }

    public void hoverToElement(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator)).build().perform();
    }

    /**
     * Hover to "hoverElement" and click on the "clickElement" inside
     * @param hoverElement hover to this element
     * @param clickElement click on element
     */
    public void hoverAndClick(By hoverElement, By clickElement) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(hoverElement))
                .click(driver.findElement(clickElement))
                .build()
                .perform();
    }

    public void waitForPageLoaded() {
        wait.withTimeout(Duration.ofSeconds(30)).until(driver1 ->
                ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
    }
}
