package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

import static utils.Constants.BASE_URL;


public class BaseTest {

    protected HomePage homePage;
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("Opening Browser");
        driver = new ChromeDriver();
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
}
