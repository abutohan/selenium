package pages.largeanddeepdom;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LargeAndDeepDOMPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class LargeAndDeepDOMTest extends BaseTest {

    private LargeAndDeepDOMPage largeAndDeepDOMPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Large & Deep DOM");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        largeAndDeepDOMPage = homePage.clickLargeAndDeepDOMPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = largeAndDeepDOMPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Long List is Displayed", priority = 3, dataProvider = "getLongListDisplayStatus", groups = {"regression"})
    public void testLargeListIsDisplayed(JSONObject testData) {
        boolean actualStatus = largeAndDeepDOMPage.scrollToSiblings();
        boolean expectedStatus = testData.getBoolean("long_list_display_status");
        assertEquals(actualStatus, expectedStatus,
                onFailure(String.valueOf(expectedStatus), String.valueOf(actualStatus)));
    }

    @DataProvider(name = "getLongListDisplayStatus")
    private Object[][] getLongListDisplayStatus(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("long-list"));
    }

    @Test(testName = "Wide Table is Displayed", priority = 2, dataProvider = "getWideTableDisplayStatus", groups = {"regression"})
    public void testWideTableIsDisplayed(JSONObject testData) {
        boolean actualStatus = largeAndDeepDOMPage.scrollToTable();
        boolean expectedStatus = testData.getBoolean("wide_table_display_status");
        assertEquals(actualStatus, expectedStatus,
                onFailure(String.valueOf(expectedStatus), String.valueOf(actualStatus)));
    }

    @DataProvider(name = "getWideTableDisplayStatus")
    private Object[][] getWideTableDisplayStatus(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("wide-table"));
    }

}
