package pages.dynamiccontrols;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DynamicControlsPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class DynamicControlsTest extends BaseTest {

    private DynamicControlsPage dynamicControlsPage;

    @BeforeClass
    public void initTest() {
        test = extent.createTest("Dynamic Controls");
    }

    @BeforeMethod
    public void initPage() {
        dynamicControlsPage = homePage.clickDynamicControlsPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = dynamicControlsPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @Test(testName = "Message Can Be Entered", priority = 2, dataProvider = "getMessageToBeEnteredData")
    public void testMessageCanBeEnter(JSONObject testData) {
        dynamicControlsPage.setTextInput(testData.getString("message"));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.dynamic-controls.header");
    }

    @DataProvider(name = "getMessageToBeEnteredData")
    private Object[][] getMessageToBeEnteredData() throws IOException {
        return getTestDataFromJSON("test-data.dynamic-controls.messages");
    }
}
