package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        return headerTitle.getText();
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public EmailSentPage clickRetrievePassword() {
        retrievePasswordButton.click();
        return new EmailSentPage(getDriver());
    }

    public EmailSentPage retrievePassword(String email) {
        enterEmail(email);
        return clickRetrievePassword();
    }

}
