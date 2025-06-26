package pages.fileupload;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.FileUploadPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class FileUploadTest extends BaseTest {

    private FileUploadPage fileUploadTest;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("File Upload");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        fileUploadTest = homePage.clickFileUploadPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = fileUploadTest.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Upload File", priority = 2, dataProvider = "getFilesData", groups = {"regression"})
    public void testUploadFile(JSONObject testData) {
        fileUploadTest.uploadFile(testData.getString("directory"), testData.getString("file_name"));
        String actualUploadedFile = fileUploadTest.getUploadedFile();
        String expectedUploadedFile = testData.getString("file_name");
        assertEquals(actualUploadedFile, expectedUploadedFile,
                onFailure(expectedUploadedFile, actualUploadedFile));
    }

    @DataProvider(name = "getFilesData")
    private Object[][] getFilesData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("files"));
    }

}
