package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DynamicLoadingExample1Page extends BasePage {

    @FindBy(css = "#start button")
    @CacheLookup
    private WebElement startButton;

    @FindBy(id = "finish")
    @CacheLookup
    private WebElement loadedText;

    public DynamicLoadingExample1Page(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickStart() {
        getWait().until(ExpectedConditions.elementToBeClickable(startButton)).click();
        /* FLUENT WAIT
        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.invisibilityOf(
                driver.findElement(loadingIndicator)));
        */
    }

    public String getLoadedText() {
        return getWait().until(ExpectedConditions.visibilityOf(loadedText)).getText();
    }
}
