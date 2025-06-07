package pages;

import base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

public class MultipleWindowsPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(linkText = "Click Here")
    @CacheLookup
    private WebElement clickHereLink;

    public MultipleWindowsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return headerTitle.getText();
    }

    public NewWindowPage openANewWindow() {
//        String originalWindowHandler = getDriver().getWindowHandle();
//        Set<String> oldWindowHandles = getDriver().getWindowHandles();
        clickHereLink.click();
//        getWait().until(ExpectedConditions.numberOfWindowsToBe(oldWindowHandles.size() + 1));
        return new NewWindowPage(getDriver());
    }

    public NewWindowPage openANewWindowRightClick() {
//        String originalWindowHandler = getDriver().getWindowHandle();
//        Set<String> oldWindowHandles = getDriver().getWindowHandles();
        clickHereLink.sendKeys(Keys.CONTROL, Keys.RETURN);
//        getWait().until(ExpectedConditions.numberOfWindowsToBe(oldWindowHandles.size() + 1));
        return new NewWindowPage(getDriver());
    }
}
