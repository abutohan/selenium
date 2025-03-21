package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AddRemoveElementsPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(xpath = "//button[contains(text(), \"Add Element\")]")
    @CacheLookup
    private WebElement btnAddElement;

    private final By btnAddedElementLoc = By.xpath("//button[contains(text(), \"Delete\")]");

    public AddRemoveElementsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return getWait().until(ExpectedConditions.visibilityOf(headerTitle)).getText();
    }

    public void clickBtnAddElement(int n) {
        for (int i = 0; i < n; i++) {
            getWait().until(ExpectedConditions.elementToBeClickable(btnAddElement)).click();
        }
    }
    
    public int getAddedElementsCount() {
//        getWait().until(ExpectedConditions.visibilityOfElementLocated(btnAddedElementLoc));
        List<WebElement> btnAddElements = getDriver().findElements(btnAddedElementLoc);
        return btnAddElements.size();
    }

    public void clickBtnDeleteElement(int n) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(btnAddedElementLoc));
        List<WebElement> btnAddElements = getDriver().findElements(btnAddedElementLoc);
        for(WebElement element: btnAddElements){
            element.click();
        }
    }
}
