package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private void clickAnchorLink(String anchorLink) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText(anchorLink))).click();
    }

    public ABTestingPage clickABTestingPage() {
        clickAnchorLink("A/B Testing");
        return new ABTestingPage(getDriver());
    }

    public AddRemoveElementsPage clickAddRemoveElementsPage() {
        clickAnchorLink("Add/Remove Elements");
        return new AddRemoveElementsPage(getDriver());
    }

    public BrokenImagesPage clickBrokenImagesPage() {
        clickAnchorLink("Broken Images");
        return new BrokenImagesPage(getDriver());
    }
}
