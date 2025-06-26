package pages.infinitescroll;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InfiniteScrollPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class InfiniteScrollTest extends BaseTest {

    private InfiniteScrollPage infiniteScrollPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Infinite Scroll");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        infiniteScrollPage = homePage.clickInfiniteScrollPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = infiniteScrollPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Scroll to nth Paragraph", priority = 2, dataProvider = "getScrollIndexData", groups = {"regression"})
    public void testScrollParagraph(JSONObject testData) {
        int expectedScrollIndex = testData.getInt("index");
        infiniteScrollPage.scrollToParagraph(expectedScrollIndex);
        int actualScrollIndex = infiniteScrollPage.getNumberOfParagraphsPresent();
        assertEquals(actualScrollIndex, expectedScrollIndex,
                onFailure(String.valueOf(expectedScrollIndex), String.valueOf(actualScrollIndex)));
    }

    @DataProvider(name = "getScrollIndexData")
    private Object[][] getScrollIndexData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("scroll-index"));
    }

}
