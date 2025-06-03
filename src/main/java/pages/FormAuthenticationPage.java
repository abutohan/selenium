package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FormAuthenticationPage extends BasePage {

    @FindBy(tagName = "h2")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(id = "username")
    @CacheLookup
    private WebElement txtUserName;

    @FindBy(id = "password")
    @CacheLookup
    private WebElement txtPassword;

    @FindBy(css = "#login button")
    @CacheLookup
    private WebElement btnLogin;

    public FormAuthenticationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return getWait().until(ExpectedConditions.visibilityOf(headerTitle)).getText();
    }

    public void setUsername(String username) {
        getWait().until(ExpectedConditions.visibilityOf(txtUserName)).sendKeys(username);
    }

    public void setPassword(String password) {
        getWait().until(ExpectedConditions.visibilityOf(txtPassword)).sendKeys(password);
    }

    public SecureAreaPage clickLoginButton() {
        getWait().until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
        return new SecureAreaPage(getDriver());
    }
}
