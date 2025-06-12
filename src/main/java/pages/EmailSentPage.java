package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmailSentPage extends BasePage {
    @FindBy(id = "content")
    @CacheLookup
    private WebElement contentArea;

    public EmailSentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getMessage() {
        return contentArea.getText();
    }
}
