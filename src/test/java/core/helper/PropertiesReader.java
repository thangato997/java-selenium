package core.helper;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {
    public static String getPropName(String propNames) {
        String value = "";
        try (FileInputStream input = new FileInputStream("./config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            // get the property value and print it out
            value = prop.getProperty(propNames);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return value;
    }
}
