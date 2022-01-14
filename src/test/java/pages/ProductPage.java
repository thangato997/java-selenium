package pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import core.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

@Getter
public class ProductPage extends BasePage {
	private final By lstProductImg = By.cssSelector("div.inventory_item_img img");
	
	public ProductPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}

	public ProductDetailPage viewProductDetail(String productId) {
		findElementVisibilityAndClick(By.cssSelector(productId));
		return new ProductDetailPage(this.driver, this.test);
	}
	
	public boolean verifyLinkImagesAreBroken(String brokenText) {
		 boolean isBroken = true;
		// Create a list to get all the image string from the product list
		List<WebElement> listInventoryItems = driver.findElements(lstProductImg);

		// Loop the list to check if all the image
		for (WebElement i : listInventoryItems) {
			 isBroken = i.getAttribute("src").contains(brokenText);
		}
		return isBroken;
	}
	
	public void checkIfAllImageIsBroken(String brokenText) {
		if (verifyLinkImagesAreBroken(brokenText)) {
			test.log(Status.PASS, "TC 3 - All image are broken");
		} else {
			test.log(Status.FAIL, "TC 3 - Not all image are broken");
		}
		Assert.assertTrue(verifyLinkImagesAreBroken(brokenText));
	}
}
