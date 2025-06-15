package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import static utils.Constants.CONFIG_PROPERTIES;

public class ReadProperties {

    /**
     * Loads properties from the specified configuration file.
     * This method assumes the configuration file is located in the classpath.
     *
     * @return A Properties object containing the loaded properties.
     * @throws IOException           If an I/O error occurs while reading the properties file.
     * @throws IllegalStateException If the configuration file cannot be found in the classpath.
     */

    public static Properties loadProperty() throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = Optional.ofNullable(ReadProperties.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES))
                .orElseThrow(() -> new IllegalStateException("Configuration file '" + CONFIG_PROPERTIES + "' not found in classpath."))) {
            properties.load(inputStream);
        }
        return properties;
    }

}
