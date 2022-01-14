package core.config.dirver;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    /**
     * @param browser is the browser to run the test script
     * @return the browser name
     * @throws Exception is threw if the browser is empty
     */
    public static WebDriver getDriver(String browser) throws Exception {
        switch (browser.toUpperCase()) {
            case "CHROME":
                return new ChromeService().createDriver();
            case "FIREFOX":
                return null;
            default:
                throw new Exception("No Driver found");
        }
    }
}
