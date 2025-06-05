package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HoversPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(className = "figure")
    @CacheLookup
    private List<WebElement> figures;

    private final By boxCaption = By.className("figcaption");

    public HoversPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return getWait().until(ExpectedConditions.visibilityOf(headerTitle)).getText();
    }

    public FigureCaption hoverOverFigure(int index) {
        WebElement figure = getWait().until(ExpectedConditions.visibilityOfAllElements(figures)).get(index - 1);

        Actions actions = new Actions(getDriver());
        actions.moveToElement(figure).perform();
        return new FigureCaption(figure.findElement(boxCaption));
    }

    public static class FigureCaption {

        private final WebElement caption;
        private final By header = By.tagName("h5");
        private final By link = By.tagName("a");

        public FigureCaption(WebElement caption) {
            this.caption = caption;
        }

        public boolean isCaptionDisplayed() {
            return caption.isDisplayed();
        }

        public String getTitle() { return caption.findElement(header).getText(); }

        public String getLink() {
            return caption.findElement(link).getDomProperty("href");
        }

        public String getLinkText() {
            return caption.findElement(link).getText();
        }
    }
}
