package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static utils.ReadProperties.loadProperty;

public class Screenshot {

    public static String captureScreenshot(String screenshotName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String screenshotPath = Paths.get(loadProperty().getProperty("screenshot-dir")).toAbsolutePath().toString();
        String destinationPath = screenshotPath + "\\" + screenshotName + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".png";
        Path destination = Paths.get(destinationPath);
        Files.copy(source.toPath(), destination);
        return destinationPath;
    }

}
