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
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class FramesTest extends BaseTest {

    private FramesPage framesPage;

    @BeforeMethod
    public void initPage() {
        framesPage = homePage.clickFramesPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = framesPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @Test(testName = "Nested Frames", priority = 2, dataProvider = "getNestedFramesData")
    public void testGoToFrames(JSONObject testData) {
        NestedFramesPage nestedFramesPage = framesPage.clickNestedFrames();
        String parentFrame = testData.getString("parentFrame");
        String childFrame = testData.getString("childFrame");
        String actualMessage = nestedFramesPage.getFrameText(parentFrame, childFrame);
        String expectedMessage = testData.getString("text");
        assertEquals(actualMessage, expectedMessage,
                onFailure(expectedMessage, actualMessage));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.frames.header");
    }

    @DataProvider(name = "getNestedFramesData")
    private Object[][] getNestedFramesData() throws IOException {
        return getTestDataFromJSON("test-data.frames.nested");
    }

}
