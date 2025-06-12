package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EntryAdPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(xpath = "//p[contains(text(), \"Close\")]")
    @CacheLookup
    private WebElement closeModal;

    @FindBy(id = "restart-ad")
    @CacheLookup
    private WebElement restart;

    private final By modal = By.className("modal");

    public EntryAdPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return headerTitle.getText();
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
        closeModal.click();
    }

    public void clickRestart() {
        restart.click();
    }

}
