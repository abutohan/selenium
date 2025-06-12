package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;

public class CheckboxesPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(xpath = "//input[@type='checkbox']")
    @CacheLookup
    private List<WebElement> checkboxes;

    public CheckboxesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return headerTitle.getText();
    }

    public void resetCheckboxes() {
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) checkbox.click();
        }
    }

    public void tickCheckboxes(HashMap<Integer, Boolean> checkboxConfig) {
        resetCheckboxes();
        for (int i = 0; i < checkboxes.size(); i++) {
            if (checkboxConfig.get(i)) checkboxes.get(i).click();
        }
    }

    public List<WebElement> getCheckboxes() {
        return checkboxes;
    }

    public int getCheckboxCount() {
        return checkboxes.size();
    }
    
}
