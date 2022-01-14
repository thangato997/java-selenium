package pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import core.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class CheckoutCompletePage extends BasePage {

    private final By lblSuccessPlaceOrder = By.cssSelector("h2[class = 'complete-header']");

    public CheckoutCompletePage(WebDriver driver, ExtentTest test) {
        super(driver, test);
    }

    public void verifyOrderSuccessfully(String expected) {
        this.wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/checkout-complete.html"));
        if (this.driver.findElement(lblSuccessPlaceOrder).getText().equals(expected)) {
            test.log(Status.PASS, "TC 1 - Verify place order PASSED");
        } else {
            test.log(Status.FAIL, "TC 1 - Verify place order FAILED");
        }
    }
}
