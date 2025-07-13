package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WysiwygEditorPage extends BasePage {

    @FindBy(id = "tinymce")
    @CacheLookup
    private WebElement textArea;

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    public WysiwygEditorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return headerTitle.getText();
    }

    public void clearTextArea() {
        switchToEditArea();
        (textArea).clear();
        switchToMainArea();
    }

    public void setTextArea(String text) {
        switchToEditArea();
        (textArea).sendKeys(text);
        switchToMainArea();
    }

    private void switchToEditArea() {
        String editorIframeId = "mce_0_ifr";
        getDriver().switchTo().frame(editorIframeId);
    }

    private void switchToMainArea() {
        getDriver().switchTo().parentFrame();
    }

    public String getTextFromEditor() {
        switchToEditArea();
        String text = (textArea).getText();
        switchToMainArea();
        return text;
    }

}
