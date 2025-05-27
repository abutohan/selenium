package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContextMenuPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(id = "hot-spot")
    @CacheLookup
    private WebElement box;

    public ContextMenuPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return getWait().until(ExpectedConditions.visibilityOf(headerTitle)).getText();
    }

    public void rightClickTheBox() {
        Actions action = new Actions(getDriver());
        action.contextClick(box).perform();
    }

    public String getPopUpText() {
        return getDriver().switchTo().alert().getText();
    }

    public void acceptPopUp() {
        getDriver().switchTo().alert().accept();
    }
}
