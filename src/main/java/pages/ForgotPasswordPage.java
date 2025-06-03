package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ForgotPasswordPage extends BasePage {

    @FindBy(tagName = "h2")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(id = "email")
    @CacheLookup
    private WebElement emailField;

    @FindBy(id = "form_submit")
    @CacheLookup
    private WebElement retrievePasswordButton;

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return getWait().until(ExpectedConditions.visibilityOf(headerTitle)).getText();
    }

    public void enterEmail(String email) {
        getWait().until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
    }

    public EmailSentPage clickRetrievePassword() {
        getWait().until(ExpectedConditions.elementToBeClickable(retrievePasswordButton)).click();
        return new EmailSentPage(getDriver());
    }

    public EmailSentPage retrievePassword(String email) {
        enterEmail(email);
        return clickRetrievePassword();
    }
}
