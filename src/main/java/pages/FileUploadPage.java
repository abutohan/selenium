package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.nio.file.Paths;

public class FileUploadPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(id = "file-upload")
    @CacheLookup
    private WebElement inputField;

    @FindBy(id = "file-submit")
    @CacheLookup
    private WebElement uploadButton;

    @FindBy(id = "uploaded-files")
    @CacheLookup
    private WebElement uploadedFiles;

    public FileUploadPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return headerTitle.getText();
    }

    public void uploadFile(String fileDirectory, String fileName) {
        String absolutePathOfFile = Paths.get(fileDirectory, fileName).toAbsolutePath().toString();
        inputField.sendKeys(absolutePathOfFile);
        uploadButton.click();
    }

    public String getUploadedFile() {
        return getWait().until(ExpectedConditions.visibilityOf(uploadedFiles)).getText();
    }

}
