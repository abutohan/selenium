package pages.brokenimages;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
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

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Broken Images");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        brokenImagesPage = homePage.clickBrokenImagesPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = brokenImagesPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "All Images Are Loaded", priority = 2, dataProvider = "getImagesLoadedStatusData", groups = {"regression"})
    public void testAllImageAreLoaded(JSONObject testData) {
        boolean expectedImagesLoadedStatus = testData.getBoolean("images_loaded_status");
        boolean actualImagesLoadedStatus = brokenImagesPage.allImageAreLoaded();
        assertEquals(actualImagesLoadedStatus, expectedImagesLoadedStatus,
                onFailure(String.valueOf(expectedImagesLoadedStatus), String.valueOf(actualImagesLoadedStatus)));
    }

    @DataProvider(name = "getImagesLoadedStatusData")
    private Object[][] getImagesLoadedStatusData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("images-loaded-status"));
    }

}
