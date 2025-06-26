package pages.filedownload;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.FileDownloadPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class FileDownloadTest extends BaseTest {

    private FileDownloadPage fileDownloadPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("File Download");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        fileDownloadPage = homePage.clickFileDownloadPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = fileDownloadPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Download Files", priority = 2, groups = {"regression"})
    public void testDownloadFileS() throws IOException, InterruptedException {
        fileDownloadPage.downloadFiles("download-dir");
    }

}
