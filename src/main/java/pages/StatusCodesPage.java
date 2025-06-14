package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StatusCodesPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    public StatusCodesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return headerTitle.getText();
    }

    public StatusCodePage getStatusCodePage(String statusCodeLink){
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText(statusCodeLink))).click();
        return new StatusCodePage(getDriver());
    }

}
