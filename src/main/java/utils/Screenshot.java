package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static utils.Constants.*;
import static utils.Messages.onPropertyNotFound;
import static utils.ReadProperties.loadProperty;

public class Screenshot {

    /**
     * Captures a screenshot and saves it to a specified location.
     * The base screenshot directory is read from the loaded properties.
     *
     * @param parentFolder   The name of the parent folder within the base screenshot directory.
     * @param testFolder     The name of the test-specific folder within the parent folder.
     * @param screenshotName The base name for the screenshot file (e.g., "login_failure").
     * @param driver         The WebDriver instance to use for capturing screenshots.
     * @return               The absolute path to the saved screenshot file.
     * @throws IOException              If an I/O error occurs during screenshot capture or saving.
     * @throws IllegalArgumentException If any of the folder names or screenshot name are blank,
     *                                  or if the 'screenshot-dir' property is not found or is blank.
     */

    public static String captureScreenshot(String parentFolder, String testFolder, String screenshotName, WebDriver driver) throws IOException {

        String relativeScreenshotPath = loadProperty().getProperty(SCREENSHOT_DIR_PROPERTY);
        if (relativeScreenshotPath == null || relativeScreenshotPath.isBlank())
            throw new IllegalArgumentException(onPropertyNotFound(SCREENSHOT_DIR_PROPERTY));

        Path screenshotBaseDir = Paths.get(relativeScreenshotPath);
        Path targetDirectory = screenshotBaseDir.resolve(parentFolder).resolve(testFolder);

        if (!Files.exists(targetDirectory)) {
            Files.createDirectories(targetDirectory);
        }

        String timeStamp = new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date());

        TakesScreenshot ts = (TakesScreenshot) driver;
        Path sourceFile = ts.getScreenshotAs(OutputType.FILE).toPath();
        String fileName = screenshotName + " - " + timeStamp + SCREENSHOT_FILE_EXTENSION;
        Path destination = targetDirectory.resolve(fileName);
        Files.copy(sourceFile, destination);

        return destination.toAbsolutePath().toString();
    }

}
