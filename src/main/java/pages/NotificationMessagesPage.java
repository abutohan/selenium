package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NotificationMessagesPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    private final By alertMessage = By.id("flash");

    private final By reload = By.linkText("Click here");

    public NotificationMessagesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return headerTitle.getText();
    }

    public boolean checkNotificationMessage(String message){
        String text = getWait().until(ExpectedConditions.visibilityOfElementLocated(alertMessage)).getText();
        while (!text.contains(message)){
            getWait().until(ExpectedConditions.visibilityOfElementLocated(reload)).click();
            text = getWait().until(ExpectedConditions.visibilityOfElementLocated(alertMessage)).getText();
        }
        return true;
    }

}
