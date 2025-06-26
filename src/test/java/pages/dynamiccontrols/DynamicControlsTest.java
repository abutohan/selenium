package pages.dynamiccontrols;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
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

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Dynamic Controls");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        dynamicControlsPage = homePage.clickDynamicControlsPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = dynamicControlsPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Message Can Be Entered", priority = 2, dataProvider = "getMessageToBeEnteredData", groups = {"regression"})
    public void testMessageCanBeEnter(JSONObject testData) {
        dynamicControlsPage.setTextInput(testData.getString("message"));
    }

    @DataProvider(name = "getMessageToBeEnteredData")
    private Object[][] getMessageToBeEnteredData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("messages"));
    }

}
