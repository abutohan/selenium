package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static utils.Constants.CONFIG_PROPERTIES;

public class ReadProperties {

    public static Properties loadProperty() throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = ReadProperties.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)) {
            properties.load(inputStream);
        }
        return properties;
    }
}
