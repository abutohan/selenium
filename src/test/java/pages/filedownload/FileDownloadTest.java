package pages.filedownload;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.FileDownloadPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class FileDownloadTest extends BaseTest {

    private FileDownloadPage fileDownloadPage;

    @BeforeMethod
    public void initPage() {
        fileDownloadPage = homePage.clickFileDownloadPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) throws IOException {
        assertEquals(fileDownloadPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), fileDownloadPage.getHeaderTitle()));
    }

    @Test(testName = "Download Files", priority = 2)
    public void testDownloadFileS() throws IOException, InterruptedException {
        fileDownloadPage.downloadFiles("download-dir");
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.file-download.header");
    }
}
