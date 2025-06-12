package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DynamicControlsPage extends BasePage {

    @FindBy(tagName = "h4")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(xpath = "//input[@type=\"text\"]")
    @CacheLookup
    private WebElement textInput;

    @FindBy(xpath = "//button[contains(text(),\"Enable\")]")
    @CacheLookup
    private WebElement enableDisableBtn;

    private final By notifLabel = By.id("message");

    public DynamicControlsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return headerTitle.getText();
    }

    public void setTextInput(String message) {
        clickEnableButton();
        getWait().until(ExpectedConditions.textToBePresentInElementLocated(notifLabel, "enabled"));
        textInput.sendKeys(message);
        clickEnableButton();
        getWait().until(ExpectedConditions.textToBePresentInElementLocated(notifLabel, "disabled"));
    }

    private void clickEnableButton() {
        enableDisableBtn.click();
    }

}
