package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.Constants.TIMEOUT;

public abstract class BasePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this. driver, Duration.ofSeconds(TIMEOUT));
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public WebDriverWait getWait(){
        return wait;
    }
}