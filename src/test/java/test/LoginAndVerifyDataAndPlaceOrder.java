package test;

import core.base.BaseTest;
import core.helper.PropertiesReader;
import core.helper.TestController;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.data.AccountData;

public class LoginAndVerifyDataAndPlaceOrder extends BaseTest {

    @Test(description = "- Test case 1", dataProvider = "Account Data",
            dataProviderClass = AccountData.class, retryAnalyzer = TestController.class)
    void loginAndPlaceOrder(String username, String password, String productId) throws InterruptedException {
        loginPage.goToUrl(PropertiesReader.getPropName("baseUrl"));
        // Enter username and password
        productPage = loginPage.loginAndGotoProductPage(username, password);
        // View product from Json file
        productDetailPage = productPage.viewProductDetail(productId);
        productDetailPage.addToCart();
        // View cart page
        cartPage = productDetailPage.goToCartDetail();
        checkoutStep1Page = cartPage.goToCheckoutPage();
        checkoutStep2Page = checkoutStep1Page.fillInfoAndContinue("Take", "Hong", "70000");
        checkoutCompletePage = checkoutStep2Page.finishCheckout();
        String expectedResult = PropertiesReader.getPropName("orderConfirmExpected");
        String actualResult = this.driver.findElement(checkoutCompletePage.getLblSuccessPlaceOrder()).getText();
        checkoutCompletePage.verifyOrderSuccessfully(expectedResult);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(description = "- Test case 2", enabled = false, retryAnalyzer = TestController.class)
    void checkLockedUser() {
        loginPage.goToUrl(PropertiesReader.getPropName("baseUrl"));
        loginPage.loginAndVerifyError(PropertiesReader.getPropName("lockedUser"),
                PropertiesReader.getPropName("password"));
        Assert.assertTrue(loginPage.checkIsDisplayed(loginPage.getLblError()));
    }

    @Test(description = "- Test case 3", enabled = false,  retryAnalyzer = TestController.class)
    void checkAllImageIsDisplayOrNot() throws InterruptedException {
        loginPage.goToUrl(PropertiesReader.getPropName("baseUrl"));
        productPage = loginPage.loginAndGotoProductPage(PropertiesReader.getPropName("problemUser"),
                PropertiesReader.getPropName("password"));
        String brokenText = PropertiesReader.getPropName("brokenText");
        productPage.checkIfAllImageIsBroken(brokenText);
        Assert.assertTrue(productPage.verifyLinkImagesAreBroken(brokenText));
    }
}
