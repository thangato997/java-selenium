package pages;

import com.aventstack.extentreports.ExtentTest;
import core.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Getter
public class CartPage extends BasePage {

	private final By btnCheckout = By.cssSelector("button#checkout");
	
	public CartPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}
	
	public CheckoutStep1Page goToCheckoutPage() {
		findElementVisibilityAndClick(btnCheckout);
		return new CheckoutStep1Page(this.driver, this.test);
	}
}
