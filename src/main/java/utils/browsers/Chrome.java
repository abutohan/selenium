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
import static utils.ReadProperties.loadProperty;

public class Chrome implements Browser {

    @Override
    public WebDriver getBrowser() throws IOException {
//        setProperty("webdriver.chrome.driver", "src/test/resources/browserBinaries/chromedriver.exe");
        //        driver.manage().window().maximize();
        return new ChromeDriver(getBrowserOptions());
    }

    private ChromeOptions getBrowserOptions() throws IOException {
        ChromeOptions options = new ChromeOptions();

        //prefs
        Map<String, Object> prefs = new HashMap<>();
        String downloadPath = Paths.get(loadProperty().getProperty(DOWNLOAD_DIR_PROPERTY)).toAbsolutePath().toString();

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);

        //download prefs
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);
        //change-password prefs
        prefs.put("profile.password_manager_leak_detection", false);

        //options
        //options.addArguments("--incognito");
        options.setCapability("goog:loggingPrefs", logPrefs);
        options.setExperimentalOption("prefs", prefs);

        return options;
    }
}
