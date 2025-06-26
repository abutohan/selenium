package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import pages.HomePage;
import utils.CustomEventListener;
import utils.ExtentReportManager;
import utils.WindowManager;
import utils.browsers.BrowserGetter;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.Constants.*;
import static utils.ReadProperties.loadProperty;
import static utils.Screenshot.captureScreenshot;


public class BaseTest {

    //    protected final static Chrome chrome = new Chrome();
    protected final static BrowserGetter browserGetter = new BrowserGetter();
//    protected WebDriver driver;
    protected static WebDriver driver;
    protected static ExtentReportManager extentReportManager;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected static String screenshotFolder;
    protected HomePage homePage;

    @BeforeSuite(alwaysRun = true)
    public void setUpReporter() throws IOException {
        String timestamp = new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date());
        screenshotFolder = REPORT_FILE_NAME + timestamp;
        extentReportManager = new ExtentReportManager();
        extentReportManager.setUpReporter(timestamp);
        extent = extentReportManager.getExtentReports();
    }

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setUpBrowser(String browser) throws IOException {

//        WebDriver originalDriver = new ChromeDriver(getChromeOptions());

        WebDriver originalDriver = browserGetter.getBrowser(browser);

        System.out.println("Opening Browser");
        CustomEventListener customEventListener = new CustomEventListener();
        EventFiringDecorator<WebDriver> eventFiringDecorator = new EventFiringDecorator<>(customEventListener);
        driver = eventFiringDecorator.decorate(originalDriver);
        driver.manage().window().maximize();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("url")
    public void loadHomePage(@Optional("https://the-internet.herokuapp.com/") String url) {
        driver.get(url);
        homePage = new HomePage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void screenCapture(ITestResult result) throws IOException, InterruptedException {
        String testName = Objects.requireNonNull(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class)).testName();

        String testMethod = result.getMethod().toString();
        Pattern pattern = Pattern.compile("([^.]*)");
        Matcher matcher = pattern.matcher(testMethod);
        String testFolder = null;

        if (matcher.find()) {
            testFolder = matcher.group(1);
        } else {
            testFolder = "testFolder";
        }

        String screenshot = captureScreenshot(screenshotFolder, testFolder, result.getName(), driver);

        Object[] testParameters = result.getParameters();
        String testData = testParameters.length > 0 ? testParameters[0].toString() : "";

        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, testName + " " + testData, MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
            test.log(Status.INFO, result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, testName + " " + testData, MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, testName + " " + testData);
        }

        Thread.sleep(1000);
    }


    @AfterClass(alwaysRun = true)
    public void tearDownBrowser() {
        System.out.println("Closing Browser");
        driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownReporter() {
        extent.flush();
    }

    public WindowManager getWindowManager() {
        return new WindowManager(driver);
    }

}
