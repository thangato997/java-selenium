package pages;

import com.aventstack.extentreports.ExtentTest;
import core.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Getter
public class ProductDetailPage extends BasePage {
	private final By btnAddToCart = By.cssSelector("button.btn_primary.btn_inventory");
	private final By btnCart = By.id("shopping_cart_container");
	
	public ProductDetailPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}
	
	public void addToCart() {
		findElementVisibilityAndClick(btnAddToCart);
	}
	
	public CartPage goToCartDetail() {
		findElementVisibilityAndClick(btnCart);
		return new CartPage(this.driver, this.test);
	}

}
