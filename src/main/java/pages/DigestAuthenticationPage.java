package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static utils.Constants.BASE_URL;
import static utils.SetupAuth.setupAuthentication;

public class DigestAuthenticationPage extends BasePage {

    private final By headerTitle = By.tagName("h3");

    public DigestAuthenticationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle(String username, String password) {
        setupAuthentication(getDriver(), BASE_URL, username, password);
        getDriver().get(String.format("%s/digest_auth", BASE_URL));
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(headerTitle)).getText();
    }

}
