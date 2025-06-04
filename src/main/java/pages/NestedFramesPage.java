package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NestedFramesPage extends BasePage {

    @FindBy(tagName = "body")
    @CacheLookup
    private WebElement body;

    public NestedFramesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getFrameText(String parentFrameName, String childFrameName) {
        try {
            getDriver().switchTo().frame(parentFrameName);
            if (!childFrameName.equals("N/A")) {
                getDriver().switchTo().frame(childFrameName);
            }
            return getWait().until(ExpectedConditions.visibilityOf(body)).getText();
        } finally {
            getDriver().switchTo().parentFrame();
            if (!childFrameName.equals("N/A")) {
                getDriver().switchTo().parentFrame();
            }
        }
    }
}
