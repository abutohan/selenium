package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static utils.Constants.*;
import static utils.Messages.onPropertyNotFound;
import static utils.ReadProperties.loadProperty;

public class ExtentReportManager {

    private ExtentReports extent;

    public void setUpReporter(String timestamp, String browser) throws IOException {

        String reportFileName = REPORT_FILE_NAME + " - " + browser + " - " + timestamp + REPORT_FILE_EXTENSION;

        String relativeReportPath = loadProperty().getProperty(REPORT_DIR_PROPERTY);
        if (relativeReportPath == null || relativeReportPath.isBlank()) {
            throw new IllegalArgumentException(
                    String.format(onPropertyNotFound(REPORT_DIR_PROPERTY)));
        }

        Path reportPath = Paths.get(relativeReportPath);

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath.resolve(reportFileName).toString());
        this.extent = new ExtentReports();
        this.extent.attachReporter(htmlReporter);

        // Reporter configurations
        htmlReporter.config().setOfflineMode(true); // Ensures all resources are bundled
        htmlReporter.config().setDocumentTitle(REPORT_FILE_NAME); // Browser tab title
        htmlReporter.config().setReportName(REPORT_NAME); // Report header name
        htmlReporter.config().setTheme(Theme.DARK);
    }

    public ExtentReports getExtentReports() {
        if (this.extent == null) {
            throw new IllegalStateException("ExtentReports instance has not been initialized. Call setUpReporter() first.");
        }
        return extent;
    }


}
