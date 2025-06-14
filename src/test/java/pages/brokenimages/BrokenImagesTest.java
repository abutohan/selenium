package pages.brokenimages;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BrokenImagesPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class BrokenImagesTest extends BaseTest {

    private BrokenImagesPage brokenImagesPage;

    @BeforeClass
    public void initTest() {
        test = extent.createTest("Broken Images");
    }

    @BeforeMethod
    public void initPage() {
        brokenImagesPage = homePage.clickBrokenImagesPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = brokenImagesPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @Test(testName = "All Images Are Loaded", priority = 2, dataProvider = "getImagesLoadedStatusData")
    public void testAllImageAreLoaded(JSONObject testData) {
        boolean expectedImagesLoadedStatus = testData.getBoolean("images_loaded_status");
        boolean actualImagesLoadedStatus = brokenImagesPage.allImageAreLoaded();
        assertEquals(actualImagesLoadedStatus, expectedImagesLoadedStatus,
                onFailure(String.valueOf(expectedImagesLoadedStatus), String.valueOf(actualImagesLoadedStatus)));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.broken-images.header");
    }

    @DataProvider(name = "getImagesLoadedStatusData")
    private Object[][] getImagesLoadedStatusData() throws IOException {
        return getTestDataFromJSON("test-data.broken-images.images-loaded-status");
    }

}
