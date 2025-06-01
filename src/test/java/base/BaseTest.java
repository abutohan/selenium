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

import static utils.Constants.BASE_URL;


public class BaseTest {

    protected HomePage homePage;
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
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

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        return options;
    }
}
