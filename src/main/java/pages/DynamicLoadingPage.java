package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DynamicLoadingPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(partialLinkText = "Example 1")
    @CacheLookup
    private WebElement linkExample1;

    @FindBy(partialLinkText = "Example 2")
    @CacheLookup
    private WebElement linkExample2;

    public DynamicLoadingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle(){
        return headerTitle.getText();
    }

    public DynamicLoadingExample1Page exampleOne() {
        linkExample1.click();
        return new DynamicLoadingExample1Page(getDriver());
    }

    public DynamicLoadingExample2Page exampleTwo() {
        linkExample2.click();
        return new DynamicLoadingExample2Page(getDriver());
    }

}
