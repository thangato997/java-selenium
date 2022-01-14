package core.helper;

import lombok.Getter;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.Objects;

/**
 * Controls Environment, Retries, and headless
 */
public class TestController implements IRetryAnalyzer {
    @Getter
    private static final String TEST_ENV = PropertiesReader.getPropName("testEnv");
    @Getter
    private static final Boolean HEADLESS = Objects.equals(PropertiesReader.getPropName("headless"), "true");
    private static final int MAX_RETRIES = Integer.parseInt(PropertiesReader.getPropName("mexRetries"));
    @Getter
    private static int retryCount = 0;
    private final boolean doRetries = Objects.equals(PropertiesReader.getPropName("doRetries"), "true");

    public boolean retry(ITestResult result) {
        // Set to true to do retries - Turn this off only for debugging
        if ((retryCount < MAX_RETRIES) && doRetries) {
            retryCount++;
            Reporter.log(getTestInfo() + "Retry #" + retryCount + ", on thread: " + Thread.currentThread().getName());
            return true;
        }
        return false;
    }
    private static String getTestInfo() {
        return Reporter.getCurrentTestResult().getTestClass().getName() + " - ";
    }
}