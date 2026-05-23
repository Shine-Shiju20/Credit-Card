package utils.Loan;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;

    public ConfigReader() throws IOException {
        prop = new Properties();

        FileInputStream fis = new FileInputStream(
                "src/test/java/resources/config/API_CONFIG.prop"
        );

        prop.load(fis);
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}