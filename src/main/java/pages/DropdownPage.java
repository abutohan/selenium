package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(id = "dropdown")
    @CacheLookup
    private WebElement dropdownList;

    public DropdownPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return headerTitle.getText();
    }

    public String selectFromDropDown(String option) {
        findDropdownElement().selectByVisibleText(option);
        return findDropdownElement().getFirstSelectedOption().getText();
    }

    public Select findDropdownElement() {
        return new Select(dropdownList);
    }

}
