package core.config.dirver;

import core.helper.TestController;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeService implements IDriver{
    private ChromeOptions chromeOptions;

    /**
     *  Set up chrome
     * @return ChromeOptions
     */
    private ChromeOptions getChromeOptions() {
        if(chromeOptions == null) {
            chromeOptions = new ChromeOptions();
            chromeOptions.setHeadless(TestController.getHEADLESS());
            chromeOptions.addArguments("incognito");
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.setAcceptInsecureCerts(true);
            chromeOptions.setCapability("setJavascriptEnabled", true);
        }
        return chromeOptions;
    }

    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(getChromeOptions());
    }

    @Override
    public void setDriverOptions(Object options) {
        chromeOptions = (ChromeOptions) options;
    }
}
