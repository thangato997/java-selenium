package core.base;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtilities extends BasePage{
    protected TestUtilities(WebDriver driver, ExtentTest test) {
        super(driver, test);
    }

    public void takesScreenShot(String fileName) {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + getTodayDate()
                                                     + File.separator + getSystemTime() + " " + fileName + ".png";
        try {
            FileUtils.copyFile(srcFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String getTodayDate() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    private static String getSystemTime() {
        return new SimpleDateFormat("HHmmssSS").format(new Date());
    }
}
