package pages;

import com.aventstack.extentreports.ExtentTest;
import core.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Getter
public class CheckoutStep2Page extends BasePage {
	private final By btnFinish = By.cssSelector("button#finish");
	
	public CheckoutStep2Page(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}
	
	public CheckoutCompletePage finishCheckout() {
		findElementVisibilityAndClick(btnFinish);
		return new CheckoutCompletePage(this.driver, this.test);
	}
}
