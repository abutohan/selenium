package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        return headerTitle.getText();
    }

    public void setUsername(String username) {
        txtUserName.sendKeys(username);
    }

    public void setPassword(String password) {
        txtPassword.sendKeys(password);
    }

    public SecureAreaPage clickLoginButton() {
        btnLogin.click();
        return new SecureAreaPage(getDriver());
    }
}
