package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SecureAreaPage extends BasePage {

    @FindBy(id = "flash")
    @CacheLookup
    private WebElement statusAlert;

    public SecureAreaPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getAlertText() {
        return getWait().until(ExpectedConditions.visibilityOf(statusAlert)).getText();
    }
}
