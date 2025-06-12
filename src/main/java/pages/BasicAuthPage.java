package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static utils.Constants.BASE_URL;
import static utils.SetupAuth.setupAuthentication;

public class BasicAuthPage extends BasePage {

    private final By headerTitle = By.tagName("h3");

    public BasicAuthPage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderTitle(String username, String password) {
        setupAuthentication(getDriver(), BASE_URL, username, password);
        getDriver().get(String.format("%s/basic_auth", BASE_URL));
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(headerTitle)).getText();
    }

}
