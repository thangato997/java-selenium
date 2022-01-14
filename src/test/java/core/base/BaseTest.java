package core.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import core.config.dirver.DriverManager;
import core.helper.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.*;


public class BaseTest {
    protected WebDriver driver;
    protected static ExtentTest test;
    protected static ExtentReports report;
    protected static ExtentSparkReporter spark;

    // declare all the pages here
    protected LoginPage loginPage;
    protected CartPage cartPage;
    protected CheckoutStep1Page checkoutStep1Page;
    protected CheckoutStep2Page checkoutStep2Page;
    protected ProductDetailPage productDetailPage;
    protected ProductPage productPage;
    protected CheckoutCompletePage checkoutCompletePage;

    /**
     * The method to generate the report when any test class runs
     */
    @BeforeClass
    protected void startReport() {
        report = new ExtentReports();
        spark = new ExtentSparkReporter("reports/TestCaseReportResults.html");
        report.attachReporter(spark);
        test = report.createTest("My test");
    }

    /**
     * When any test class is finished, the test result is written to the html file
     */
    @AfterClass
    protected void endTest() {
        report.flush();
    }

    /**
     * Generate the browser and the first page
     * @throws Exception is thrown when the test method is finished
     */
    @BeforeMethod
    protected void setUp() throws Exception {
        driver = DriverManager.getDriver(PropertiesReader.getPropName("browser"));
        // the first page have to declare here
        loginPage = new LoginPage(driver, test);
    }

    /**
     * Set test result after the test method is finished
     * @param result to specify the testResult
     */
    @AfterMethod
    protected void tearDown(ITestResult result) {
        String testResult;
        if(result.getStatus() == ITestResult.FAILURE) {
            testResult = "Test Fail: " + result.getName();
            test.fail(MarkupHelper.createLabel(testResult, ExtentColor.RED));
            System.out.println(testResult);
            test.log(Status.FAIL,"Details of Fail Testcase: " + result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SKIP) {
            testResult = "Test Skip: " + result.getName();
            test.skip(MarkupHelper.createLabel(testResult, ExtentColor.YELLOW));
            System.out.println(testResult);
            test.log(Status.SKIP, "Details of Skip Testcase" + result.getThrowable());
         }
        else {
            testResult = "Test Pass: " + result.getName();
            test.pass(MarkupHelper.createLabel(testResult, ExtentColor.GREEN));
            System.out.println(testResult);
            test.log(Status.PASS, "Details passed");
        }
        test.log(Status.INFO, "=================*-*=================");
        driver.quit();
    }
}
