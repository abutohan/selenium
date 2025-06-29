package utils.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static utils.Constants.DOWNLOAD_DIR_PROPERTY;
import static utils.Messages.onPropertyNotFound;
import static utils.ReadProperties.loadProperty;

public class Chrome implements Browser {

    private final String headless;

    public Chrome(String headless) {
        this.headless = headless;
    }

    @Override
    public WebDriver getBrowser() throws IOException {
        return new ChromeDriver(getBrowserOptions());
    }

    /**
     * Configures and returns ChromeOptions for the Chrome browser.
     * This includes setting up download preferences, logging, and other experimental options.
     *
     * @return An ChromeOptions object with desired configurations.
     * @throws IOException              If an I/O error occurs while resolving the download path.
     * @throws IllegalArgumentException If the 'download-dir' property is not found or is blank.
     */

    private ChromeOptions getBrowserOptions() throws IOException {
        ChromeOptions options = new ChromeOptions();

        // --- Preferences ---
        Map<String, Object> prefs = new HashMap<>();

        // Get download path from properties
        String downloadPath = loadProperty().getProperty(DOWNLOAD_DIR_PROPERTY);
        if (downloadPath == null || downloadPath.isBlank()) {
            throw new IllegalArgumentException(
                    String.format(onPropertyNotFound(DOWNLOAD_DIR_PROPERTY)));
        }
        String absoluteDownloadPath = Paths.get(downloadPath).toAbsolutePath().toString();

        // Download preferences
        prefs.put("download.default_directory", absoluteDownloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);

        // Password manager preferences
        prefs.put("profile.password_manager_leak_detection", false);

        // --- Logging Preferences ---
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);

        // --- Apply Options ---
        // Set logging capabilities
        options.setCapability("goog:chromeOptions", Map.of("prefs", prefs, "loggingPrefs", Map.of(LogType.BROWSER, Level.ALL.getName())));
        options.setExperimentalOption("prefs", prefs);
        options.setCapability("goog:chromeOptions", Map.of("loggingPrefs", logPrefs));

        // Additional Edge-specific arguments/options can be added here
        //options.addArguments("--incognito");
        if (headless.equals("true")) options.addArguments("--headless"); // For running in headless mode

        return options;
    }
    
}

