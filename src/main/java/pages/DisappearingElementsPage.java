package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DisappearingElementsPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    public DisappearingElementsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle(){
        return getWait().until(ExpectedConditions.visibilityOf(headerTitle)).getText();
    }

    public void chekPresenceOfElementLink(String elementLink){
        try {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText(elementLink)));
            System.out.println(elementLink + " is present");
        } catch (Exception e){
            System.out.println(elementLink + " is not present");
        }
    }
}
