package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import utils.CustomEventListener;
import utils.WindowManager;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static utils.Constants.BASE_URL;
import static utils.ReadProperties.loadProperty;


public class BaseTest {

    protected HomePage homePage;
    protected WebDriver driver;

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

    @AfterClass
    public void tearDown() {
        System.out.println("Closing Browser");
        driver.quit();
    }

    public WindowManager getWindowManager() {
        return new WindowManager(driver);
    }

    private ChromeOptions getChromeOptions() throws IOException {
        ChromeOptions options = new ChromeOptions();

        //prefs
        Map<String, Object> prefs = new HashMap<>();
        String downloadPath = Paths.get(loadProperty().getProperty("download-dir")).toAbsolutePath().toString();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);

        //options
        //options.addArguments("--incognito");
        options.setExperimentalOption("prefs", prefs);

        return options;
    }
}
