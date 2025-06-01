package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.DownloadsUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileDownloadPage extends BasePage {

    @FindBy(tagName = "h3")
    @CacheLookup
    private WebElement headerTitle;

    @FindBy(xpath = "//div[@class=\"example\"]/a")
    @CacheLookup
    private List<WebElement> downloadLinks;

    public FileDownloadPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return getWait().until(ExpectedConditions.visibilityOf(headerTitle)).getText();
    }

    public void downloadFiles(String downloadDir) throws IOException, InterruptedException {
        getWait().until(ExpectedConditions.visibilityOfAllElements(downloadLinks));

        //get absolute path
        String downloadPath = DownloadsUtil.absoluteDownloadPath(downloadDir);

        DownloadsUtil.clearDownloadDirectory(downloadPath);

        for (WebElement el : downloadLinks) {

            el.click();

            String fileName = el.getText();

            boolean fileDownloaded = DownloadsUtil.waitForDownloadCompletion(fileName, downloadPath, 60);

            if (fileDownloaded) {
                System.out.println("File downloaded successfully to: " + new File(downloadPath, fileName).getAbsolutePath());
            } else {
                System.out.println("File download failed or timed out.");
            }
        }
    }
}
