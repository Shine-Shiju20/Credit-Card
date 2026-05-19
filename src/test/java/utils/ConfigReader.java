package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

public class ConfigReader {
    private static final Logger logger = LoggerUtility.getLogger(ConfigReader.class);

    private  final Properties properties;

    //constructor (initialize Properties object)
    public ConfigReader() throws IOException {
        FileInputStream fs =
                new FileInputStream(
                        "src/test/resources/config.properties"
                        //"C:/Users/abhiram.x1/Desktop/Testing-Bank-scripts/Banking_Scripts/src/test/resources/config.properties"
                );
        this.properties = new Properties();
        this.properties.load(fs);
        logger.info("Config properties loaded successfully");
    }

    //fetch and return value from config File
    public String getProp(String key){
        return this.properties.getProperty(key);
    }

}
