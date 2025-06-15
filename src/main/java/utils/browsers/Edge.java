package utils.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static utils.Constants.DOWNLOAD_DIR_PROPERTY;
import static utils.ReadProperties.loadProperty;

public class Edge implements Browser {


    @Override
    public WebDriver getBrowser() throws IOException {
        // WebDriverManager (recommended) or System.setProperty for driver executable
        // If you are using WebDriverManager, you won't need the setProperty line.
        // For example: WebDriverManager.edgedriver().setup();
        // System.setProperty("webdriver.edge.driver", "src/test/resources/browserBinaries/msedgedriver.exe"); // If not using WebDriverManager

        return new EdgeDriver(getEdgeOptions());
    }

    /**
     * Configures and returns EdgeOptions for the Edge browser.
     * This includes setting up download preferences, logging, and other experimental options.
     *
     * @return An EdgeOptions object with desired configurations.
     * @throws IOException              If an I/O error occurs while resolving the download path.
     * @throws IllegalArgumentException If the 'download-dir' property is not found or is blank.
     */
    private EdgeOptions getEdgeOptions() throws IOException {
        EdgeOptions options = new EdgeOptions();

        // --- Preferences ---
        Map<String, Object> prefs = new HashMap<>();

        // Get download path from properties
        String downloadPath = loadProperty().getProperty(DOWNLOAD_DIR_PROPERTY);
        if (downloadPath == null || downloadPath.isBlank()) {
            throw new IllegalArgumentException(
                    String.format(loadProperty().getProperty(DOWNLOAD_DIR_PROPERTY)));
        }
        String absoluteDownloadPath = Paths.get(downloadPath).toAbsolutePath().toString();

        // Download preferences
        prefs.put("download.default_directory", absoluteDownloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safeBrowse.enabled", true); // Still applicable for Chromium-based browsers

        // Password manager preferences (also applicable for Chromium-based browsers)
        prefs.put("profile.password_manager_leak_detection", false);

        // --- Logging Preferences ---
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);

        // --- Apply Options ---
        // Set logging capabilities
        options.setCapability("ms:edgeOptions", Map.of("prefs", prefs, "loggingPrefs", Map.of(LogType.BROWSER, Level.ALL.getName())));
        // For logging, EdgeOptions might need a slightly different approach or `setCapability` might be enough.
        // The above `setCapability` for "ms:edgeOptions" might encapsulate both prefs and logging.
        // options.setCapability("ms:edgeOptions", Map.of("loggingPrefs", logPrefs)); // Alternative if prefs are separate

        // Add experimental options (prefs should be under "ms:edgeOptions")
        options.setExperimentalOption("prefs", prefs); // This is how you'd typically set prefs for Edge/Chrome
        options.setCapability("ms:edgeOptions", Map.of("loggingPrefs", logPrefs)); // Logging prefs through capability

        // Additional Edge-specific arguments/options can be added here
        // options.addArguments("--inprivate"); // Edge's equivalent of incognito
        // options.addArguments("--start-maximized"); // Maximize window on start
        // options.addArguments("--headless"); // For running in headless mode

        return options;
    }

}
