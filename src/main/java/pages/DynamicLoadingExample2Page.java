package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DynamicLoadingExample2Page extends BasePage {

    @FindBy(css = "#start button")
    @CacheLookup
    private WebElement startButton;

    @FindBy(id = "finish")
    @CacheLookup
    private WebElement loadedText;

    public DynamicLoadingExample2Page(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickStart() {
        getWait().until(ExpectedConditions.elementToBeClickable(startButton)).click();
    }

    public String getLoadedText() {
        return getWait().until(ExpectedConditions.visibilityOf(loadedText)).getText();
    }
}
