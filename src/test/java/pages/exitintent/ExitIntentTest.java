package pages.exitintent;

import base.BasePage;
import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ExitIntentPage;

import java.awt.*;
import java.io.IOException;

import static org.testng.Assert.*;
import static utils.ReadJSON.getTestDataFromJSON;

public class ExitIntentTest extends BaseTest {

    private ExitIntentPage exitIntentPage;

    @BeforeMethod
    public void initPage(){
        exitIntentPage = homePage.clickExitIntentPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        assertEquals(exitIntentPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), exitIntentPage.getHeaderTitle()));
    }

    @Test(testName = "Check Modal Presence", priority = 2, dataProvider = "getModalPresence")
    public void testModalPresence(JSONObject testData) throws AWTException {
        exitIntentPage.moveMousePosition(testData.getInt("xPos"), testData.getInt("yPos"));
        assertEquals(exitIntentPage.checkModalPresence(), testData.getBoolean("modal_presence"));
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.exit-intent.header");
    }

    @DataProvider(name = "getModalPresence")
    public Object[][] getModalPresence() throws IOException {
        return getTestDataFromJSON("test-data.exit-intent.exit-intent");
    }

}
