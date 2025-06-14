package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import utils.CustomEventListener;
import utils.WindowManager;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.Constants.BASE_URL;
import static utils.ReadProperties.loadProperty;
import static utils.Screenshot.captureScreenshot;


public class BaseTest {

    protected HomePage homePage;
    protected WebDriver driver;

    protected static ExtentSparkReporter htmlReporter;
    protected static ExtentReports extent;
    protected static ExtentTest test;


    protected static String parentFolder;


    @BeforeSuite
    public void setUpReporter() throws IOException {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportFileName = "Selenium_Automation_Report_" + timestamp + ".html";

        parentFolder = "Selenium_Automation_Report_" + timestamp;

        String reportPath = Paths.get(loadProperty().getProperty("report-dir")).toAbsolutePath().toString() + "\\" + reportFileName;

        htmlReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        htmlReporter.config().setOfflineMode(true);
        htmlReporter.config().setDocumentTitle("Selenium Automation Report");
        htmlReporter.config().setReportName("Test Execution Results");
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @BeforeClass
    public void setUp() throws IOException {


        WebDriver originalDriver = new ChromeDriver(getChromeOptions());
        System.out.println("Opening Browser");
        CustomEventListener customEventListener = new CustomEventListener();
        EventFiringDecorator<WebDriver> eventFiringDecorator = new EventFiringDecorator<>(customEventListener);
        driver = eventFiringDecorator.decorate(originalDriver);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void loadHomePage() {
        driver.get(BASE_URL);
        homePage = new HomePage(driver);
    }

    @AfterMethod

    public void tearDown(ITestResult result) throws IOException, InterruptedException {

        String testName = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName();

        Object t = result.getMethod();
        System.out.println(t);
        Pattern pattern = Pattern.compile("([^.]*)"); // Matches any character except a dot, zero or more times
        Matcher matcher = pattern.matcher(t.toString());
        String testFolder = null;

        if (matcher.find()) {
            testFolder = matcher.group(1);
//            System.out.println("Extracted String: " + extractedString); // Output: ForgotPasswordTest

        } else {
            System.out.println("No match found.");

        }
        String screenshotPath = captureScreenshot(parentFolder, testFolder, result.getName(), driver);

        String testData = Arrays.toString(result.getParameters());

        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, testName + " " + testData, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//            test.addScreenCaptureFromPath(screenshotPath, "Failed Screenshot");
            test.log(Status.INFO, result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, testName + " " + testData, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, testName + " " + testData);
        }

        Thread.sleep(1000);
// =======
//     public void tearDown(ITestResult result) throws IOException {

//         String testName = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName();
//         String testData = Arrays.toString(result.getParameters());

//         if (result.getStatus() == ITestResult.FAILURE) {
//             test.log(Status.FAIL, testName + " " + testData);
//             String screenshotPath = captureScreenshot(result.getName(), driver);
// //            test.addScreenCaptureFromPath(screenshotPath, "Failed Screenshot");
//             test.log(Status.INFO, result.getThrowable(),
//                     MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

//         } else if (result.getStatus() == ITestResult.SUCCESS) {
//             test.log(Status.PASS, testName + " " + testData);
//         } else if (result.getStatus() == ITestResult.SKIP) {
//             test.log(Status.SKIP, testName + " " + testData);
//         }
// >>>>>>> main
    }


    @AfterClass
    public void tearDown() {
        System.out.println("Closing Browser");

        driver.quit();
    }

    @AfterSuite
    public void tearDownReporter() {
        extent.flush();
    }

    public WindowManager getWindowManager() {
        return new WindowManager(driver);
    }

    private ChromeOptions getChromeOptions() throws IOException {
        ChromeOptions options = new ChromeOptions();

        //prefs
        Map<String, Object> prefs = new HashMap<>();
        String downloadPath = Paths.get(loadProperty().getProperty("download-dir")).toAbsolutePath().toString();
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
