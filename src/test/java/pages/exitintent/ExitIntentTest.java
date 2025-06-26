package pages.exitintent;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ExitIntentPage;

import java.awt.*;
import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class ExitIntentTest extends BaseTest {

    private ExitIntentPage exitIntentPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Exit Intent");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        exitIntentPage = homePage.clickExitIntentPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = exitIntentPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Check Modal Presence", priority = 2, dataProvider = "getModalPresenceData", groups = {"regression"})
    public void testModalPresence(JSONObject testData) throws AWTException {
        exitIntentPage.moveMousePosition(testData.getInt("xPos"), testData.getInt("yPos"));
        boolean actualModalPresenceStatus = exitIntentPage.checkModalPresence();
        boolean expectedModalPresenceStatus = testData.getBoolean("modal_presence");
        assertEquals(actualModalPresenceStatus, expectedModalPresenceStatus,
                onFailure(String.valueOf(expectedModalPresenceStatus), String.valueOf(actualModalPresenceStatus)));
    }

    @DataProvider(name = "getModalPresenceData")
    private Object[][] getModalPresenceData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("modal-presence-status"));
    }

}
