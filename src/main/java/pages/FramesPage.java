package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FramesPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(linkText = "Nested Frames")
    @CacheLookup
    private WebElement nestedFrames;

    public FramesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return getWait().until(ExpectedConditions.visibilityOf(headerTitle)).getText();
    }

    public NestedFramesPage clickNestedFrames() {
        getWait().until(ExpectedConditions.visibilityOf(nestedFrames)).click();
        return new NestedFramesPage(getDriver());
    }
}
