package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import utils.CustomEventListener;
import utils.ExtentReportManager;
import utils.WindowManager;
import utils.browsers.BrowserGetter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.Constants.REPORT_FILE_NAME;
import static utils.Constants.TIMESTAMP_FORMAT;
import static utils.Screenshot.captureScreenshot;


public class BaseTest {

    protected final static BrowserGetter browserGetter = new BrowserGetter();

    protected static WebDriver driver;
    protected static ExtentReportManager extentReportManager;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected static String screenshotFolder;
    protected HomePage homePage;

    @BeforeSuite(alwaysRun = true)
    @Parameters("browser")
    public void setUpReporter(@Optional("chrome") String browser) throws IOException {
        String timestamp = new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date());
        screenshotFolder = REPORT_FILE_NAME + " - " + browser + " - " + timestamp;
        extentReportManager = new ExtentReportManager();
        extentReportManager.setUpReporter(timestamp, browser);
        extent = extentReportManager.getExtentReports();
    }

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser", "headless"})
    public void setUpBrowser(@Optional("chrome") String browser, @Optional("false") String headless) throws IOException {

        WebDriver originalDriver = browserGetter.getBrowser(browser, headless);

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
