package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DragAndDropPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(id = "column-a")
    @CacheLookup
    private WebElement boxA;

    @FindBy(id = "column-b")
    @CacheLookup
    private WebElement boxB;

    private final By boxHeader = By.tagName("header");

    public DragAndDropPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return headerTitle.getText();
    }

    public void dragAndDropElement(WebElement sourceElement, WebElement destinationElement) {
        Actions actions = new Actions(getDriver());
        actions.clickAndHold(sourceElement).moveToElement(destinationElement).release().build().perform();
    }

    public List<WebElement> getBoxHeader() {
        return getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(boxHeader));
    }

    public void dragAndDrop(String scenario) {
        switch (scenario) {
            case "scenario 1":
                dragAndDropElement(boxA, boxB);
                break;
            case "scenario 2":
                dragAndDropElement(boxB, boxA);
                break;
            default:
        }
    }
}
