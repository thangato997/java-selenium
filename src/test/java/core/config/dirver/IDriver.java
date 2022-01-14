package core.config.dirver;

import org.openqa.selenium.WebDriver;

public interface IDriver {
    WebDriver createDriver();
    void setDriverOptions(Object options);
}
