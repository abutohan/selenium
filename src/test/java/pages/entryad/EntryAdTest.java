package pages.entryad;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.EntryAdPage;

import java.io.IOException;

import static org.testng.Assert.*;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class EntryAdTest extends BaseTest {

    private EntryAdPage entryAdPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Entry Ad");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        entryAdPage = homePage.clickEntryAdPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = entryAdPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Check Modal Presence", priority = 2, groups = {"regression"})
    public void testModalPresence() {
        assertTrue(entryAdPage.checkModalPresence());
        entryAdPage.clickCloseModal();
        assertFalse(entryAdPage.checkModalPresence());
        getWindowManager().refreshPage();
        assertFalse(entryAdPage.checkModalPresence());
        entryAdPage.clickRestart();
        getWindowManager().refreshPage();
        assertTrue(entryAdPage.checkModalPresence());
    }

}
