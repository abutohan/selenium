package pages.hovers;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HoversPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class HoversTest extends BaseTest {

    private HoversPage hoversPage;

    @BeforeClass
    public void initTest() {
        test = extent.createTest("Hovers");
    }

    @BeforeMethod
    public void initPage() {
        hoversPage = homePage.clicHoversPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = hoversPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @Test(testName = "Hover User", priority = 2, dataProvider = "getUsersData")
    public void testHoverUser(JSONObject testData) {
        int index = testData.getInt("index");
        HoversPage.FigureCaption caption = hoversPage.hoverOverFigure(index);
        assertTrue(caption.isCaptionDisplayed(), "Caption not displayed");
        String name = testData.getString("name");
        String link = testData.getString("link");
        assertTrue(caption.getTitle().contains(name), "Caption title incorrect");
        assertTrue(caption.getLink().endsWith(link), "Link incorrect");
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.hovers.header");
    }

    @DataProvider(name = "getUsersData")
    private Object[][] getUsersData() throws IOException {
        return getTestDataFromJSON("test-data.hovers.hovers");
    }

}
