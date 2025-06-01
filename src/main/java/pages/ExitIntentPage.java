package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;

public class ExitIntentPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(xpath = "//p[contains(text(), \"Close\")]")
    @CacheLookup
    private WebElement closeModal;

    private final By modal = By.className("modal");

    public ExitIntentPage(WebDriver diver) {
        super(diver);
        PageFactory.initElements(diver, this);
    }

    public String getHeaderTitle() {
        return getWait().until(ExpectedConditions.visibilityOf(headerTitle)).getText();
    }


    public boolean checkModalPresence() {
        try {
            getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(modal));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCloseModal() {
        getWait().until(ExpectedConditions.visibilityOf(closeModal)).click();
    }

    public void moveMousePosition(int xPos, int yPos) throws AWTException {
        Robot robot = new Robot();
        robot.mouseMove(xPos, yPos);
    }
}
