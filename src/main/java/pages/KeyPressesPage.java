package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class KeyPressesPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(id = "target")
    @CacheLookup
    private WebElement inputField;

    @FindBy(id = "result")
    @CacheLookup
    private WebElement resultText;

    public KeyPressesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return getWait().until(ExpectedConditions.visibilityOf(headerTitle)).getText();
    }

    public void enterText(String text) {
        getWait().until(ExpectedConditions.visibilityOf(inputField)).sendKeys(text);
    }

    public String getResult() {
        return getWait().until(ExpectedConditions.visibilityOf(resultText)).getText();
    }
}
