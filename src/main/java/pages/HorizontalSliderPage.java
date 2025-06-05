package pages;

import base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HorizontalSliderPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(id = "range")
    @CacheLookup
    private WebElement sliderValue;

    @FindBy(css = "input[type=\"range\"]")
    @CacheLookup
    private WebElement slider;

    public HorizontalSliderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return getWait().until(ExpectedConditions.visibilityOf(headerTitle)).getText();
    }

    public String getSliderValue() {
        return getWait().until(ExpectedConditions.visibilityOf(sliderValue)).getText();
    }

    public void setSliderValue(int number) {
        while (number != 0) {
            getWait().until(ExpectedConditions.visibilityOf(slider)).sendKeys(Keys.ARROW_RIGHT);
            number--;
        }
    }

}
