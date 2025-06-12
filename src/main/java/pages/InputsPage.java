package pages;

import base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InputsPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(tagName = "input")
    @CacheLookup
    private WebElement input;

    public InputsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return headerTitle.getText();
    }

    public void setInputBySendingKeys(String text) {
        input.sendKeys(text);
    }

    public void setInputByArrowKeys(int number) {
        boolean value = number >= 0;
        Keys key = value ? Keys.ARROW_UP : Keys.ARROW_DOWN;

        int count = Math.abs(number);

        for (int i = 0; i < count; i++) {
            input.sendKeys(key);
        }
    }

    public void clearInput() {
        input.clear();
    }

    public String getInput() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String value = (String) js.executeScript("return arguments[0].value;", input);
        return value.isBlank() ? "0" : value;
    }
}
