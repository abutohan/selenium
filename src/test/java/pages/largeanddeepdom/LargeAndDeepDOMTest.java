package pages.largeanddeepdom;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LargeAndDeepDOMPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class LargeAndDeepDOMTest extends BaseTest {

    private LargeAndDeepDOMPage largeAndDeepDOMPage;

    @BeforeMethod
    public void initPage() {
        largeAndDeepDOMPage = homePage.clickLargeAndDeepDOMPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        assertEquals(largeAndDeepDOMPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), largeAndDeepDOMPage.getHeaderTitle()));
    }

    @Test(testName = "Long List is Displayed", priority = 2, dataProvider = "getLongListDisplayStatus")
    public void testLargeListIsDisplayed(JSONObject testData) {
        assertEquals(largeAndDeepDOMPage.scrollToSiblings(), testData.getBoolean("long_list_display_status"));
    }

    @Test(testName = "Wide Table is Displayed", priority = 3, dataProvider = "getWideTableDisplayStatus")
    public void testWideTableIsDisplayed(JSONObject testData) {
        assertEquals(largeAndDeepDOMPage.scrollToTable(), testData.getBoolean("wide_table_display_status"));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.large-and-deep-dom.header");
    }

    @DataProvider(name = "getLongListDisplayStatus")
    private Object[][] getLongListDisplayStatus() throws IOException {
        return getTestDataFromJSON("test-data.large-and-deep-dom.long-list");
    }

    @DataProvider(name = "getWideTableDisplayStatus")
    private Object[][] getWideTableDisplayStatus() throws IOException {
        return getTestDataFromJSON("test-data.large-and-deep-dom.wide-table");
    }
}
