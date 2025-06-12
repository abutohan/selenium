package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InfiniteScrollPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    private final By textBlocks = By.className("jscroll-added");

    public InfiniteScrollPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return headerTitle.getText();
    }

    public int getNumberOfParagraphsPresent() {
        return getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(textBlocks)).size();
    }

    public void scrollToParagraph(int index) {
        String script = "window.scrollTo(0, document.body.scrollHeight)";
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();

        while (getNumberOfParagraphsPresent() < index) {
            jsExecutor.executeScript(script);
        }
    }

}
