package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JavaScriptAlertsPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(xpath = ".//button[text()='Click for JS Alert']")
    @CacheLookup
    private WebElement btnAlert;

    @FindBy(xpath = ".//button[text()='Click for JS Confirm']")
    @CacheLookup
    private WebElement btnConfirm;

    @FindBy(xpath = ".//button[text()='Click for JS Prompt']")
    @CacheLookup
    private WebElement btnPrompt;

    @FindBy(id = "result")
    @CacheLookup
    private WebElement lblResult;

    public JavaScriptAlertsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return headerTitle.getText();
    }

    public void clickAlert() {
        btnAlert.click();
    }

    public void clickConfirm() {
        btnConfirm.click();
    }

    public void clickPrompt() {
        btnPrompt.click();
    }

    public void clickAcceptAlert() {
        getDriver().switchTo().alert().accept();
    }

    public void clickDismissAlert() {
        getDriver().switchTo().alert().dismiss();
    }

    public String getAlertText() {
        return getDriver().switchTo().alert().getText();
    }

    public void setAlertText(String text) {
        getDriver().switchTo().alert().sendKeys(text);
    }

    public String getResultText() {
        return lblResult.getText();
    }

}
