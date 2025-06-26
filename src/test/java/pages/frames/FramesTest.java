package pages.frames;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
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

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Frames");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        framesPage = homePage.clickFramesPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = framesPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Nested Frames", priority = 2, dataProvider = "getNestedFramesData", groups = {"regression"})
    public void testGoToFrames(JSONObject testData) {
        NestedFramesPage nestedFramesPage = framesPage.clickNestedFrames();
        String parentFrame = testData.getString("parentFrame");
        String childFrame = testData.getString("childFrame");
        String actualMessage = nestedFramesPage.getFrameText(parentFrame, childFrame);
        String expectedMessage = testData.getString("text");
        assertEquals(actualMessage, expectedMessage,
                onFailure(expectedMessage, actualMessage));
    }

    @DataProvider(name = "getNestedFramesData")
    private Object[][] getNestedFramesData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("nested"));
    }

}
