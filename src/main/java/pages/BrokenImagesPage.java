package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BrokenImagesPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    private final By imgLoc = By.cssSelector("div.example img");


    public BrokenImagesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return getWait().until(ExpectedConditions.visibilityOf(headerTitle)).getText();
    }

    public int countBrokenImages() {
        List<WebElement> imgElements = getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(imgLoc));
        return (int) imgElements.stream().filter(img -> "0".equals(img.getAttribute("naturalWidth"))).count();
    }
}
