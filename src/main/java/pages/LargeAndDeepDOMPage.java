package pages;

import base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

public class LargeAndDeepDOMPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(id = "large-table")
    @CacheLookup
    private WebElement table;

    @FindBy(id = "sibling-1.1")
    @CacheLookup
    private WebElement siblings;

    public LargeAndDeepDOMPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return getWait().until(ExpectedConditions.visibilityOf(headerTitle)).getText();
    }

    public boolean scrollToSiblings() {
        String script = "arguments[0].scrollIntoView();";
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript(script, siblings);
        return isElementInViewport(js, siblings);
    }

    public boolean scrollToTable() {
        String script = "arguments[0].scrollIntoView();";
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript(script, table);
        return isElementInViewport(js, table);
    }

    private boolean isElementInViewport(JavascriptExecutor js, WebElement element) {

        // First, check basic display properties
        if (!element.isDisplayed()) {
            return false;
        }

        // Check if element has actual dimensions
        Long width = (Long) js.executeScript("return arguments[0].offsetWidth;", element);
        Long height = (Long) js.executeScript("return arguments[0].offsetHeight;", element);
        if (width == 0 || height == 0) {
            return false; // Element has no visible area
        }

        // Get the element's bounding rectangle relative to the viewport
        String script = "return arguments[0].getBoundingClientRect();";
        @SuppressWarnings("unchecked") // Cast required for the Map
        Map<String, Object> rect = (Map<String, Object>) js.executeScript(script, element);

        double top = (Double) rect.get("top");
        double bottom = (Double) rect.get("bottom");
        double left = (Double) rect.get("left");
        double right = (Double) rect.get("right");

        // Get viewport dimensions
        Long viewportWidth = (Long) js.executeScript("return window.innerWidth || document.documentElement.clientWidth;");
        Long viewportHeight = (Long) js.executeScript("return window.innerHeight || document.documentElement.clientHeight;");

        // Check for intersection:
        // Element's bottom must be below viewport's top (0)
        // Element's top must be above viewport's bottom (viewportHeight)
        // Element's right must be to the right of viewport's left (0)
        // Element's left must be to the left of viewport's right (viewportWidth)
        return (bottom > 0 &&
                top < viewportHeight &&
                right > 0 &&
                left < viewportWidth);
    }
}
