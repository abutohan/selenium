package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        return headerTitle.getText();
    }

    public void enterText(String text) {
        inputField.sendKeys(text);
    }

    public String getResult() {
        return resultText.getText();
    }

}
