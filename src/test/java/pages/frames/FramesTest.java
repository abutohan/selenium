package pages.frames;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.FramesPage;
import pages.NestedFramesPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class FramesTest extends BaseTest {

    private FramesPage framesPage;

    @BeforeMethod
    public void initPage() {
        framesPage = homePage.clickFramesPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) throws IOException {
        assertEquals(framesPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), framesPage.getHeaderTitle()));
    }

    @Test(testName = "Nested Frames", priority = 2, dataProvider = "getNestedFramesDetails")
    public void testGoToFrames(JSONObject testData) {
        NestedFramesPage nestedFramesPage = framesPage.clickNestedFrames();
        assertEquals(nestedFramesPage.getFrameText(testData.getString("parentFrame"), testData.getString("childFrame")), testData.getString("text"));
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.frames.header");
    }

    @DataProvider(name = "getNestedFramesDetails")
    public Object[][] getNestedFramesDetails() throws IOException {
        return getTestDataFromJSON("test-data.frames.frames-nested");
    }

}
