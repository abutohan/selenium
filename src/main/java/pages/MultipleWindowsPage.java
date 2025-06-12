package pages;

import base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        clickHereLink.click();
        return new NewWindowPage(getDriver());
    }

    public NewWindowPage openANewWindowRightClick() {
        clickHereLink.sendKeys(Keys.CONTROL, Keys.RETURN);
        return new NewWindowPage(getDriver());
    }

}
