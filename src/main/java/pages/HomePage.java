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

    public CheckboxesPage clickCheckboxesPage() {
        clickAnchorLink("Checkboxes");
        return new CheckboxesPage(getDriver());
    }

    public ContextMenuPage clickContextMenuPage() {
        clickAnchorLink("Context Menu");
        return new ContextMenuPage(getDriver());
    }

    public DigestAuthenticationPage clickDigestAuthPage() {
        clickAnchorLink("Digest Authentication");
        return new DigestAuthenticationPage(getDriver());
    }

    public DisappearingElementsPage clickDisappearingElementsPage(){
        clickAnchorLink("Disappearing Elements");
        return new DisappearingElementsPage(getDriver());
    }

    public DragAndDropPage clickDragAndDropPage(){
        clickAnchorLink("Drag and Drop");
        return new DragAndDropPage(getDriver());
    }

    public DropdownPage clickDropdownListPage(){
        clickAnchorLink("Dropdown");
        return new DropdownPage(getDriver());
    }

    public DynamicControlsPage clickDynamicControlsPage(){
        clickAnchorLink("Dynamic Controls");
        return new DynamicControlsPage(getDriver());
    }

    public DynamicLoadingPage clickDynamicLoadingPage(){
        clickAnchorLink("Dynamic Loading");
        return new DynamicLoadingPage(getDriver());
    }

    public EntryAdPage clickEntryAdPage(){
        clickAnchorLink("Entry Ad");
        return new EntryAdPage(getDriver());
    }

    public ExitIntentPage clickExitIntentPage(){
        clickAnchorLink("Exit Intent");
        return new ExitIntentPage(getDriver());
    }

    public FileDownloadPage clickFileDownloadPage(){
        clickAnchorLink("File Download");
        return new FileDownloadPage(getDriver());
    }

    public FileUploadPage clickFileUploadPage(){
        clickAnchorLink("File Upload");
        return new FileUploadPage(getDriver());
    }
}
