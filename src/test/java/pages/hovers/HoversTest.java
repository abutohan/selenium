package pages.hovers;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HoversPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.ReadJSON.getTestDataFromJSON;

public class HoversTest extends BaseTest {

    private HoversPage hoversPage;

    @BeforeMethod
    public void initPage() {
        hoversPage = homePage.clicHoversPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) throws IOException {
        assertEquals(hoversPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), hoversPage.getHeaderTitle()));
    }

    @Test(testName = "Hover User", priority = 2, dataProvider = "getUsersDetails")
    public void testHoverUser(JSONObject testData) {
        HoversPage.FigureCaption caption = hoversPage.hoverOverFigure(testData.getInt("index"));
        assertTrue(caption.isCaptionDisplayed(), "Caption not displayed");
        assertTrue(caption.getTitle().contains(testData.getString("name")), "Caption title incorrect");
        assertTrue(caption.getLink().endsWith(testData.getString("link")), "Link incorrect");
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.hovers.header");
    }

    @DataProvider(name = "getUsersDetails")
    public Object[][] getUsersDetails() throws IOException {
        return getTestDataFromJSON("test-data.hovers.hovers");
    }

}
