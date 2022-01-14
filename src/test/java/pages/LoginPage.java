package pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import core.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Getter
public class LoginPage extends BasePage {

	private final By txtUsername = By.id("user-name");
	private final By txtPassword = By.id("password");
	private final By btnLogin = By.id("login-button");

	// Error message
	private final By lblError = By.cssSelector("h3[data-test =  'error']");

	public LoginPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}

	public ProductPage loginAndGotoProductPage(String username, String password) throws InterruptedException {
		findElementAndSendTexts(txtUsername, username);
		Thread.sleep(5000);
		findElementAndSendTexts(txtPassword, password);
		findElementPresenceAndClick(btnLogin);
		return new ProductPage(this.driver, this.test);
	}

	public void loginAndVerifyError(String username, String password) {
		findElementAndSendTexts(txtUsername, username);
		findElementAndSendTexts(txtPassword, password);
		findElementPresenceAndClick(btnLogin);
		if (this.driver.findElement(lblError).isDisplayed()) {
			test.log(Status.PASS, "TC 2 - Error message is shown");
		} else {
			test.log(Status.FAIL, "TC 2 - Error message isn't shown");
		}
	}

}
