package pages;

import com.aventstack.extentreports.ExtentTest;
import core.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStep1Page extends BasePage {

	private final By txtFirstname = By.id("first-name");
	private final By txtLastname = By.id("last-name");
	private final By txtPostalCode = By.id("postal-code");
	private final By btnContinue = By.cssSelector("input[type = 'submit']");
	
	public CheckoutStep1Page(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}
	
	public CheckoutStep2Page fillInfoAndContinue(String firstName, String lastName, String codePostal) {
		findElementAndSendTexts(txtFirstname, firstName);
		findElementAndSendTexts(txtLastname, lastName);
		findElementAndSendTexts(txtPostalCode, codePostal);
		findElementVisibilityAndClick(btnContinue);
		return new CheckoutStep2Page(this.driver, this.test);
	}
}
