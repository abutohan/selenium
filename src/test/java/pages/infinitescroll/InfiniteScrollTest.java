package pages.infinitescroll;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InfiniteScrollPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class InfiniteScrollTest extends BaseTest {

    private InfiniteScrollPage infiniteScrollPage;

    @BeforeMethod
    public void initPage() {
        infiniteScrollPage = homePage.clickInfiniteScrollPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) throws IOException {
        assertEquals(infiniteScrollPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), infiniteScrollPage.getHeaderTitle()));
    }

    @Test(testName = "Scroll to nth Paragraph", priority = 2, dataProvider = "getScrollIndex")
    public void testScrollParagraph(JSONObject testData) throws IOException {
        infiniteScrollPage.scrollToParagraph(testData.getInt("index"));
        assertEquals(infiniteScrollPage.getNumberOfParagraphsPresent(), testData.getInt("index"));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.infinite-scroll.header");
    }

    @DataProvider(name = "getScrollIndex")
    private Object[][] getScrollIndex() throws IOException {
        return getTestDataFromJSON("test-data.infinite-scroll.infinite-scroll");
    }

}
