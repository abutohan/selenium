package pages.fileupload;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.FileUploadPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class FileUploadTest extends BaseTest {

    private FileUploadPage fileUploadTest;

    @BeforeMethod
    public void initPage(){
        fileUploadTest = homePage.clickFileUploadPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) throws IOException {
        assertEquals(fileUploadTest.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), fileUploadTest.getHeaderTitle()));
    }

    @Test(testName = "Upload File", priority = 1, dataProvider = "getFileDetails")
    public void testUploadFile(JSONObject testData) throws IOException {
        fileUploadTest.uploadFile(testData.getString("directory"), testData.getString("file_name"));
        assertEquals(fileUploadTest.getUploadedFile(), testData.getString("file_name"));
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.file-uploader.header");
    }

    @DataProvider(name = "getFileDetails")
    public Object[][] getFileDetails() throws IOException {
        return getTestDataFromJSON("test-data.file-uploader.file-uploader");
    }
}
